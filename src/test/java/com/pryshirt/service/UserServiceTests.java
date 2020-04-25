package com.pryshirt.service;

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

import com.pryshirt.model.User;
import com.pryshirt.repository.UserRepository;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {
	
	@Mock
	private UserRepository repository;
	
	@InjectMocks
	private UserService service;
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		User user = Expectations.createUser("c/ example n12", "Federico", "123456", "644865819", "particular", "fred");
		Mockito.when(repository.save(user)).thenReturn(user);
		User result = service.create(user);
		Assertions.assertEquals("c/ example n12", result.getAddress());
		Assertions.assertEquals("Federico", result.getName());
		Assertions.assertEquals("123456", result.getPassword());
		Assertions.assertEquals("644865819", result.getPhone());
		Assertions.assertEquals("particular", result.getType());
		Assertions.assertEquals("fred", result.getUserName());
		Mockito.verify(repository, Mockito.times(1)).save(user);
	}
	
	@Test
	public void getById() {
		User user = Expectations.createUser("c/ example n12", "Federico", "123456", "644865819", "particular", "fred");
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(user));
		Optional<User> result = service.getById(Mockito.anyLong());
		Assertions.assertTrue(result.isPresent());
		Assertions.assertEquals("c/ example n12", result.get().getAddress());
		Assertions.assertEquals("Federico", result.get().getName());
		Assertions.assertEquals("123456", result.get().getPassword());
		Assertions.assertEquals("644865819", result.get().getPhone());
		Assertions.assertEquals("particular", result.get().getType());
		Assertions.assertEquals("fred", result.get().getUserName());
		Mockito.verify(repository, Mockito.times(1)).findById(user.getId());
	}

	@Test
	public void getByName() {
		Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(new ArrayList<User>());
		List<User> newUsers = service.getByName(Mockito.anyString());
		Mockito.verify(repository, Mockito.times(1)).findByName(Mockito.anyString());
		Assertions.assertNotNull(newUsers);
	}
	
	@Test
	public void getByUserName() {

		Mockito.when(repository.findByUserName(Mockito.anyString())).thenReturn(Optional.of(new User()));
		Optional<User> newUser = service.getByUserName(Mockito.anyString());
		Mockito.verify(repository, Mockito.times(1)).findByUserName(Mockito.anyString());
		Assertions.assertTrue(newUser.isPresent());
	}

	@Test
	public void update() {
		User user = Expectations.createUser("c/ example n12", "Federico", "123456", "644865819", "particular", "fred");
		Mockito.when(repository.save(user)).thenReturn(user);
		User result = service.update(user);
		Assertions.assertEquals("c/ example n12", result.getAddress());
		Assertions.assertEquals("Federico", result.getName());
		Assertions.assertEquals("123456", result.getPassword());
		Assertions.assertEquals("644865819", result.getPhone());
		Assertions.assertEquals("particular", result.getType());
		Assertions.assertEquals("fred", result.getUserName());
		Mockito.verify(repository, Mockito.times(1)).save(user);
	}
	
	@Test
	public void delete() {
		service.delete(Mockito.anyLong());
		Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
}