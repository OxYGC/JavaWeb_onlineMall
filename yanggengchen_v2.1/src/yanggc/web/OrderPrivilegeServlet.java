package yanggc.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import yanggc.domain.Order;
import yanggc.domain.OrderItem;
import yanggc.domain.User;
import yanggc.service.OrderService;
import yanggc.utils.BeanFactory;
import yanggc.utils.CommonUtils;
import yanggc.vo.Cart;
import yanggc.vo.CartItem;

public class OrderPrivilegeServlet extends BaseServlet {

	//查看我的订单
	public String myOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//2、根据当前用户的uid去查询该用户的订单信息  查询订单  订单项   商品
		//2.1 先去查询当前用户的所有的订单  List<Order>  注意 每一个Order对象都没有订单项数据
		OrderService orderService = (OrderService) BeanFactory.getBean("orderService");
		List<Order> orderList = orderService.findOrderByUid(user.getUid());//单表查询  查询orders表
		//2.2当前集合中的每一个Order少 orderItems  是一个存储订单项的集合List<OrderItem>
		for(Order order:orderList){
			//获得oid
			String oid = order.getOid();
			//根据oid查询该订单的所有的订单项 List<OrderItem>
			List<OrderItem> orderItems = orderService.findOrderItemByOid(oid);
			//将查询到的该orderitems封装到order中
			order.setOrderItems(orderItems);
		}

		//List<Order> orderList中的内一个Order对象都已经封装好orderItems
		request.setAttribute("orderList", orderList);


		return "dispatcher:order_list";
	}


	public String submitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		//2、订单提交  将购物车的数据转移到数据库中
		//2.1 封装Order对象---大部分数据存在域cart中
		//取出session中的cart
		Cart cart = (Cart) session.getAttribute("cart");

		Order order = new Order();
		//1-private String oid;//订单编号 主键
		String oid = CommonUtils.getUUID();
		order.setOid(oid);
		//2-private String ordertime;//下单时间
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		order.setOrdertime(format.format(new Date()));
		//3-private Double total;//订单总金额
		order.setTotal(cart.getTotal());
		//4-private Integer state;//订单状态  1代表已付款  0代表未付款
		order.setState(0);
		//5-private String address;//收货人的地址
		//6-private String name;//收货人的姓名
		//7-private String telephone;//收货人的电话

		//8-private User user;//该订单属于哪个用户
		order.setUser(user);
		//9-private List<OrderItem> orderItems//该订单中包括哪些订单项
		//订单项集合 与 购物车项集合是对应的  映射封装
		Map<String, CartItem> cartItems = cart.getCartItems();
		for(Map.Entry<String, CartItem> entry:cartItems.entrySet()){
			//取出CartItem
			CartItem cartItem = entry.getValue();
			//CartItem就相当于 OrderItem
			OrderItem orderItem = new OrderItem();
			//1-private String itemid;//订单项主键
			orderItem.setItemid(CommonUtils.getUUID());
			//2-private Integer count;//商品数量
			orderItem.setCount(cartItem.getCount());
			//3-private Double subtotal;//订单项小计
			orderItem.setSubtotal(cartItem.getSubtotal());
			//4-private Product product;//该订单项内部是哪个商品
			orderItem.setProduct(cartItem.getProduct());
			//5-private Order order;//该订单项属于哪个订单
			orderItem.setOrder(order);
			//将orderItem放到Order对象的订单项集合中orderItems中
			order.getOrderItems().add(orderItem);
		}

		//Order封装完毕

		//2.2将Order对象传递给service层
		OrderService orderService = (OrderService) BeanFactory.getBean("orderService");
		orderService.submitOrder(order);

		//3、清空购物车
		session.removeAttribute("cart");

		//4、将订单放到session中
		session.setAttribute("order", order);

		return "redirect:order_info";
	}
}