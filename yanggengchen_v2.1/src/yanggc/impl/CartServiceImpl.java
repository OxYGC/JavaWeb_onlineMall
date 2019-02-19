package yanggc.impl;

import java.util.Map;

import yanggc.dao.CartDao;
import yanggc.dao.ProductDao;
import yanggc.domain.Product;
import yanggc.service.CartService;
import yanggc.utils.BeanFactory;
import yanggc.vo.Cart;
import yanggc.vo.CartItem;

public class CartServiceImpl implements CartService {

	@Override
	public Cart addProductToCart(String pid, Integer count, Cart cart) {
		
		//CartDao cartDao = (CartDao) BeanFactory.getBean("cartDao");
		ProductDao productDao = (ProductDao) BeanFactory.getBean("productDao");
		
		try {
			//将商品放到车中
			//1、现根据id查询商品
			Product product = productDao.findById(pid);
			/*
			 * 2、将商品放到车中的细节：
			 * 	判断车中是否已经存在相同的商品
			 *  根据pid去判断
			 *  	存在：在原有的商品基础上数量+n 小计变化
			 *  	不存在：新增一个购物项  数量当前购买的数据  小计就是当前购买的小计
			 *  
			 *  	总计修改
			 */
			Map<String, CartItem> cartItems = cart.getCartItems();
			//本次购买上的小计
			Double currentSubtotal = 0.0;
			if(cartItems.containsKey(pid)){
				//包含该商品
				CartItem cartItem = cartItems.get(pid);
				//修改数量
				Integer oldCount = cartItem.getCount();
				Integer newCount = oldCount+count;
				cartItem.setCount(newCount);
				//修改小计
				Double oldSubtotal = cartItem.getSubtotal();//原来小计
				currentSubtotal = product.getShop_price()*count;//本次又购买的商品的小计
				Double newSubtotal = oldSubtotal+currentSubtotal;
				cartItem.setSubtotal(newSubtotal);
			}else{
				//不包含该商品
				//直接将商品放到车中
				CartItem item = new CartItem();
				item.setProduct(product);
				item.setCount(count);
				currentSubtotal = product.getShop_price()*count;
				item.setSubtotal(currentSubtotal);//单价*数量
				cartItems.put(pid, item);
			}
			
			//修改总计  原有总计的基础上 加上 本次新购买的商品的小计
			Double oldTotal = cart.getTotal();
			Double newTotal = oldTotal+currentSubtotal;
			cart.setTotal(newTotal);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cart;
	}

}
