package yanggc.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import yanggc.domain.User;
import yanggc.impl.UserServiceImpl;
import yanggc.service.UserService;
import yanggc.utils.CommonUtils;
import yanggc.utils.EmailUrlUtils;
import yanggc.utils.MD5Utils;
import yanggc.utils.MailUtils;

public class UserServlet extends BaseServlet {
	
	//退出
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//将session销毁 还是 将session中的user干掉？
		HttpSession session = request.getSession();
		session.invalidate();
		
		/*
		 * 
		 * 但是 上方的退出方式 限于没有自动登录
		 * 如果已经设置自动登录 意味客户端存储的cookie
		 * 
		 * 点击 退出---->logout---->销毁session----->redirect:login---->客户端发送新的请求 login.jsp---->客户端存储cookie
		 * ----->访问login.jsp之前----->自动登录的filter----->获得user对象并且放到session中----> login.jsp已经登录
		 * 
		 * 解决方法：
		 * 	在此 不经要销毁session 同时还要将客户端存储的cookie 删除掉
		 * 
		 * 	1、创建同名cookie
		 *  2、cookie.setMaxAge(0);
		 *  3、cookie.setPath(与产生cookie时的name一致)
		 * 
		 */
		
		return "redirect:login";
	}
	
	
	//登录
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		//对密码进行md5加密
		//password = MD5Utils.md5(password);
		
		UserService userService = new UserServiceImpl();
		User user = userService.login(username,password);
		
		if(user!=null){
			//登录成功 将user放到session中
			request.getSession().setAttribute("user", user);
			
			return "redirect:index";
			
		}else{
			//设置错误信息 跳回登录页面
			request.setAttribute("loginInfo", "对不起用户名或密码错误或没激活");
			return "dispatcher:login";
		}
		
		
	}
	
	
	//账户激活
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得激活码
		String code = request.getParameter("code");
		UserService userService = new UserServiceImpl();
		boolean isActiveSuccess = userService.active(code);
		
		if(isActiveSuccess){
			//激活成功 跳转登录页面
			//response.sendRedirect(request.getContextPath()+"/login.jsp");
			return "redirect:login";
		}
		
		return null;
		
	}
	//注册功能
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//收集数据 封装实体
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		//user封装不完全 
		//uid主键id  uuid
		String uid = CommonUtils.getUUID();
		user.setUid(uid);
		//state激活状态
		user.setState(0);
		//code激活码   uuid
		String code = CommonUtils.getUUID();
		user.setCode(code);
		//对密码进行md5加密
		/*user.setPassword(MD5Utils.md5(user.getPassword()));*/
		
		//将user传递到service层
		UserService userService = new UserServiceImpl();
		boolean isRegistSuccess = userService.regist(user);
		
		if(isRegistSuccess){
			//注册成功
			//跳转之前 使用java的API进行激活邮件的发送
			String email = user.getEmail();
			String activeUrl = "http://localhost:8080/yanggengchen_v1.0/user?method=active&code="+code;
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
			return "dispatcher:registSuccess";
		}else{
			return null;
		}
		
		
		
		
	}
}