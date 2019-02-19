package yanggc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yanggc.base.BaseServlet;
import yanggc.domain.User;
import yanggc.serviceImp.UserServiceImp;
import yanggc.utils.EmailUrlUtils;
import yanggc.utils.MailUtils;
import yanggc.utils.MyBeanUtils;
import yanggc.utils.UUIDUtils;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/jsp/login.jsp";
	}
	public String login(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
	String um = request.getParameter("username");
	String pwd = request.getParameter("password");
	UserServiceImp usi = new UserServiceImp();
	User user = usi.login(um, pwd);
	if(null != user) {
		//登录成功重定向到主页
		request.getSession().setAttribute("user", user);
		response.sendRedirect("/yanggengchen_v3.0/index.jsp");
		return null;
	}else {
		//登录不成功,给出提示
		request.setAttribute("msg", "用户名密码不匹配,或账户未激活");
		return "/jsp/login.jsp";
	}
}

	//注册功能
	public String regist(HttpServletRequest request, HttpServletResponse response) throws Exception {
	//接受用户的表单数据，将其封装到一个对象User上
	User user=MyBeanUtils.populate(User.class, request.getParameterMap());
	user.setUid(UUIDUtils.getId());
	String code = UUIDUtils.getUUID64();
	user.setCode(code);
	user.setState(0);
	System.out.println(user);
	//调用sevice用户注册功能
	UserServiceImp usi=new UserServiceImp();
	int isRegistSuccess = usi.regist(user);
	if(isRegistSuccess == 1){
		//注册成功
		//跳转之前 使用java的API进行激活邮件的发送
		String email = user.getEmail();
		String activeUrl = "http://localhost:8080/yanggengchen_v3.0/UserServlet?method=active&code="+code;
		String emailMsg = "恭喜你，注册成功，请点击下面的链接进行激活<a href='"+activeUrl+"'>"+activeUrl+"</a>";
		try {
			MailUtils.sendMail(email, emailMsg);
			System.out.println("邮件发送成功... ...");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		//跳转到注册成功的页面 提示需要账户激活
		//response.sendRedirect(request.getContextPath()+"/registSuccess.jsp");
		//判断用户是哪个邮箱产品
		String emailLoginUrl = EmailUrlUtils.getEmailLoginUrl(email);
		request.setAttribute("emailLoginUrl", emailLoginUrl);
		//request.getRequestDispatcher("/registSuccess.jsp").forward(request, response);
		request.setAttribute("msg", "恭喜你,注册成功，快去邮箱中激活吧......");
		return "/jsp/info.jsp";
		/*return "dispatcher:registSuccess";*/
	}else{
		return null;
	}
}
	
	
	
	
	//转发到个提示页面info.jsp

	//异步校验注册用户名
	public String findUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		//接受客户端的用户名
		String um=request.getParameter("um");
		UserServiceImp usi = new UserServiceImp();
		User u = usi.findUsers(um);
		if(null != u) {
			System.out.println("fail");
			out.print("fail");
		}else {
			System.out.println("success");
			out.print("success");
		}
		return null;
	}
	/*//设置响应的类型
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
				}*/
	
	//激活功能
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取用户的激活码
		String code = request.getParameter("code");
		//调用SERVICE层激活功能,返回用户对象
		UserServiceImp userService=new UserServiceImp();
		User user=userService.active(code);
		System.out.println(user);
		if(null!=user) {
			//如果用户不为空,可以激活，更改用户的状态，清空用户的激活码，向request放入提示消息,转发到登录页面
			user.setState(1);
			user.setCode("");
			userService.updateUser(user);
			request.setAttribute("msg", "用户激活成功，请登录");
			return "/jsp/login.jsp";
		}else {
			//如果用户为空,可以失败，向request放入提示消息,转发到info.jsp页面
			request.setAttribute("msg", "用户激活失败，请重新激活");
			return "/jsp/info.jsp";
		}
	}
	
	
	//退出功能
	public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.getSession().invalidate();
		/*response.sendRedirect("/yanggengchen_v3.0/login.jsp");*/
		return "/jsp/login.jsp";
	}
	
	
	
	
}