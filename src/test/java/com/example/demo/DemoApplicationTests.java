package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserServiceImpl;

@SpringBootTest
class DemoApplicationTests {

	// @Test
	// void contextLoads() {
	// }
	@Mock
	private UserRepository userRepository;
	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	public void getAllUsers() {
		List<User> expectedResult=Arrays.asList(new User("1","Sai",25,null),
		new User("2","Charan",15,null));
		when(userRepository.findAll()).thenReturn(expectedResult);
		List<User> actualResult=userServiceImpl.getAllUsers();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void getUserById(){
		User user=new User("1","Sai",10,null);
		when(userRepository.findById("1")).thenReturn(Optional.of(user));
		User actualResult=userServiceImpl.getUserById("1");
		assertEquals(user, actualResult);
	}
@Test
	public void createUser(){
		User user=new User("1","Sai",10,null);
		when(userRepository.save(user)).thenReturn(user);
		User actualUser=userServiceImpl.addUser(user);
		assertEquals(user, actualUser);
	}
	@Test
	public void deleteUser(){
		User user=new User("1","Sai",10,null);
		// when(userRepository.delete(user)).thenReturn(null);
		userServiceImpl.deleteUser("1");
		verify(userRepository,times(1)).deleteById("1");
	}

	@Test
	public void updateScore(){
		User user = new User("1", "Sai", 10, null);
		when(userRepository.findById("1")).thenReturn(Optional.of(user));
		when(userRepository.save(user)).thenReturn(user);
		User updatedUser = userServiceImpl.updateUserScore("1", 10);
		assertEquals(10, updatedUser.getScore());
		assertTrue(updatedUser.getBadges().contains("Code Ninja"));
	}
	@Test
	public void sortedListOfUsers(){
		User user1=new User("1","Sai",10,null);
		User user2=new User("2","Charan",25,null);
		List<User> users=Arrays.asList(user1,user2);
		List<User> expectedSortedUsers=Arrays.asList(user2,user1);
		when(userRepository.findAll()).thenReturn(users);
        List<User> actualSortedUsers = userServiceImpl.getAllUsers();
        assertEquals(expectedSortedUsers, actualSortedUsers);
	}
}
