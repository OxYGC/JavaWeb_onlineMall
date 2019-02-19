package yanggc.serviceImp;

import java.util.List;

import yanggc.dao.OrderDao;
import yanggc.daoImp.OrderDaoImp;
import yanggc.domain.OrderItem;
import yanggc.domain.Orders;
import yanggc.domain.User;
import yanggc.service.OrderService;
import yanggc.utils.JDBCUtils;
import yanggc.utils.PageModel;

public class OrderServiceImp implements OrderService {
private OrderDao orderDao = new OrderDaoImp();
	@Override
	public void saveOrder(Orders orders) {
		try {
			//开启事务
			JDBCUtils.startTransaction();
			//保存订单
			orderDao.saveOrder(orders);
			//保存订单项
			for (OrderItem item : orders.getList()) {
				orderDao.saveOrderItem(item);
			}
			//提交事务
			JDBCUtils.commitAndClose();
		} catch (Exception e) {
			//回滚事务	
			JDBCUtils.rollbackAndClose();
			e.printStackTrace();
		}
	}
	@Override
	public Orders findOrderByOid(String oid) throws Exception {
		return orderDao.findOrderByOid(oid);
	}
	@Override
	public PageModel findMyOrdersWithPage(User user, int num) throws Exception {
		int totalRecords=orderDao.findOrdersByPerson(user);
		PageModel pm=new PageModel(num, totalRecords, 5);
		//2_为PageModel关联集合(当前用户的第1页的订单)  select * from orders where uid= ? limit ? , ?
		List<Orders> list=orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_为PageModel关联url
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}
	@Override
	public void updateOrder(Orders order) throws Exception {
		orderDao.updateOrder(order);
	}
	
	
	
	
}
