package yanggc.dao;

import java.util.List;

import yanggc.domain.Product;

public interface ProductDao {
	List<Product> find9Hots()throws Exception;

	List<Product> find9News()throws Exception;

	Product findProductByPid(String pid)throws Exception;

	int findTotalRecordsByCid(String cid)throws Exception;

	List<Product> findProductsWithPage(String cid, int startIndex, int pageSize)throws Exception;
}
