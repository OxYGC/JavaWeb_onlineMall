package yanggc.service;

import java.util.List;

import yanggc.domain.Product;
import yanggc.utils.PageModel;

public interface ProductService {
	public List<Product>  find9News() throws Exception;
	public List<Product>  find9Hots() throws Exception;
	public Product findProductByPid(String Pid) throws Exception;
	public PageModel findProductsWithPage(String cid, int num) throws Exception;
}
