package yanggc.impl;

import java.sql.SQLException;

import yanggc.dao.UserDao;
import yanggc.dao.impl.UserDaoImpl;
import yanggc.domain.User;
import yanggc.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public boolean regist(User user) {
		
		UserDao userDao = new UserDaoImpl();
		int affectRow = 0;
		try {
			affectRow = userDao.save(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectRow>0;
	}

	@Override
	public boolean active(String code) {
		UserDao userDao = new UserDaoImpl();
		int affectRow = 0;
		try {
			affectRow = userDao.active(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectRow>0;
	}

	@Override
	public User login(String username, String password) {
		UserDao userDao = new UserDaoImpl();
		User user = null;
		try {
			user = userDao.login(username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

}
