package com.pryshirt.controller;

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
import org.springframework.http.ResponseEntity;

import com.pryshirt.model.State;
import com.pryshirt.service.StateService;
import com.pryshirt.utils.Expectations;

@RunWith(MockitoJUnitRunner.class)
public class StateControllerTests {
	
	@Mock
	private StateService service;
	
	@InjectMocks
	private StateController controller;
	
	
	@BeforeEach
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void create() {
		State state = Expectations.createState("delivered", new Calendar.Builder().build(), 1l);
		Mockito.when(service.create(state)).thenReturn(state);
		ResponseEntity<State> response = controller.createState(state);
		Assertions.assertNotNull(response.getBody());
		State result = response.getBody();
		Assertions.assertEquals("delivered", result.getState());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(1l, result.getOrderId());
		Mockito.verify(service, Mockito.times(1)).create(state);
	}
	
	@Test
	public void getById() {
		State state = Expectations.createState("delivered", new Calendar.Builder().build(), 1l);
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(state));
		ResponseEntity<State> response = controller.getStateById(Mockito.anyLong());
		Assertions.assertNotNull(response.getBody());
		State result = response.getBody();
		Assertions.assertEquals("delivered", result.getState());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(1l, result.getOrderId());
		Mockito.verify(service, Mockito.times(1)).getById(Mockito.anyLong());
	}

	@Test
	public void testGetStatesByOrderId() {
		Mockito.when(service.getByOrderId(Mockito.anyLong())).thenReturn(new ArrayList<State>());
		ResponseEntity<List<State>> response = controller.getStateByOrderId(Mockito.anyLong());
		Assertions.assertNotNull(response.getBody());
		List<State> newStates = response.getBody();
		Mockito.verify(service, Mockito.times(1)).getByOrderId(Mockito.anyLong());
		Assertions.assertNotNull(newStates);
	}


	@Test
	public void update() {
		State state = Expectations.createState("delivered", new Calendar.Builder().build(), 1l);
		Mockito.when(service.getById(Mockito.anyLong())).thenReturn(Optional.of(state));
		Mockito.when(service.update(state)).thenReturn(state);
		ResponseEntity<State> response = controller.updateState(Mockito.anyLong(), state);
		Assertions.assertNotNull(response.getBody());
		State result = response.getBody();
		Assertions.assertEquals("delivered", result.getState());
		Assertions.assertNotNull(result.getDateState());
		Assertions.assertEquals(1l, result.getOrderId());
		Mockito.verify(service, Mockito.times(1)).update(state);
	}
	
	@Test
	public void delete() {
		service.delete(Mockito.anyLong());
		Mockito.verify(service, Mockito.times(1)).delete(Mockito.anyLong());
	}
}