package com.pryshirt.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.Order;
import com.pryshirt.model.Product;
import com.pryshirt.model.Shirt;


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
	
	private Shirt createShirt (String color, String  size) {
		Shirt shirt = new Shirt();
		shirt.setColor(color);
		shirt.setSize(size);
		return shirt;
	}
	
	private Product createProduct(float price, float discount) {
		Product product = new Product();
		product.setPrice(price);
		product.setDiscount(discount);
		return product;
	}
	
	private Order createOrder(String state, Calendar date, Calendar dateState, long userId) {
		Order order = new Order();
		order.setState(state);
		order.setDate(date);
		order.setDateState(dateState);
		order.setUserId(userId);
		return order;
	}
	
	@Test
	public void testCreateOrderWithProducts() {
		Order order = createOrder("available",new Calendar.Builder().build(),new Calendar.Builder().build(),64);
		Product product1 = createProduct(1.65f,1.25f);
		Shirt shirt1 = createShirt("black","40");
		product1.setShirt(shirt1);
		Product product2 = createProduct(1.25f,1.35f);
		Shirt shirt2 = createShirt("white","450");
		product2.setShirt(shirt2);
		Set<Product> products = new HashSet<>();
		products.add(product1);
		products.add(product2);
		order.setProducts(products);
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