package yanggc.service;

import yanggc.domain.User;

public interface UserService {

	public boolean regist(User user);

	public boolean active(String code);

	public User login(String username, String password);
	
}
