package com.pryshirt.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTests {

	@Autowired
	private ProductController controller;
	
	@Test
	public void testCreateProduct() {
		Product product = new Product();
		product.setDiscount(0.0f);
		product.setPrice(100.25f);
		ResponseEntity<Product> newProduct = controller.createProduct(product);
		assertNotNull(newProduct);
		assertNotNull(newProduct.getBody());
	}

	@Test
	public void testGetProductById() {
		long productId = 3;
		Product product = controller.getProductById(productId).getBody();
		assertNotNull(product);
	}

	@Test
	public void testGetProductsByDisccount() {
		float discount = 1.75f;
		List<Product> products = controller.getProductByDiscount(discount).getBody();
		assertNotNull(products);
	}

	@Test
	public void testUpdateProduct() {
		long productId = 4;
		Product product = controller.getProductById(productId).getBody();
		product.setPrice(260.25f);
		Product updatedProduct = controller.updateProduct(productId, product).getBody();
		assertNotNull(updatedProduct);
	}

	@Test
	public void testDeleteProduct() {
		long productId = 5;
		Product product = controller.getProductById(productId).getBody();
		assertNotNull(product);
		boolean erased = controller.deleteProduct(productId).getBody();
		assertTrue(erased);
	}
}