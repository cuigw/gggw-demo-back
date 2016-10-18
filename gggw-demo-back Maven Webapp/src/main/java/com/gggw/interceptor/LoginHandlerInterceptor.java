package com.gggw.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import com.gggw.core.annotation.NoLogin;

/**
 * 
 * ClassName: LoginHandlerInterceptor <br/>
 * Function: 校验用户是否登录拦截器.使用cookie代替session以便集群. <br/>
 * 			 session:	服务器端保持状态
 * 			 cookie:    客户端保持状态
 * 			       		集群环境下，使用session,当另一台服务器接收请求时,会找不到session.
 * date: 2016-6-24 下午2:33:19 <br/>
 *
 * @author cgw
 * @version 
 * @since JDK 1.6
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	/**
	 * 在业务处理器处理请求之前被调用 
	 * 		1.返回false 退出拦截器链
	 * 		2.返回true  执行下一个拦截器,直到所有的拦截器都执行完毕 再执行被拦截的Controller 然后进入拦截器链,
	 * 		3.抛出异常
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		
		/**
		 * 		登录拦截处理流程：
		 * 				1.读取cookie（USER_SESSION）, 把cookie转为User类
		 * 				2.request中设置属性  request.setAttribute(sessionName, user);
		 * 				3.判断是否为静态资源，如果是不做拦截
		 * 				4.如果有注解@NoLogin 不做拦截
		 * 				5.其他登录逻辑
		 */
		
		
		
		/** 静态资源请求 不做拦截*/
		if (handler.getClass().equals(ResourceHttpRequestHandler.class)) {
			return true;
		}
		
		/**
		 * 	1.不是  Controller、RestController不做拦截
		 *  2.NoLogin类 不做拦截
		 *  3.NoLogin方法不做拦截
		 */
		HandlerMethod method = (HandlerMethod)handler;
		if (!method.getBeanType().isAnnotationPresent(Controller.class) && !method.getBeanType().isAnnotationPresent(RestController.class)) {
			return true;
		}
		if (method.getBeanType().isAnnotationPresent(NoLogin.class)) {
			return true;
		}
		if (method.getMethodAnnotation(NoLogin.class) != null) {
			return true;
		}
		
		
		System.out.println("LoginHandlerInterceptor  preHandle()===========登录拦截");
		
		return true;
	}
	
	
	/** 
	 * 在业务处理器处理请求执行完成后,生成视图之前执行的动作
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		
		/**
		 * 1.读取request   user = request.getAttribute(sessionName);
		 * 2.把user写入cookie
		 */
		
		System.out.println("LoginHandlerInterceptor  postHandle()===========");
	}
	
	
	/**
	 * 在DispatcherServlet完全处理完请求后被调用
	 * 
	 * 当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion()
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
		System.out.println("LoginHandlerInterceptor  afterCompletion()===========");
		
	}
}
