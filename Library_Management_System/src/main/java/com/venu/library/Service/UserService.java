package com.venu.library.Service;

import java.util.List;

import com.venu.library.Model.User;

public interface UserService {

	void registerUser(User user);

	User loginUser(String email, String password);
	
	void removeById(long id);
	
	User findById(long id);
	
	User findByName(String userName);
	
	List<User> getAllUsers();

}
