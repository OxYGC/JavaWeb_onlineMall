package yanggc.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import yanggc.dao.CategoryDao;
import yanggc.domain.Category;
import yanggc.domain.Product;
import yanggc.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		List<Category> query = runner.query(sql, new BeanListHandler<Category>(Category.class));
		return query;
	}

}
