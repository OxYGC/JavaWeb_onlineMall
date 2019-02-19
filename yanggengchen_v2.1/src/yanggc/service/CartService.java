package yanggc.service;

import yanggc.vo.Cart;

public interface CartService {

	Cart addProductToCart(String pid,Integer count, Cart cart);

}
