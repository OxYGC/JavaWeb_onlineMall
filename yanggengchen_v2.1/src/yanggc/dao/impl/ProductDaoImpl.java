package yanggc.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import yanggc.dao.ProductDao;
import yanggc.domain.Category;
import yanggc.domain.Product;
import yanggc.utils.DataSourceUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findHotProductList() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where is_hot=? limit 0,9";
		List<Product> query = runner.query(sql, new BeanListHandler<Product>(Product.class), 1);
		return query;
	}

	@Override
	public List<Product> findNewProductList() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product order by pdate desc limit 0,9";
		List<Product> query = runner.query(sql, new BeanListHandler<Product>(Product.class));
		return query;
	}

	@Override
	public Long findTotalCount(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from product where cid=?";
		Long totalCount = (Long) runner.query(sql, new ScalarHandler(), cid);
		return totalCount;
		
	}

	@Override
	public List<Product> findPageList(String cid, int index, int pageSize) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where cid=? limit ?,?";
		List<Product> pageList = runner.query(sql, new BeanListHandler<Product>(Product.class),cid,index,pageSize);
		return pageList;
	}

	@Override
	public Product findById(String pid) throws Exception {
		//不仅查询Product对象  该对象内部封装Category也有数据
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product p,category c where c.cid=p.cid and p.pid=?";
		Map<String, Object> map = runner.query(sql, new MapHandler(), pid);
		//使用BeanUtils映射封装实体数据
		Product product = new Product();
		Category category = new Category();
		BeanUtils.populate(product, map);
		BeanUtils.populate(category, map);
		
		product.setCategory(category);
		
		return product;
		
		
		//仅仅查询Product对象  该对象内部封装Category没有数据
		/*QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from product where pid=?";
		Product product = runner.query(sql, new BeanHandler<Product>(Product.class), pid);
		return product;*/
	}

}
