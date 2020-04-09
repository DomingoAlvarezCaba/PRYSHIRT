package com.pryshirt.service;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.User;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserServiceTests {

	@Autowired
	private UserService service;
	
	private static final long ID = 25;

	@Test
	@Order(1)
	public void create() {
		User user = new User();
		user.setAddress("c/ example n12");
		user.setName("Federico");
		user.setPassword("123456");
		user.setPhone("644865819");
		user.setType("particular");
		user.setUserName("fred");
		User newUser = service.add(user);
		assertNotNull(newUser);
	}
	
	@Test
	@Order(2)
	public void getById() {
		User user = service.getById(ID).get();
		assertNotNull(user);
	}
	
	@Test
	@Order(3)
	public void getByName() {
		String name = "Federico";
		User user = service.getByName(name).get();
		assertNotNull(user);
	}

	@Test
	@Order(4)
	public void update() {
		User user = service.getById(ID).get();
		user.setName("Frederic");
		User updated = service.update(user);
		assertNotNull(updated);
	}

	@Test
	@Order(5)
	public void delete() {
		assertTrue(service.remove(ID));
	}
}