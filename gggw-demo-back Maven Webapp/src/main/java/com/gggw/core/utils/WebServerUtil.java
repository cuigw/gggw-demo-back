package com.gggw.core.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;

import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * ClassName:EnvironmentUtils <br/>
 * Function: web环境相关. <br/>
 * Date:     2016-6-25 上午10:19:28 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class WebServerUtil {

	private static Logger logger = LoggerFactory.getLogger(WebServerUtil.class);

	public static String getHttpPort() throws AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException {
		return getServerPort(false);
	}

	public static String getHttpsPort() throws AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException {
		return getServerPort(true);
	}

	/**
	 * 获取本地IP地址
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getHostAddress() throws UnknownHostException {
		Enumeration<NetworkInterface> netInterfaces = null;
		try {
			netInterfaces = NetworkInterface.getNetworkInterfaces();
			while (netInterfaces.hasMoreElements()) {
				NetworkInterface ni = netInterfaces.nextElement();
				Enumeration<InetAddress> ips = ni.getInetAddresses();
				while (ips.hasMoreElements()) {
					InetAddress ip = ips.nextElement();
					if (ip.isSiteLocalAddress()) {
						return ip.getHostAddress();
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return InetAddress.getLocalHost().getHostAddress();
	}

	/**
	 * 获取服务端口
	 * @return
	 * @throws Exception
	 */
	public static String getServicePort() throws Exception {
		String port = getHttpPort();
		if (StringUtils.isEmpty(port)) {
			port = getHttpsPort();
		}
		return port;
	}

	/**
	 * 获取本地服务地址
	 * @return
	 * @throws Exception
	 */
	public static String getServiceAddress() throws Exception {
		return getServiceAddress(ContextLoader.getCurrentWebApplicationContext());
	}

	/**
	 * 获取本地服务地址
	 * @return
	 * @throws Exception
	 */
	public static String getServiceAddress(ApplicationContext context) throws Exception {
		String contextPath;
		if (context == null || !(context instanceof WebApplicationContext)) {
			contextPath = ContextLoader.getCurrentWebApplicationContext().getServletContext().getContextPath();
		} else {
			contextPath = ((WebApplicationContext)context).getServletContext().getContextPath();
		}
		String hostAddress = getHostAddress();
		String port = getHttpPort();
		if (StringUtils.isEmpty(port)) {
			port = WebServerUtil.getHttpsPort();
			return "https://" + hostAddress + ":" + port + contextPath;
		}
		return "http://" + hostAddress + ":" + port + contextPath;
	}

	/**
	 * 获取服务端口号
	 * @return 端口号
	 * @throws ReflectionException
	 * @throws MBeanException
	 * @throws InstanceNotFoundException
	 * @throws AttributeNotFoundException
	 */
	private static String getServerPort(boolean secure) throws AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException {
		MBeanServer mBeanServer = null;
		if (MBeanServerFactory.findMBeanServer(null).size() > 0) {
			mBeanServer = (MBeanServer)MBeanServerFactory.findMBeanServer(null).get(0);
		}
		if (mBeanServer == null) {
			logger.debug("调用findMBeanServer查询到的结果为null");
			return "";
		}
		Set<ObjectName> names = null;
		try {
			names = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
		} catch (Exception e) {
			return "";
		}
		Iterator<ObjectName> it = names.iterator();
		ObjectName oname = null;
		while (it.hasNext()) {
			oname = (ObjectName)it.next();
			String protocol = (String)mBeanServer.getAttribute(oname, "protocol");
			String scheme = (String)mBeanServer.getAttribute(oname, "scheme");
			Boolean secureValue = (Boolean)mBeanServer.getAttribute(oname, "secure");
			Boolean SSLEnabled = (Boolean)mBeanServer.getAttribute(oname, "SSLEnabled");
			if (SSLEnabled != null && SSLEnabled) {// tomcat6开始用SSLEnabled
				secureValue = true;// SSLEnabled=true但secure未配置的情况
				scheme = "https";
			}
			if (protocol != null && ("HTTP/1.1".equals(protocol) || protocol.contains("http"))) {
				if (secure && "https".equals(scheme) && secureValue) {
					return ((Integer)mBeanServer.getAttribute(oname, "port")).toString();
				} else if (!secure && !"https".equals(scheme) && !secureValue) {
					return ((Integer)mBeanServer.getAttribute(oname, "port")).toString();
				}
			}
		}
		return "";
	}
}
