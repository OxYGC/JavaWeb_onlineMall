package yanggc.domain;

import java.util.Collection;
import java.util.HashMap;
public class Cart {
	//将购物项加入购物车
	HashMap<String, CartItem> map = new HashMap<String,CartItem>();
	private double total;
	public Collection<CartItem> getCartItems() {
		Collection<CartItem> values = map.values();
		return values;
	}
	public void addCartItem(CartItem ct) {
		//购物项的map
		String pid = ct.getPro().getPid();
		//获取到该购物项中的pid
		if(map.containsKey(pid)) {
			CartItem oldCartItem = map.get(pid);
			oldCartItem.setNum(oldCartItem.getNum()+ct.getNum());
		}else {
			map.put(pid, ct);
		}
	}
	//清空购物车
	public void clearCart() {
		map.clear();
	}
	//移除购物车上的单个购物项
	public void removeCartItem(String pid) {
		map.remove(pid);
	}
	
	
	//返回购物车上所有的购物项
	public HashMap<String, CartItem> getMap() {
		return map;
	}

	public void setMap(HashMap<String, CartItem> map) {
		this.map = map;
	}
	//购物总计
	public double getTotal() {
		total = 0;
		Collection<CartItem> values = map.values();
		for (CartItem cartItem : values) {
			total += cartItem.getSubTotal();
		}
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
