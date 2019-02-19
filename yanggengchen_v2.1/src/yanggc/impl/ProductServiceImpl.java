package yanggc.impl;

import java.sql.SQLException;
import java.util.List;

import yanggc.dao.ProductDao;
import yanggc.dao.impl.ProductDaoImpl;
import yanggc.domain.Product;
import yanggc.service.ProductService;
import yanggc.utils.BeanFactory;
import yanggc.vo.PageBean;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findHotProductList() {
		ProductDao productDao = new ProductDaoImpl();
		List<Product> hotProductList = null;
		try {
			hotProductList = productDao.findHotProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProductList;
	}

	@Override
	public List<Product> findNewProductList() {
		ProductDao productDao = new ProductDaoImpl();
		List<Product> newProductList = null;
		try {
			newProductList = productDao.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newProductList;
	}

	@Override
	public PageBean<Product> page(String cid, int pageNum, int pageSize) {
		
		ProductDao productDao = new ProductDaoImpl();
		//封装好一个PageBean对象
		PageBean<Product> pageBean = new PageBean<>();
		
		try {
			//1-private Integer pageNum;//当前页
			pageBean.setPageNum(pageNum);
			//2-private Integer pageSize;//当前页显示条数
			pageBean.setPageSize(pageSize);
			//3-private Long totalCount;
			Long totalCount = productDao.findTotalCount(cid);
			pageBean.setTotalCount(totalCount);
			//4-private Integer totalPage;
			//公式：totalPage=(总条数/每页显示条数)向上取整
			Integer totalPage = (int) Math.ceil(1.0*totalCount/pageSize);
			pageBean.setTotalPage(totalPage);
			//5-private List<T> pageList;
			//将当前页码转换成起始索引 公式：起始索引=(当前页-1)*每页显示条数
			int index = (pageNum-1)*pageSize;
			List<Product> pageList = productDao.findPageList(cid,index,pageSize);
			pageBean.setPageList(pageList);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return pageBean;
	}

	@Override
	public Product findById(String pid) {
		ProductDao productDao = (ProductDao) BeanFactory.getBean("productDao");
		Product product = null;
		try {
			product = productDao.findById(pid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product;
	}

}
