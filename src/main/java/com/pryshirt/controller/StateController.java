package com.pryshirt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pryshirt.model.State;
import com.pryshirt.service.StateService;

@RestController
@RequestMapping("/pryshirt")
public class StateController {

	@Autowired
	private StateService service;

	@GetMapping("/states")
	public ResponseEntity<List<State>> getStates() {
		ResponseEntity<List<State>> response = null;
		try {
			List<State> states = service.getAll();
			if (states.isEmpty()) {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			response = new ResponseEntity<>(states, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("/state/{id}")
	public ResponseEntity<State> getStateById(@PathVariable("id") long id) {
		ResponseEntity<State> response = null;
		Optional<State> state = service.getById(id);
		if (state.isPresent()) {
			response = new ResponseEntity<>(state.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/state")
	public ResponseEntity<List<State>> getStateByOrderId(@RequestParam(required = false) long orderId) {
		ResponseEntity<List<State>> response = null;
		List<State> states = service.getByOrderId(orderId);
		if (states.isEmpty()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		response = new ResponseEntity<>(states, HttpStatus.OK);

		return response;
	}

	@PostMapping("/state")
	public ResponseEntity<State> createState(@RequestBody State state) {
		ResponseEntity<State> response = null;
		try {
			State newstate = service.create(state);
			response = new ResponseEntity<>(newstate, HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@PutMapping("/state/{id}")
	public ResponseEntity<State> updateState(@PathVariable("id") long id, @RequestBody State state) {
		ResponseEntity<State> response = null;
		Optional<State> stateFound = service.getById(id);
		if (stateFound.isPresent()) {
			stateFound.get().setState(state.getState());
			stateFound.get().setOrderId(state.getOrderId());
			service.update(stateFound.get());
			response = new ResponseEntity<>(stateFound.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@DeleteMapping("/state/{id}")
	public ResponseEntity<Boolean> deleteState(@PathVariable("id") long id) {
		ResponseEntity<Boolean> response = null;
		try {
			boolean removed = service.delete(id);
			response = new ResponseEntity<>(removed, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}