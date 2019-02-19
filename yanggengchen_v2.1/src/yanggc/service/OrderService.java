package yanggc.service;

import yanggc.domain.Orders;
import yanggc.domain.User;
import yanggc.utils.PageModel;

public interface OrderService {
	void saveOrder(Orders orders);

	Orders findOrderByOid(String oid)throws Exception;

	PageModel findMyOrdersWithPage(User user, int num)throws Exception;
	
	void updateOrder(Orders order)throws Exception;
}
