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

import com.pryshirt.model.Shirt;
import com.pryshirt.repository.ShirtRepository;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class ShirtServiceTests {
	
	@Mock
	private ShirtRepository repository;
	
	@InjectMocks
	private ShirtService service;
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		Shirt shirt = Expectations.createShirt("black", "40");
		Mockito.when(repository.save(shirt)).thenReturn(shirt);
		Shirt result = service.create(shirt);
		Assertions.assertEquals("black", result.getColor());
		Assertions.assertEquals("40", result.getSize());
		Mockito.verify(repository, Mockito.times(1)).save(shirt);
	}
	
	@Test
	public void getById() {
		Shirt shirt = Expectations.createShirt("black", "40");
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(shirt));
		Optional<Shirt> result = service.getById(Mockito.anyLong());
		Assertions.assertEquals("black", result.get().getColor());
		Assertions.assertEquals("40", result.get().getSize());
		Mockito.verify(repository, Mockito.times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void getByColor() {
		Mockito.when(repository.findByColor(Mockito.anyString())).thenReturn(new ArrayList<Shirt>());
		List<Shirt> newShirts = service.getByColor(Mockito.anyString());
		Mockito.verify(repository, Mockito.times(1)).findByColor(Mockito.anyString());
		Assertions.assertNotNull(newShirts);
	}

	@Test
	public void update() {
		Shirt shirt = Expectations.createShirt("black", "40");
		Mockito.when(repository.save(shirt)).thenReturn(shirt);
		Shirt result = service.update(shirt);
		Assertions.assertEquals("black", result.getColor());
		Assertions.assertEquals("40", result.getSize());
		Mockito.verify(repository, Mockito.times(1)).save(shirt);
	}
	
	@Test
	public void delete() {
		service.delete(1l);
		Mockito.verify(repository, Mockito.times(1)).deleteById(1l);
	}
}