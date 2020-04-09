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


@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class ProductServiceTests {

	@Autowired
	private ProductService service;
	
	private static final long ID = 3;

	@Test
	@Order(1)
	public void create() {
		Product product = new Product();
		product.setDiscount(0.0f);
		product.setPrice(100.25f);
		Product newProduct = service.add(product);
		assertNotNull(newProduct);
	}
	
	@Test
	@Order(2)
	public void getById() {
		Product product = service.getById(ID).get();
		assertNotNull(product);
	}
	
	@Test
	@Order(3)
	public void getByColor() {
		float discount = 0.0f;
		List<Product> product = service.getByDiscount(discount);
		assertNotNull(product);
	}

	@Test
	@Order(4)
	public void update() {
		Product product = service.getById(ID).get();
		product.setDiscount(1.25f);
		Product updated = service.update(product);
		assertNotNull(updated);
	}

	@Test
	@Order(5)
	public void delete() {
		assertTrue(service.remove(ID));
	}
}