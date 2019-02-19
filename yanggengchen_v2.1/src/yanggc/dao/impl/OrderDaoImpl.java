package yanggc.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import yanggc.dao.OrderDao;
import yanggc.domain.Order;
import yanggc.domain.OrderItem;
import yanggc.domain.Product;
import yanggc.utils.DataSourceUtils;

public class OrderDaoImpl implements OrderDao {

	@Override
	public void saveOrders(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		//获得线程绑定conn 在service层中开启事务的conn
		Connection conn = DataSourceUtils.getConnection();
		runner.update(conn,sql, order.getOid(),order.getOrdertime(),order.getTotal(),
				order.getState(),order.getAddress(),order.getName(),order.getTelephone(),
				order.getUser().getUid());
	}

	@Override
	public void saveOrderItem(OrderItem item) throws SQLException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orderitem values(?,?,?,?,?)";
		//获得线程绑定conn 在service层中开启事务的conn
		Connection conn = DataSourceUtils.getConnection();
		runner.update(conn,sql, item.getItemid(),item.getCount(),item.getSubtotal(),
				item.getProduct().getPid(),item.getOrder().getOid());
	}

	@Override
	public List<Order> findOrderByUid(String uid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where uid=?";
		List<Order> orderList = runner.query(sql, new BeanListHandler<Order>(Order.class), uid);
		return orderList;
	}

	@Override
	public List<OrderItem> findOrderItemByOid(String oid) throws SQLException, IllegalAccessException, InvocationTargetException {
		
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		// 多表查询  查询orderitem和product
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orderitem i,product p where i.pid=p.pid and i.oid=?";
		List<Map<String, Object>> mapList = runner.query(sql, new MapListHandler(), oid);
		//一个Map内部封装的既有orderittem的数据也有product的数据
		for(Map<String, Object> map:mapList){
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
			BeanUtils.populate(orderItem, map);
			BeanUtils.populate(product, map);
			
			orderItem.setProduct(product);
			
			//一个OrderItem封装完毕
			orderItems.add(orderItem);
		}
		
		
		return orderItems;
	}

	@Override
	public Order findOrderByOid(String oid) throws SQLException, IllegalAccessException, InvocationTargetException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders o,orderitem i,product p where o.oid=i.oid and i.pid=p.pid and o.oid=?";
		List<Map<String, Object>> mapList = runner.query(sql, new MapListHandler(), oid);
		Order order = new Order();
		BeanUtils.populate(order, mapList.get(0));
		for(Map<String, Object> map:mapList){
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
			BeanUtils.populate(orderItem, map);
			BeanUtils.populate(product, map);
			
			orderItem.setProduct(product);
			
			order.getOrderItems().add(orderItem);
			
		}
		return order;
	}

	@Override
	public int updateOrderInfo(Order order) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set address=?,name=?,telephone=? where oid=?";
		int update = runner.update(sql, order.getAddress(),order.getName(),order.getTelephone(),order.getOid());
		return update;
	}

	@Override
	public void updateState(String oid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set state=? where oid=?";
		runner.update(sql, 1,oid);
	}

}
