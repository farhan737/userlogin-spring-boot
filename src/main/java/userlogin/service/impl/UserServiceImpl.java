package userlogin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import userlogin.model.User;
import userlogin.repo.UserRepository;
import userlogin.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repo;

	@Override
	public boolean register(User user) {
		if (repo.existsByEmail(user.getEmail()))
			return false;
		repo.save(user);
		return true;
	}

	@Override
	public boolean isAuthorised(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
