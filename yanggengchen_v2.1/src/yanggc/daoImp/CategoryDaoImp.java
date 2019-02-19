package yanggc.daoImp;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import yanggc.dao.CategoryDao;
import yanggc.domain.Category;
import yanggc.utils.JDBCUtils;

public class CategoryDaoImp implements CategoryDao {
	@Override
	public List<Category> findAllCats() {
		String sql = "SELECT *FROM category";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		List<Category> list = null;
		try {
			list = qr.query(sql, new BeanListHandler<Category>(Category.class));
			for (Category category : list) {
				System.out.println(category.getCname());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
