package com.pryshirt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.pryshirt.model.User;
import com.pryshirt.service.UserService;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {
	
	@Mock
	private UserService service;
	
	@InjectMocks
	private UserController controller;
	
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		User user = Expectations.createUser("c/ example n12", "Federico", "123456", "644865819", "particular", "fred");
		Mockito.when(service.create(user)).thenReturn(user);
		ResponseEntity<User> response = controller.createUser(user);
		Assertions.assertNotNull(response.getBody());
		User result = response.getBody();
		Assertions.assertEquals("c/ example n12", result.getAddress());
		Assertions.assertEquals("Federico", result.getName());
		Assertions.assertEquals("123456", result.getPassword());
		Assertions.assertEquals("644865819", result.getPhone());
		Assertions.assertEquals("particular", result.getType());
		Assertions.assertEquals("fred", result.getUserName());
		Mockito.verify(service, Mockito.times(1)).create(user);
	}
	
	@Test
	public void getById() {
		User user = Expectations.createUser("c/ example n12", "Federico", "123456", "644865819", "particular", "fred");
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(user));
		ResponseEntity<User> response = controller.getUserById(Mockito.anyLong());
		Assertions.assertNotNull(response.getBody());
		User result = response.getBody();
		Assertions.assertEquals("c/ example n12", result.getAddress());
		Assertions.assertEquals("Federico", result.getName());
		Assertions.assertEquals("123456", result.getPassword());
		Assertions.assertEquals("644865819", result.getPhone());
		Assertions.assertEquals("particular", result.getType());
		Assertions.assertEquals("fred", result.getUserName());
		Mockito.verify(service, Mockito.times(1)).getById(Mockito.anyLong());
	}

	@Test
	public void getByName() {
		Mockito.when(service.getByName(Mockito.anyString())).thenReturn(new ArrayList<User>());
		ResponseEntity<List<User>> response = controller.getUserByName(Mockito.anyString());
		Assertions.assertNotNull(response.getBody());
		List<User> newUsers = response.getBody();
		Mockito.verify(service, Mockito.times(1)).getByName(Mockito.anyString());
		Assertions.assertNotNull(newUsers);
	}


	@Test
	public void update() {
		User user = Expectations.createUser("c/ example n12", "Federico", "123456", "644865819", "particular", "fred");
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(user));
		Mockito.when(service.update(user)).thenReturn(user);
		ResponseEntity<User> response = controller.updateUser(Mockito.anyLong(), user);
		Assertions.assertNotNull(response.getBody());
		User result = response.getBody();
		Assertions.assertEquals("c/ example n12", result.getAddress());
		Assertions.assertEquals("Federico", result.getName());
		Assertions.assertEquals("123456", result.getPassword());
		Assertions.assertEquals("644865819", result.getPhone());
		Assertions.assertEquals("particular", result.getType());
		Assertions.assertEquals("fred", result.getUserName());
		Mockito.verify(service, Mockito.times(1)).update(user);
	}
	
	@Test
	public void delete() {
		service.delete(Mockito.anyLong());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyLong());
	}
}