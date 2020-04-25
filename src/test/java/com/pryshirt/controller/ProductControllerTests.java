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

import com.pryshirt.model.Product;
import com.pryshirt.service.ProductService;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTests {
	
	@Mock
	private ProductService service;
	
	@InjectMocks
	private ProductController controller;
	
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		Product product = Expectations.createProduct(1.75f, 16.25f);
		Mockito.when(service.create(product)).thenReturn(product);
		ResponseEntity<Product> response = controller.createProduct(product);
		Assertions.assertNotNull(response.getBody());
		Product result = response.getBody();
		Assertions.assertEquals(1.75f, result.getDiscount());
		Assertions.assertEquals(16.25f, result.getPrice());
		Mockito.verify(service, Mockito.times(1)).create(product);
	}
	
	@Test
	public void getById() {
		Product product = Expectations.createProduct(1.75f, 16.25f);
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(product));
		ResponseEntity<Product> response = controller.getProductById(Mockito.anyLong());
		Assertions.assertNotNull(response.getBody());
		Product result = response.getBody();
		Assertions.assertEquals(1.75f, result.getDiscount());
		Assertions.assertEquals(16.25f, result.getPrice());
		Mockito.verify(service, Mockito.times(1)).getById(Mockito.anyLong());
	}

	@Test
	public void testGetByDisccount() {
		Mockito.when(service.getByDiscount(Mockito.anyFloat())).thenReturn(new ArrayList<Product>());
		ResponseEntity<List<Product>> response = controller.getProductByDiscount(Mockito.anyFloat());
		Assertions.assertNotNull(response.getBody());
		List<Product> newProducts = response.getBody();
		Mockito.verify(service, Mockito.times(1)).getByDiscount(Mockito.anyFloat());
		Assertions.assertNotNull(newProducts);
	}


	@Test
	public void update() {
		Product product = Expectations.createProduct(1.75f, 16.25f);
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(product));
		Mockito.when(service.update(product)).thenReturn(product);
		ResponseEntity<Product> response = controller.updateProduct(Mockito.anyLong(), product);
		Assertions.assertNotNull(response.getBody());
		Product result = response.getBody();
		Assertions.assertEquals(1.75f, result.getDiscount());
		Assertions.assertEquals(16.25f, result.getPrice());
		Mockito.verify(service, Mockito.times(1)).update(product);
	}
	
	@Test
	public void delete() {
		service.delete(Mockito.anyLong());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyLong());
	}
}