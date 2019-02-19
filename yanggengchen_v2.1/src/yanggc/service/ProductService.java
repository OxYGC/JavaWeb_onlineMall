package yanggc.service;

import java.util.List;

import yanggc.domain.Product;
import yanggc.vo.PageBean;

public interface ProductService {

	List<Product> findHotProductList();

	List<Product> findNewProductList();

	PageBean<Product> page(String cid, int pageNum, int pageSize);

	Product findById(String pid);

}
