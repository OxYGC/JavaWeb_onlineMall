package yanggc.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yanggc.service.CartService;
import yanggc.utils.BeanFactory;
import yanggc.vo.Cart;
import yanggc.vo.CartItem;

public class CartServlet extends BaseServlet {

	public String clearCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("cart");
		
		return "redirect:cart";
	}
	
	
	public String delCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("pid");
		//获得购物车
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		//被删除的项
		CartItem cartItem = cart.getCartItems().remove(pid);
		//从总计中将删除的该向的小计减去
		cart.setTotal(cart.getTotal()-cartItem.getSubtotal());
		
		return "redirect:cart";
	}
	
	
	public String addProductToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//目标：将当前商品放到购物车中
		//获得商品的pid
		String pid = request.getParameter("pid");
		//获得该商品的购买数量
		Integer count = Integer.parseInt(request.getParameter("count"));
		//将该pid的商品放到购物车中 
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		//判断车是否存在
		if(cart==null){
			cart = new Cart();
			//将车放到session中
			session.setAttribute("cart", cart);
		}
		
		//将商品放到购物车中的业务操作
		CartService cartService = (CartService) BeanFactory.getBean("cartService");
		cart = cartService.addProductToCart(pid,count,cart);
		
		//放到session中
		session.setAttribute("cart", cart);
		
		return "redirect:cart";
	}

}
