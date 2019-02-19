package yanggc.web;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String methodName = request.getParameter("method");
		
		//this代表：真正被调用的类的对象
		//System.out.println(this);
		
		Class currentClass = this.getClass();
		try {
			//arg0:methodName方法名称
			//arg1:可变参数 代表获得方法的参数列表的字节码对象
			Method method = currentClass.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			//执行
			String url = (String) method.invoke(this, request, response);
			
			//判断是否为空
			if(url!=null){
				//格式：redirect:name    dispatcher:name
				String resultType = url.split(":")[0];
				String resultName = url.split(":")[1];
				if("redirect".equals(resultType)){
					response.sendRedirect(request.getContextPath()+"/"+resultName+".jsp");
				}else if("dispatcher".equals(resultType)){
					request.getRequestDispatcher("/"+resultName+".jsp").forward(request, response);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}