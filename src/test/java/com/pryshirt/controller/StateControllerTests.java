package com.pryshirt.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.State;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StateControllerTests {

	@Autowired
	private StateController controller;
	
	@Test
	public void testCreateState() {
		State state = new State();
		state.setState("delivered");
		state.setDateState(new Calendar.Builder().build());
		state.setId(1);
		ResponseEntity<State> newState = controller.createState(state);
		assertNotNull(newState);
		assertNotNull(newState.getBody());
	}

	@Test
	public void testGetStateById() {
		State state = controller.getStatelById(3).getBody();
		assertNotNull(state);
	}

	@Test
	public void testGetStatesByOrderId() {
		long stateId = 0;
		List<State> states = controller.getStateByOrderId(stateId).getBody();
		assertNotNull(states);
	}

	@Test
	public void testUpdateState() {
		State state = controller.getStatelById(3).getBody();
		state.setState("new");
		State updatedState = controller.updateState(3, state).getBody();
		assertNotNull(updatedState);
	}

	@Test
	public void testDeleteState() {
		long stateId = 3;
		State state = controller.getStatelById(stateId).getBody();
		assertNotNull(state);
		boolean erased = controller.deleteState(stateId).getBody();
		assertTrue(erased);
	}
}