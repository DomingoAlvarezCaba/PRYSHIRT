package com.pryshirt.service;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.pryshirt.model.Order;
import com.pryshirt.repository.OrderRepository;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTests {
	
	@Mock
	private OrderRepository repository;
	
	@InjectMocks
	private OrderService service;
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		Order order = Expectations.createOrder("available", new Calendar.Builder().build(), new Calendar.Builder().build(), 64l);
		Mockito.when(repository.save(order)).thenReturn(order);
		Order result = service.create(order);
		Assertions.assertEquals("available", result.getState());
		Assertions.assertNotNull(result.getDate());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(64l, result.getUserId());
		Mockito.verify(repository, Mockito.times(1)).save(order);
	}
	
	@Test
	public void getById() {
		Order order = Expectations.createOrder("available", new Calendar.Builder().build(), new Calendar.Builder().build(), 64l);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(order));
		Optional<Order> result = service.getById(Mockito.anyLong());
		Assertions.assertEquals("available", result.get().getState());
		Assertions.assertNotNull(result.get().getDate());
		Assertions.assertNotNull(result.get().getDateState());
		Assertions.assertEquals(64l, result.get().getUserId());
		Mockito.verify(repository, Mockito.times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void getByState() {
		Mockito.when(repository.findByState(Mockito.anyString())).thenReturn(new ArrayList<Order>());
		List<Order> newShirts = service.getByState(Mockito.anyString());
		Mockito.verify(repository, Mockito.times(1)).findByState(Mockito.anyString());
		Assertions.assertNotNull(newShirts);
	}
	
	@Test
	public void getByUserId() {
		Mockito.when(repository.findByUserId(Mockito.anyLong())).thenReturn(new ArrayList<Order>());
		List<Order> newShirts = service.getByUserId(Mockito.anyLong());
		Mockito.verify(repository, Mockito.times(1)).findByUserId(Mockito.anyLong());
		Assertions.assertNotNull(newShirts);
	}

	@Test
	public void update() {
		Order order = Expectations.createOrder("available", new Calendar.Builder().build(), new Calendar.Builder().build(), 64l);
		Mockito.when(repository.save(order)).thenReturn(order);
		Order result = service.update(order);
		Assertions.assertEquals("available", result.getState());
		Assertions.assertNotNull(result.getDate());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(64l, result.getUserId());
		Mockito.verify(repository, Mockito.times(1)).save(order);
	}
	
	@Test
	public void delete() {
		service.delete(1l);
		Mockito.verify(repository, Mockito.times(1)).deleteById(1l);
	}
}