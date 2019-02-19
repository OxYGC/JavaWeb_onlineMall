package yanggc.dao;

import java.sql.SQLException;
import java.util.List;

import yanggc.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws SQLException;

}
