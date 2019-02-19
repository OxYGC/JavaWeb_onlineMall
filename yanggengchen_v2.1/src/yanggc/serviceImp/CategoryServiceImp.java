package yanggc.serviceImp;

import java.util.List;

import yanggc.daoImp.CategoryDaoImp;
import yanggc.domain.Category;
import yanggc.service.CategoryService;


public class CategoryServiceImp implements CategoryService {
	@Override
	public List<Category> findAllCats() {
		CategoryDaoImp cid = new CategoryDaoImp();
		return cid.findAllCats();
	}
}
