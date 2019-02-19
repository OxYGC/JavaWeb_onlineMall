package yanggc.serviceImp;

import java.util.List;

import yanggc.daoImp.ProductDaoImp;
import yanggc.domain.Product;
import yanggc.service.ProductService;
import yanggc.utils.PageModel;

public class ProductServiceImp implements ProductService{
	@Override
	public List<Product> find9News() throws Exception {
		ProductDaoImp pdi = new ProductDaoImp();
		return pdi.find9News();
	}
	@Override
	public List<Product> find9Hots() throws Exception {
		ProductDaoImp pdi = new ProductDaoImp();
		return pdi.find9Hots();
	}
	@Override
	public Product findProductByPid(String Pid) throws Exception {
		ProductDaoImp pdi = new ProductDaoImp();
		return pdi.findProductByPid(Pid);
	}
	@Override
	public PageModel findProductsWithPage(String cid, int num) throws Exception {
		ProductDaoImp pdi = new ProductDaoImp();
		int totalRecords = pdi.findTotalRecordsByCid(cid);
		PageModel pm = new PageModel(num, totalRecords, 12);
		List list = pdi.findProductsWithPage(cid, pm.getCurrentPageNum(), pm.getPageSize());
		pm.setList(list);
		pm.setUrl("ProductServlet?method=findProductsWithPage&cid="+cid);
		return pm;
	}
}
