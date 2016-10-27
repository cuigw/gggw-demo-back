package com.gggw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.gggw.core.exception.BizException;
import com.gggw.util.PropertiesUtils;

/**
 * ClassName:SecurityFilter <br/>
 * Function: 安全过滤器，主要针对xss攻击   通过转义的方式<br/>
 * Date: 2016-10-27 下午1:37:40 <br/>
 * 
 * @author cgw
 */
public class SecurityFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		XSSHttpServletRequestWrapper xssRequest = new XSSHttpServletRequestWrapper((HttpServletRequest) request);
		chain.doFilter(xssRequest, response);
		//chain.doFilter(request, response);
		//checkCsrf(xssRequest);

	}

	private void checkCsrf(HttpServletRequest request) {
		String origin = request.getHeader("Origin");
		String referer = request.getHeader("Referer");
		if (origin == null && referer == null) {
			return;
		}

		String[] hosts = new String[] { "地址1",
				"地址2",
				"地址3",
				"地址4",
				"地址5",
				"地址6" };
		if (StringUtils.isNotEmpty(referer) && StringUtils.isNotEmpty(origin)
				&& !StringUtils.startsWith(referer, origin)) {
			// origin、referer不一致
			throw new BizException("403", "请求不合法，访问被拒绝");
		}
		for (String host : hosts) {
			if (StringUtils.lastIndexOf(host, ":") > 5) {
				host = host.substring(0, host.lastIndexOf(":"));
			}
			if (StringUtils.isNotEmpty(origin)
					&& StringUtils.startsWith(origin, host)) {
				return;
			}
			if (StringUtils.isNotEmpty(referer)
					&& StringUtils.startsWith(referer, host)) {
				return;
			}
		}
		throw new BizException("403", "请求不合法，访问被拒绝");
	}

	@Override
	public void destroy() {
	}

}
