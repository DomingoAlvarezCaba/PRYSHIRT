package com.pryshirt.controller;

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
import org.springframework.http.ResponseEntity;

import com.pryshirt.model.Order;
import com.pryshirt.service.OrderService;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTests {
	
	@Mock
	private OrderService service;
	
	@InjectMocks
	private OrderController controller;
	
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		Order order = Expectations.createOrder("available", new Calendar.Builder().build(), new Calendar.Builder().build(), 64l);
		Mockito.when(service.create(order)).thenReturn(order);
		ResponseEntity<Order> response = controller.createOrder(order);
		Assertions.assertNotNull(response.getBody());
		Order result = response.getBody();
		Assertions.assertEquals("available", result.getState());
		Assertions.assertNotNull(result.getDate());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(64, result.getUserId());
		Mockito.verify(service, Mockito.times(1)).create(order);
	}
	
	@Test
	public void getById() {
		Order order = Expectations.createOrder("available", new Calendar.Builder().build(), new Calendar.Builder().build(), 64l);
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(order));
		ResponseEntity<Order> response = controller.getOrderById(Mockito.anyLong());
		Assertions.assertNotNull(response.getBody());
		Order result = response.getBody();
		Assertions.assertEquals("available", result.getState());
		Assertions.assertNotNull(result.getDate());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(64, result.getUserId());
		Mockito.verify(service, Mockito.times(1)).getById(Mockito.anyLong());
	}

	@Test
	public void testGetOrdersByDisccount() {
		Mockito.when(service.getByUserId(Mockito.anyLong())).thenReturn(new ArrayList<Order>());
		ResponseEntity<List<Order>> response = controller.getOrdersByUserId((Mockito.anyLong()));
		Assertions.assertNotNull(response.getBody());
		List<Order> newOrders = response.getBody();
		Mockito.verify(service, Mockito.times(1)).getByUserId(Mockito.anyLong());
		Assertions.assertNotNull(newOrders);
	}


	@Test
	public void update() {
		Order order = Expectations.createOrder("available", new Calendar.Builder().build(), new Calendar.Builder().build(), 64l);
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(order));
		Mockito.when(service.update(order)).thenReturn(order);
		ResponseEntity<Order> response = controller.updateOrder(Mockito.anyLong(), order);
		Assertions.assertNotNull(response.getBody());
		Order result = response.getBody();
		Assertions.assertEquals("available", result.getState());
		Assertions.assertNotNull(result.getDate());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(64, result.getUserId());
		Mockito.verify(service, Mockito.times(1)).update(order);
	}
	
	@Test
	public void delete() {
		service.delete(Mockito.anyLong());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyLong());
	}
}