package com.pryshirt.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.Order;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerTests {

	@Autowired
	private OrderController controller;
	
	@Test
	public void testCreateOrder() {
		Order order = new Order();
		order.setState("available");
		order.setDate(new Calendar.Builder().build());
		order.setDateState(new Calendar.Builder().build());
		order.setUserId(64);
		ResponseEntity<Order> newOrder = controller.createOrder(order);
		assertNotNull(newOrder);
		assertNotNull(newOrder.getBody());
	}

	@Test
	public void testGetOrderById() {
		long orderId = 4;
		Order order = controller.getOrderById(orderId).getBody();
		assertNotNull(order);
	}

	@Test
	public void testGetOrdersByDisccount() {
		String state = "available";
		List<Order> orders = controller.getOrdersByState(state).getBody();
		assertNotNull(orders);
	}

	@Test
	public void testUpdateOrder() {
		long orderId = 5;
		Order order = controller.getOrderById(orderId).getBody();
		order.setDate(new Calendar.Builder().build());
		Order updatedOrder = controller.updateOrder(orderId, order).getBody();
		assertNotNull(updatedOrder);
	}

	@Test
	public void testDeleteOrder() {
		long orderId = 6;
		Order order = controller.getOrderById(orderId).getBody();
		assertNotNull(order);
		boolean erased = controller.deleteOrder(orderId).getBody();
		assertTrue(erased);
	}
}