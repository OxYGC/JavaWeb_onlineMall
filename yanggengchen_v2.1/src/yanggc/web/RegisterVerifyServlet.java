package yanggc.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yanggc.domain.User;
import yanggc.impl.RegisterVerifyService;

public class RegisterVerifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置响应的类型
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		//接受客户端的用户名
		String um=request.getParameter("um");
		RegisterVerifyService rvs = new RegisterVerifyService();
		User u = rvs.findUsers(um);
		if(null != u) {
			out.print("fail");
		}else {
			out.print("success");
		}
		
		
		/*if(null!=um&&um.equals("tom")) {
					//如果是tom,向客户端响应fail
		out.print("fail");
		}else {
		//如果不是tom,向客户端响应success
		out.print("success");
				}*/
		
		
		
		
		
			}
	}

