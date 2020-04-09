package com.pryshirt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.Order;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class OrderServiceTests {

	@Autowired
	private OrderService service;
	
	private static final long ID = 1;

	@Test
	@org.junit.jupiter.api.Order(1)
	public void create() {
		Order order = new Order();
		order.setState("available");
		order.setDate(new Calendar.Builder().build());
		order.setDateState(new Calendar.Builder().build());
		order.setUserId(25);
		Order newOrder = service.add(order);
		assertNotNull(newOrder);
	}
	
	@Test
	@org.junit.jupiter.api.Order(2)
	public void getById() {
		Order order = service.getById(ID).get();
		assertNotNull(order);
	}
	
	@Test
	@org.junit.jupiter.api.Order(3)
	public void getByState() {
		String state = "available";
		List<Order> order = service.getByState(state);
		assertNotNull(order);
	}

	@Test
	@org.junit.jupiter.api.Order(4)
	public void update() {
		Order order = service.getById(ID).get();
		order.setState("not available");
		Order updated = service.update(order);
		assertNotNull(updated);
	}

	@Test
	@org.junit.jupiter.api.Order(5)
	public void delete() {
		assertTrue(service.remove(ID));
	}
}