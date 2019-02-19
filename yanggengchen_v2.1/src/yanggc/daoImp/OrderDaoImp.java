package yanggc.daoImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import yanggc.dao.OrderDao;
import yanggc.domain.OrderItem;
import yanggc.domain.Orders;
import yanggc.domain.Product;
import yanggc.domain.User;
import yanggc.utils.JDBCUtils;

public class OrderDaoImp implements OrderDao{

	@Override
	public void saveOrder(Orders order) throws Exception {
		String sql="INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)";
		Object[] params= {order.getOid(),order.getOrdertime(),order.getTotal(),order.getState()
				,order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()};
		QueryRunner qr=new QueryRunner();
		qr.update(JDBCUtils.getConnection(),sql,params);
		
	}

	@Override
	public void saveOrderItem(OrderItem item) throws Exception {
		String sql="INSERT INTO orderitem VALUES (?,?,?,?,?)";
		Object[] params= {item.getItemid(),item.getQuantity(),item.getTotal(),
				item.getProduct().getPid(),item.getOrders().getOid()		
		};
		QueryRunner qr=new QueryRunner();
		qr.update(JDBCUtils.getConnection(),sql,params);	
	}

	@Override
	//通过订单id查询订单
	public Orders findOrderByOid(String oid) throws Exception {
		String sql="select * from orders where oid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		/*Orders orders = new Orders();
		orders.getOid();*/
		//Order对象上设置集合优势?
		Orders order = qr.query(sql, new BeanHandler<Orders>(Orders.class),oid);
		List<OrderItem> list = getOrderItemsByOid(order.getOid());
		order.setList(list);
		return order;
	}
	
	//通过订单id查询该id下所有的订单项(包含product对象)
	public List<OrderItem> getOrderItemsByOid(String oid) throws Exception{
		ArrayList<OrderItem> listItem = new ArrayList<OrderItem>();
		
		String sql ="SELECT * FROM orderitem AS item, product AS p WHERE item.pid=p.pid AND oid = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Map<String, Object>> list = qr.query(sql,new MapListHandler() ,oid);
		
		for (Map<String, Object> map : list) {
			OrderItem orderItem = new OrderItem();
			Product product = new Product();
			BeanUtils.populate(orderItem, map);
			BeanUtils.populate(product, map);
			
			orderItem.setProduct(product);
			listItem.add(orderItem);
		}
		
		return listItem;
	}
	
	
	@Override
	public int findOrdersByPerson(User user) throws Exception {
		String sql="select count(*) from orders where uid=?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler(),user.getUid());
		return num.intValue();
	}

	@Override
	public List<Orders> findMyOrdersWithPage(User user, int startIndex, int pageSize) throws Exception {
		String sql="SELECT * FROM orders WHERE uid=? LIMIT ? , ? ";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		List<Orders> list = qr.query(sql, new BeanListHandler<Orders>(Orders.class),user.getUid(),startIndex,pageSize);
		for (Orders order : list) {
			List<OrderItem> list02=getOrderItemsByOid(order.getOid());
			order.setList(list02);
		}
		return list;
	}

	
	@Override
	public void updateOrder(Orders order) throws Exception {
		String sql="UPDATE orders SET ordertime=? , total =? , state =?  , address =? , NAME = ? , telephone = ? WHERE oid=?";
		Object[] params= {order.getOrdertime(),order.getTotal(),order.getState()
				,order.getAddress(),order.getName(),order.getTelephone(),order.getOid()};
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,params);
	}
	
	
	
}
