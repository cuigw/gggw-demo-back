package com.gggw.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:ServletTest <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-18 上午10:01:17 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class ServletTest extends HttpServlet{
	
	/**
	 *		1.	Filter不像Servlet，它不能产生一个请求或者响应，
	 *			它只是修改对某一资源的请求，或者修改从某一的响应。
	 *
	 *			最后一个chain.doFilter(req, res);之后  执行servlet的service()方法
	 *
	 *		2.
	 *
	 *
	 *
	 *
	 *
	 */

	//初始化
	public void init() {
		System.out.println("ServletTest :" + "初始化成功!");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("ServletTest :" + "doGet方法正在执行!");
		this.doPost(request, response);
	}
	
	//http://localhost:8080/gggw-demo-back?name=%E5%B4%94%E9%AB%98%E5%A8%81&url=aaaaa
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		System.out.println("ServletTest :" + "doPost方法正在执行!");
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		String title = "使用 POST 方法读取表单数据";
		// 处理中文
		String name = new String(request.getParameter("name").getBytes(
				"ISO8859-1"), "UTF-8");
		String docType = "<!DOCTYPE html> \n";
		out.println(docType + "<html>\n" + "<head><title>" + title
				+ "</title></head>\n" + "<body bgcolor=\"#f0f0f0\">\n"
				+ "<h1 align=\"center\">" + title + "</h1>\n" + "<ul>\n"
				+ "  <li><b>站点名</b>：" + name + "\n" + "  <li><b>网址</b>："
				+ request.getParameter("url") + "\n" + "</ul>\n"
				+ "</body></html>");
	}
	
	public void destroy() {
		System.out.println("ServletTest :" + "destroy方法正在执行!");
	}
}

