package com.pryshirt.service;

import java.util.ArrayList;
import java.util.Calendar;
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

import com.pryshirt.model.State;
import com.pryshirt.repository.StateRepository;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class StateServiceTests {
	
	@Mock
	private StateRepository repository;
	
	@InjectMocks
	private StateService service;
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		State state = Expectations.createState("delivered", new Calendar.Builder().build(), 1l);
		Mockito.when(repository.save(state)).thenReturn(state);
		State result = service.create(state);
		Assertions.assertEquals("delivered", result.getState());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(1l, result.getOrderId());
		Mockito.verify(repository, Mockito.times(1)).save(state);
	}
	
	@Test
	public void getById() {
		State state = Expectations.createState("delivered", new Calendar.Builder().build(), 1l);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(state));
		Optional<State> result = service.getById(Mockito.anyLong());
		Assertions.assertTrue(result.isPresent());
		Assertions.assertEquals("delivered", result.get().getState());
		Assertions.assertNotNull(result.get().getDateState());
		Assertions.assertEquals(1l, result.get().getOrderId());
		Mockito.verify(repository, Mockito.times(1)).findById(Mockito.anyLong());
	}

	@Test
	public void getByOrderId() {
		Mockito.when(repository.findByOrderId(Mockito.anyLong())).thenReturn(new ArrayList<State>());
		List<State> newStates = service.getByOrderId(Mockito.anyLong());
		Mockito.verify(repository, Mockito.times(1)).findByOrderId(Mockito.anyLong());
		Assertions.assertNotNull(newStates);
	}

	@Test
	public void update() {
		State state = Expectations.createState("delivered", new Calendar.Builder().build(), 1l);
		Mockito.when(repository.save(state)).thenReturn(state);
		State result = service.update(state);
		Assertions.assertEquals("delivered", result.getState());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(1l, result.getOrderId());
		Mockito.verify(repository, Mockito.times(1)).save(state);
	}
	
	@Test
	public void delete() {
		service.delete(Mockito.anyLong());
		Mockito.verify(repository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
}