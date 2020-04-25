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

import com.pryshirt.model.Shirt;
import com.pryshirt.service.ShirtService;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class ShirtControllerTests {
	
	@Mock
	private ShirtService service;
	
	@InjectMocks
	private ShirtController controller;
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		Shirt shirt = Expectations.createShirt("black", "40");
		Mockito.when(service.create(shirt)).thenReturn(shirt);
		ResponseEntity<Shirt> response = controller.createShirt(shirt);
		Assertions.assertNotNull(response.getBody());
		Shirt result = response.getBody();
		Assertions.assertEquals("black", result.getColor());
		Assertions.assertEquals("40", result.getSize());
		Mockito.verify(service, Mockito.times(1)).create(shirt);
	}
	
	@Test
	public void getById() {
		Shirt shirt = Expectations.createShirt("black", "40");
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(shirt));
		ResponseEntity<Shirt> response = controller.getShirtById(Mockito.anyLong());
		Assertions.assertNotNull(response.getBody());
		Shirt result = response.getBody();
		Assertions.assertEquals("black", result.getColor());
		Assertions.assertEquals("40", result.getSize());
		Mockito.verify(service, Mockito.times(1)).getById(Mockito.anyLong());
	}

	@Test
	public void testGetByColor() {
		Mockito.when(service.getByColor(Mockito.anyString())).thenReturn(new ArrayList<Shirt>());
		ResponseEntity<List<Shirt>> response = controller.getShirtByColor(Mockito.anyString());
		Assertions.assertNotNull(response.getBody());
		List<Shirt> newShirts = response.getBody();
		Mockito.verify(service, Mockito.times(1)).getByColor(Mockito.anyString());
		Assertions.assertNotNull(newShirts);
	}


	@Test
	public void update() {
		Shirt shirt = Expectations.createShirt("black", "40");
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(shirt));
		Mockito.when(service.update(shirt)).thenReturn(shirt);
		ResponseEntity<Shirt> response = controller.updateShirt(Mockito.anyLong(), shirt);
		Assertions.assertNotNull(response.getBody());
		Shirt result = response.getBody();
		Assertions.assertEquals("black", result.getColor());
		Assertions.assertEquals("40", result.getSize());
		Mockito.verify(service, Mockito.times(1)).update(shirt);
	}
	
	@Test
	public void delete() {
		service.delete(Mockito.anyLong());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyLong());
	}
}