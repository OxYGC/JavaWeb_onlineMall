package yanggc.impl;

import java.sql.SQLException;
import java.util.List;

import yanggc.dao.CategoryDao;
import yanggc.dao.impl.CategoryDaoImpl;
import yanggc.domain.Category;
import yanggc.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> findAll() {
		CategoryDao categoryDao = new CategoryDaoImpl();
		List<Category> categoryList = null;
		try {
			categoryList = categoryDao.findAll();//dao层数据库查询
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}

}
