package yanggc.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import yanggc.dao.UserDao;
import yanggc.domain.User;
import yanggc.utils.JDBCUtils;

public class UserDaoImp implements UserDao{

	@Override
	public User login(String username, String password) throws SQLException {
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "SELECT * FROM USER WHERE username = ? AND PASSWORD = ?";
		return qr.query(sql, new BeanHandler<User>(User.class), username, password);
	}

	@Override
	public int register(User user) throws Exception {
		String sql = "INSERT INTO `user` VALUES (?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = { user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode() };
		return qr.update(sql, params);
	}

	@Override
	public User active(String code) throws Exception {
		String sql = "select * from user where code=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class), code);
	}

	@Override
	public User findUser(String username) throws Exception {
		String sql = "SELECT * FROM USER WHERE username = ?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		User user = null;
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class),username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	@Override
	public void updateUser(User user) throws Exception {
		String sql = "UPDATE USER SET username =? , PASSWORD = ? , NAME =? ,email =? , telephone =? ,birthday =? ,sex = ? , state= ?  , CODE =? WHERE uid=?";
		Object[] params = { user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode(),
				user.getUid() };
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql, params);
	}
}
