package com.pryshirt.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTests {

	@Autowired
	private UserController controller;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setAddress("c/ example n12");
		user.setName("Frederic");
		user.setPassword("123456");
		user.setPhone("644865819");
		user.setType("particular");
		user.setUserName("fred" + Math.random());
		ResponseEntity<User> newUser = controller.createUser(user);
		assertNotNull(newUser);
		assertNotNull(newUser.getBody());
	}

	@Test
	public void testGetUserById() {
		long userId = 64;
		User user = controller.getUserById(userId).getBody();
		assertNotNull(user);
	}

	@Test
	public void testGetUsersByName() {
		String name = "Federic";
		List<User> users = controller.getUserByName(name).getBody();
		assertNotNull(users);
	}

	@Test
	public void testUpdateUser() {
		long userId = 65;
		User user = controller.getUserById(userId).getBody();
		user.setName("idk");
		User updatedUser = controller.updateUser(userId, user).getBody();
		assertNotNull(updatedUser);
	}

	@Test
	public void testDeleteUser() {
		long userId = 66;
		User user = controller.getUserById(userId).getBody();
		assertNotNull(user);
		boolean erased = controller.deleteUser(userId).getBody();
		assertTrue(erased);
	}
}
