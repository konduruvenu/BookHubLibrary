package com.venu.library.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venu.library.Model.User;
import com.venu.library.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

private UserRepository userRepo;
	
    @Autowired
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
    public void registerUser(User user) {
		 userRepo.save(user);
	}
	
	public User loginUser(String email,String password) {
		User user = userRepo.findByEmailAndPassword(email,password);
		return user;
		
	}

	
	public void removeById(long id) {
		userRepo.deleteById(id);
	}

	public User findById(long id) {
		Optional<User> user=userRepo.findById(id);
	
		return user.get();
	}

	
	public User findByName(String userName) {
		User user=userRepo.findByName(userName);
		return user;
	}

	
	public List<User> getAllUsers() {
		List<User> details=userRepo.findAll();
		
		return details;
	}

	
}


