package yanggc.dao;

import java.sql.SQLException;

import yanggc.domain.User;

public interface UserDao {

	int save(User user) throws SQLException;

	int active(String code) throws SQLException;

	User login(String username, String password) throws SQLException;

}
