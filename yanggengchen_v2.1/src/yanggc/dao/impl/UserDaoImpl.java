package yanggc.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import yanggc.dao.UserDao;
import yanggc.domain.User;
import yanggc.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public int save(User user) throws SQLException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int update = runner.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),
				user.getSex(),user.getState(),user.getCode());
		
		return update;
	}

	@Override
	public int active(String code) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set state =? where code=?";
		int update = runner.update(sql, 1,code);
		
		return update;
	}

	@Override
	public User login(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username=? and password=? and state=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), username,password,1);
		return user;
	}
	
	public User findUser(String username) {
		String sql = "SELECT * FROM USER WHERE username = ?";
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}
