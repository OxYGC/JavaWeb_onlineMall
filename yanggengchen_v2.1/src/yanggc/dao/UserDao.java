package yanggc.dao;

import java.sql.SQLException;

import yanggc.domain.User;

public interface UserDao {
	User login (String username,String password)throws SQLException;
	int register(User user) throws Exception;
	User active(String code) throws Exception;
	void updateUser(User user) throws Exception;
	User findUser(String username) throws Exception;
}
