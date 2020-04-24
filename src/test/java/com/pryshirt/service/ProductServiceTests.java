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

import com.pryshirt.model.Product;
import com.pryshirt.model.Shirt;
import com.pryshirt.repository.ProductRepository;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTests {
	
	@Mock
	private ProductRepository repository;
	
	@InjectMocks
	private ProductService service;
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		Product product = Expectations.createProduct(1.75f, 16.25f, new Shirt("black", "40"));
		Mockito.when(repository.save(product)).thenReturn(product);
		Product result = service.create(product);
		Assertions.assertEquals(1.75f, result.getDiscount());
		Assertions.assertEquals(16.25f, result.getPrice());
		Assertions.assertEquals("black", result.getShirt().getColor());
		Assertions.assertEquals("40", result.getShirt().getSize());
		Mockito.verify(repository, Mockito.times(1)).save(product);
	}
	
	@Test
	public void getById() {
		Product product = Expectations.createProduct(1.75f, 16.25f, new Shirt("black", "40"));
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(product));
		Optional<Product> result = service.getById(Mockito.anyLong());
		Assertions.assertEquals(1.75f, result.get().getDiscount());
		Assertions.assertEquals(16.25f, result.get().getPrice());
		Assertions.assertEquals("black", result.get().getShirt().getColor());
		Assertions.assertEquals("40", result.get().getShirt().getSize());
		Mockito.verify(repository, Mockito.times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void getByDiscount() {
		Mockito.when(repository.findByDiscount(Mockito.anyFloat())).thenReturn(new ArrayList<Product>());
		List<Product> newShirts = service.getByDiscount(Mockito.anyFloat());
		Mockito.verify(repository, Mockito.times(1)).findByDiscount(Mockito.anyFloat());
		Assertions.assertNotNull(newShirts);
	}

	@Test
	public void update() {
		Product product = Expectations.createProduct(1.75f, 16.25f, new Shirt("black", "40"));
		Mockito.when(repository.save(product)).thenReturn(product);
		Product result = service.update(product);
		Assertions.assertEquals(1.75f, result.getDiscount());
		Assertions.assertEquals(16.25f, result.getPrice());
		Assertions.assertEquals("black", result.getShirt().getColor());
		Assertions.assertEquals("40", result.getShirt().getSize());
		Mockito.verify(repository, Mockito.times(1)).save(product);
	}
	
	@Test
	public void delete() {
		service.delete(1l);
		Mockito.verify(repository, Mockito.times(1)).deleteById(1l);
	}
}