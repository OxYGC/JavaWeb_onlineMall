package yanggc.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import yanggc.domain.Order;
import yanggc.domain.OrderItem;

public interface OrderDao {

	void saveOrders(Order order) throws SQLException;

	void saveOrderItem(OrderItem item) throws SQLException;

	List<Order> findOrderByUid(String uid) throws SQLException;

	List<OrderItem> findOrderItemByOid(String oid) throws SQLException, IllegalAccessException, InvocationTargetException;

	Order findOrderByOid(String oid) throws SQLException, IllegalAccessException, InvocationTargetException;

	int updateOrderInfo(Order order) throws SQLException;

	void updateState(String oid) throws SQLException;

}
