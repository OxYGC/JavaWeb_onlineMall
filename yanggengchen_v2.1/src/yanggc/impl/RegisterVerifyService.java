package yanggc.impl;

import yanggc.dao.impl.UserDaoImpl;
import yanggc.domain.User;

public class RegisterVerifyService {
	public User findUsers(String username) {
		UserDaoImpl udi = new UserDaoImpl();
		return udi.findUser(username);
	}
}
