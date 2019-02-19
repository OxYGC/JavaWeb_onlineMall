package yanggc.service;

import java.util.List;

import yanggc.domain.Order;
import yanggc.domain.OrderItem;

public interface OrderService {

	void submitOrder(Order order);

	List<Order> findOrderByUid(String uid);

	List<OrderItem> findOrderItemByOid(String oid);

	Order findOrderByOid(String oid);

	boolean updateOrderInfo(Order order);

	void updateState(String r6_Order);

}
