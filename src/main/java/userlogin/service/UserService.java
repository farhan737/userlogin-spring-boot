package userlogin.service;

import userlogin.model.User;

public interface UserService {
	public boolean register(User user);
	
	public boolean isAuthorised(User user);
}
