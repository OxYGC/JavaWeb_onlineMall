package yanggc.vo;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private Map<String,CartItem> cartItems = new HashMap<String,CartItem>();
	private Double total = 0.0;
	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}
	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	
	
	
	
}
