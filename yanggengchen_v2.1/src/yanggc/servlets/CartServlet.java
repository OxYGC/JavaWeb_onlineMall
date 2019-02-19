package yanggc.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import yanggc.base.BaseServlet;
import yanggc.domain.Cart;
import yanggc.domain.CartItem;
import yanggc.domain.Product;
import yanggc.domain.User;
import yanggc.serviceImp.ProductServiceImp;

public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	
	public Cart getCart(HttpServletRequest request) {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if(null == cart) {
			cart=new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		return cart;
	}
	
	public String addCartItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = (User) request.getSession().getAttribute("user");
		if(null == user) {
			request.setAttribute("msg", "请登录之后再添加购物车");
			request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
			return null;
		}
		String pid = request.getParameter("pid");
		int num = Integer.parseInt(request.getParameter("quantity"));
		ProductServiceImp psi = new ProductServiceImp();
		Product product = psi.findProductByPid(pid);
		CartItem ct = new CartItem();
		ct.setNum(num);
		ct.setPro(product);
		ct.setSubTotal(num);
		Cart cart = getCart(request);
		cart.addCartItem(ct);
		request.getRequestDispatcher("jsp/cart.jsp").forward(request, response);
		return null;
	}
	public String clearCart(HttpServletRequest request, HttpServletResponse response) {
		Cart cart = getCart(request);
		cart.clearCart();
		return "/jsp/cart.jsp";
	}
	
	
	public String removeCartItem(HttpServletRequest request, HttpServletResponse response) {
		String pid = request.getParameter("pid");
		Cart cart = getCart(request);
		cart.removeCartItem(pid);
		return "/jsp/cart.jsp";
	}
}