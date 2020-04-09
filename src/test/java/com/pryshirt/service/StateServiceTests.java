package com.pryshirt.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
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
import com.pryshirt.model.State;

@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class StateServiceTests {

	@Autowired
	private StateService service;
	
	private static final long ID = 2;

	@Test
	@Order(1)
	public void create() {
		State state = new State();
		state.setState("delivered");
		state.setDateState(new Calendar.Builder().build());
		state.setId(1);
		State newState = service.add(state);
		assertNotNull(newState);
	}
	
	@Test
	@Order(2)
	public void getById() {
		State state = service.getById(ID).get();
		assertNotNull(state);
	}
	
	@Test
	@Order(3)
	public void getByOrderId() {
		long orderId = 1;
		List<State> state = service.getByOrderId(orderId);
		assertNotNull(state);
	}

	@Test
	@Order(4)
	public void update() {
		State state = service.getById(ID).get();
		state.setState("not available");
		State updated = service.update(state);
		assertNotNull(updated);
	}

	@Test
	@Order(5)
	public void delete() {
		assertTrue(service.remove(ID));
	}
}