package yanggc.daoImp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import yanggc.dao.ProductDao;
import yanggc.domain.Product;
import yanggc.utils.JDBCUtils;

public class ProductDaoImp implements ProductDao{

	@Override
	public List<Product> find9Hots() throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT *FROM product WHERE is_hot=1 ORDER BY pdate DESC  LIMIT 0,9";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> find9News() throws Exception {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT *FROM product WHERE pflag=0 ORDER BY pdate DESC  LIMIT 0,9";
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	
	@Override
	public Product findProductByPid(String pid) throws Exception {
		String sql = "SELECT *FROM product  WHERE pid = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}

	@Override
	public int findTotalRecordsByCid(String cid) throws Exception {
		String sql = "SELECT COUNT(*)FROM product WHERE cid = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long numb = (Long) qr.query(sql, new ScalarHandler(),cid);
		return numb.intValue();
	}

	@Override
	public List<Product> findProductsWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql="SELECT * FROM product WHERE cid=? LIMIT ? ,  ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		 return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
	}
}
