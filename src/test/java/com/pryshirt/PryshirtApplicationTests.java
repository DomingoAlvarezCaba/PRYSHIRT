package com.pryshirt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {PryshirtApplication.class, User.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class PryshirtApplicationTests {

	@Autowired
	private User user;

	@Test
	public void testAssertTrue() {
		List<User> users = new ArrayList<>();
		users.add(user);
		assertTrue(!users.isEmpty());
	}

	@Test
	public void testAssertEquals() {
		user.setName("Federico");
		assertEquals("Federico", user.getName());
	}

	@Test
	public void testAssertNotNull() {
		user.setName("Federico");
		assertNotNull(user.getName());
	}

	@Test
	public void testAssertNull() {
		user.setName(null);
		assertNull(user.getName());
	}
}