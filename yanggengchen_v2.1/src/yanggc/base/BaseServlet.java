package yanggc.base;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*doGet(request, response);*/
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		response.setContentType("application/json;charset=utf-8"); 	
		if (null == method || "".equals(method) || method.trim().equals("")) {
			method = "execute";
		}

		// 注意:此处的this代表的是子类的对象
		// System.out.println(this);
		// 子类对象字节码对象
		Class clazz = this.getClass();
		try {
			// 查找子类对象对应的字节码中的名称为method的方法.这个方法的参数类型是:HttpServletRequest.class,HttpServletResponse.class
			Method md = clazz.getMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			if (null != md) {
				String jspPath = (String) md.invoke(this, request, response);
				if (null != jspPath) {
					request.getRequestDispatcher(jspPath).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	// 默认方法
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return null;
	}
}