package com.venu.library.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.venu.library.Model.User;
import com.venu.library.Repository.UserRepository;
import com.venu.library.Service.UserServiceImpl;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private UserRepository mockUserRepo;
	
	private UserServiceImpl userService;
	
	@BeforeEach
	void setUp() throws Exception {
		userService = new UserServiceImpl(mockUserRepo);
	}
	
	@Test
	void saveUser_test() {
		User user = new User();
		userService.registerUser(user);
		verify(mockUserRepo,times(1)).save(user);
	}
	
	@Test
	void checkLogin_test() {
		User user = new User();
		String testUserEmail = "lekha@gmail.com";
		String testPassword = "1234";
		when(mockUserRepo.findByEmailAndPassword(testUserEmail, testPassword)).thenReturn(user);
		User testUser = userService.loginUser(testUserEmail, testPassword);
		verify(mockUserRepo).findByEmailAndPassword(testUserEmail, testPassword);
		assertEquals(user, testUser);
	}
	
	@Test
	void removeById_test() {
		long id = 1L;
		userService.removeById(id);
		verify(mockUserRepo).deleteById(id);
	}
	
	@Test
	void findByName_test() {
		User user = new User();
		String testUserName = "LekhaPriya";
		when(mockUserRepo.findByName(testUserName)).thenReturn(user);
		User testUser = userService.findByName(testUserName);
		verify(mockUserRepo).findByName(testUserName);
		assertEquals(user, testUser);
	}
	
	@Test
	void findUserById_test() {
		User user = new User();
		when(mockUserRepo.findById(1L)).thenReturn(Optional.of(user));
		User testUser = userService.findById(1L);
		verify(mockUserRepo, times(1)).findById((1L));
		assertEquals(user, testUser);
	}
	


}
