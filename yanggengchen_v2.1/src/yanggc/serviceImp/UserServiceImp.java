package yanggc.serviceImp;

import java.sql.SQLException;

import yanggc.daoImp.UserDaoImp;
import yanggc.domain.User;
import yanggc.service.UserService;
import yanggc.utils.MailUtils;

public class UserServiceImp implements UserService {

	@Override
	public User login(String um, String pwd) throws SQLException {
		 User user = new UserDaoImp().login(um, pwd);
		 return user;
	}

	@Override
	public int regist(User user) throws Exception {
		UserDaoImp userDao=new UserDaoImp();
		MailUtils.sendMail(user.getEmail(), user.getCode());
		return userDao.register(user);
		
	}

	@Override
	public User active(String code) throws Exception {
		UserDaoImp userDao=new UserDaoImp();
		return userDao.active(code);
	}
	
	@Override
	public void updateUser(User user) throws Exception {
		UserDaoImp userDao=new UserDaoImp();
		userDao.updateUser(user);
	}

	@Override
	public User findUsers(String username) throws Exception {
		UserDaoImp udi = new UserDaoImp();
		return udi.findUser(username);
	}
	
	
}
