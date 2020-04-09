package com.pryshirt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.Product;
import com.pryshirt.model.Shirt;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ShirtServiceTests {

	@Autowired
	private ShirtService service;
	
	private static final long ID = 6;

	@Test
	@Order(1)
	public void create() {
		Shirt shirt = new Shirt();
		Product product = new Product();
		product.setDiscount(1.75f);
		product.setPrice(16.25f);
		shirt.setProduct(product);
		shirt.setColor("black");
		shirt.setSize("40");
		Shirt newShirt = service.add(shirt);
		assertNotNull(newShirt);
	}
	
	@Test
	@Order(2)
	public void getById() {
		Shirt shirt = service.getById(ID).get();
		assertNotNull(shirt);
	}
	
	@Test
	@Order(3)
	public void getByColor() {
		String color = "black";
		List<Shirt> shirt = service.getByColor(color);
		assertNotNull(shirt);
	}

	@Test
	@Order(4)
	public void update() {
		Shirt shirt = service.getById(ID).get();
		shirt.setColor("yellow");
		Shirt updated = service.update(shirt);
		assertNotNull(updated);
	}

	@Test
	@Order(5)
	public void delete() {
		assertTrue(service.remove(ID));
	}
}