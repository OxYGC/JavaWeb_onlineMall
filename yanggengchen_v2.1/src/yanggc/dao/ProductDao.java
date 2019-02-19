package yanggc.dao;

import java.sql.SQLException;
import java.util.List;

import yanggc.domain.Product;

public interface ProductDao {

	List<Product> findHotProductList() throws SQLException;

	List<Product> findNewProductList() throws SQLException;

	Long findTotalCount(String cid) throws SQLException;

	List<Product> findPageList(String cid, int index, int pageSize) throws SQLException;

	Product findById(String pid) throws  Exception;

}
