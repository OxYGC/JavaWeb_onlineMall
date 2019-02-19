package yanggc.dao;

import java.util.List;

import yanggc.domain.OrderItem;
import yanggc.domain.Orders;
import yanggc.domain.User;

public interface OrderDao {
	void saveOrder(Orders order)throws Exception;

	void saveOrderItem(OrderItem item)throws Exception;

	Orders findOrderByOid(String oid)throws Exception;

	int findOrdersByPerson(User user)throws Exception;

	List<Orders> findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception;
	
	void updateOrder(Orders order)throws Exception;
}
