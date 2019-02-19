package yanggc.impl;

import java.sql.SQLException;
import java.util.List;

import yanggc.dao.OrderDao;
import yanggc.domain.Order;
import yanggc.domain.OrderItem;
import yanggc.service.OrderService;
import yanggc.utils.BeanFactory;
import yanggc.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	public void submitOrder(Order order) {

		OrderDao orderDao = (OrderDao) BeanFactory.getBean("orderDao");

		try {
			//开启事务
			DataSourceUtils.startTransaction();

			//向orders表中插入一条数据
			orderDao.saveOrders(order);//发送一条sql
			//向orderitem表中插入n条数据
			for(OrderItem item:order.getOrderItems()){
				orderDao.saveOrderItem(item);//发送一条sql
			}
		} catch (SQLException e) {
			//回滚事务
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			//提交事务
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}


	}

	@Override
	public List<Order> findOrderByUid(String uid) {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("orderDao");
		List<Order> orderList = null;
		try {
			orderList = orderDao.findOrderByUid(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	@Override
	public List<OrderItem> findOrderItemByOid(String oid) {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("orderDao");
		List<OrderItem> orderItems = null;
		try {
			orderItems = orderDao.findOrderItemByOid(oid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderItems;
	}

	@Override
	public Order findOrderByOid(String oid) {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("orderDao");
		Order order = null;
		try {
			order = orderDao.findOrderByOid(oid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public boolean updateOrderInfo(Order order) {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("orderDao");
		int affectRow = 0;
		try {
			affectRow = orderDao.updateOrderInfo(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return affectRow>0;
	}

	@Override
	public void updateState(String oid) {
		OrderDao orderDao = (OrderDao) BeanFactory.getBean("orderDao");
		int affectRow = 0;
		try {
			orderDao.updateState(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
