package com.gggw.core.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.OrderComparator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.multiaction.InternalPathMethodNameResolver;
import org.springframework.web.servlet.mvc.multiaction.MethodNameResolver;

/**
 * 功能说明: 获取bean实例的工具类<br>
 * 系统版本: @version 1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2016-9-11 下午12:19:29<br>
 */

/**
 * 
 *	实现接口ApplicationContextAware
 *		实现该接口的setApplicationContext(ApplicationContext context)方法，并保存ApplicationContext 对象。
 *		Spring初始化时，会通过该方法将ApplicationContext对象注入。
 *
 */
public class SpringContext implements ApplicationContextAware {

	public static Logger logger = LoggerFactory.getLogger(SpringContext.class);
	
	private static ApplicationContext appContext = null;
	
	static {
		//设置Xserver模式 ，依靠系统的计算能力模拟出硬件特性
		System.setProperty("java.awt.headless", "true");
	}
	
	
	/**
	 * @param applicationContext
	 * 		 其实是个XmlWebApplicationContext （通过web.xml示例化）
	 * 		1.ClassPathXmlApplicationContext
	 * 		2.FileSystemXmlApplicationContext
	 * 		3.XmlWebApplicationContext
	 * 
	 * 
	 * 
	 * 
	 * 基本原理其实就是通过反射解析类及其类的各种信息，包括构造器、方法及其参数，属性。
	 * 然后将其封装成bean定义信息类、constructor信息类、method信息类、property信息类，
	 * 最终放在一个map里，也就是所谓的container，池等等，其实就是个map
	 * 当你写好配置文件，启动项目后，框架会先按照你的配置文件找到那个要scan的包，
	 * 然后解析包里面的所有类，找到所有含有@bean，@service等注解的类，利用反射解析它们，包括解析构造器，方法，属性等等，
	 * 然后封装成各种信息类放到一个map里。
	 * 每当你需要一个bean的时候，框架就会从container找是不是有这个类的定义啊？如果找到则通过构造器new出来（这就是控制反转，不用你new,框架帮你new），
	 * 再在这个类找是不是有要注入的属性或者方法，比如标有@autowired的属性，
	 * 如果有则还是到container找对应的解析类，new出对象，并通过之前解析出来的信息类找到setter方法，
	 * 然后用该方法注入对象（这就是依赖注入）。如果其中有一个类container里没找到，则抛出异常，
	 * 比如常见的spring无法找到该类定义，无法wire的异常。
	 * 还有就是嵌套bean则用了一下递归，container会放到servletcontext里面，每次reQuest从servletcontext找这个container即可，不用多次解析类定义。
	 * 如果bean的scope是singleton，则会重用这个bean不再重新创建，将这个bean放到一个map里，每次用都先从这个map里面找。
	 * 如果scope是session，则该bean会放到session里面。仅此而已，没必要花更多精力。建议还是多看看底层的知识。
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		appContext = applicationContext;
	}
	
	public static boolean containsBean(String beanName) {
		if (appContext != null) {
			return appContext.containsBean(beanName);
		}
		return false;
	}
	
	public static Object getBean(String beanName) {
		if (appContext != null) {
			return appContext.getBean(beanName);
		}
		return null;
	}
	
	//根据类型找bean，如果返回多个，取第一个。
	public static <T> T getBean(Class<T> clazz) {
		if (appContext != null) {
			try {
				return appContext.getBean(clazz);
			} catch (BeansException e) {
				Map<String, T> map = getBeansOfType(clazz);
				for (Map.Entry<String, T> entry : map.entrySet()) {
					if (entry.getValue().getClass().equals(clazz)) {
						return entry.getValue();
					}
				}
			}
		}
		return null;
	}	
	
	public static <T> Map<String, T> getBeansOfType(Class<T> clazz) throws BeansException {
		Map<String, T> map = null;
		if (appContext != null) {
			map = appContext.getBeansOfType(clazz);
			//防止执行两次
			if (map == null || map.isEmpty() && appContext.getParent() != null ) {
				map = appContext.getParent().getBeansOfType(clazz);
			}
		}
		if (map == null) {
			map = new HashMap<String, T>();
		}
		return map;
	}
	
	public static <T> T getBean(String beanId, Class<T> clazz) {
		if (appContext != null) {
			return appContext.getBean(beanId, clazz);
		}
		return null;
	}
	
	public <T> List<Map.Entry<String, T>> getOrderedBeans(Class<T> clazz) {
		Set<Map.Entry<String, T>> caches = getBeansOfType(clazz).entrySet();
		List<Map.Entry<String, T>> list = new ArrayList<Map.Entry<String, T>>(caches);
		//根据order排序
		Collections.sort(list, new Comparator<Map.Entry<String, T>>() {

			public int compare(Map.Entry<String, T> e1, Map.Entry<String, T> e2) {
				return new OrderComparator().compare(e1.getValue(),
						e2.getValue());
			}
		});
		return list;
	}
	
	public static Method getHandleMethod(HttpServletRequest request, Object handler) throws Exception {
		MethodNameResolver methodNameResolver = new InternalPathMethodNameResolver();
		String methodName = methodNameResolver.getHandlerMethodName(request);
		Map<String, String> pathVariables = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		for (Method method : handler.getClass().getMethods()) {
			RequestMapping rm = method.getAnnotation(RequestMapping.class);
			if (rm != null) {
				for (String val : rm.value()) {
					while (pathVariables != null && val.indexOf("{") < val.indexOf("}")) {
						String temp = val.substring(val.indexOf("{") + 1, val.indexOf("}"));
						val = val.replace("{" + temp + "}", pathVariables.get(temp));
					}
					if ("".equals(val) || methodName.equals(val)) {
						return method;
					}
				}
			}
		}
		return null;
	}
}

