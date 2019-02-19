package yanggc.dao;

import java.util.List;

import yanggc.domain.Category;

public interface CategoryDao {
	public List<Category> findAllCats();
}
