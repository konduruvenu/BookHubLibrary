package com.venu.library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venu.library.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmailAndPassword(String email,String password);
	
	//List<User> findAllByEmail(String email);
	
	User findByName(String userName);

}
