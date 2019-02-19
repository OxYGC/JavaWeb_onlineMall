package yanggc.service;

import java.sql.SQLException;

import yanggc.domain.User;

public interface UserService {
	public User login(String um,String pwd) throws SQLException;
	int regist(User user) throws Exception;
	User active(String code)  throws Exception;
	void updateUser(User user) throws Exception;
	public User findUsers(String username) throws Exception;
}
