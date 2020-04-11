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

import com.pryshirt.model.Order;
import com.pryshirt.service.OrderService;

@RestController
@RequestMapping("/pryshirt")
public class OrderController {

	@Autowired
	private OrderService service;

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> getOrders() {
		ResponseEntity<List<Order>> response = null;
		try {
			List<Order> orders = service.getAll();
			if (orders.isEmpty()) {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			response = new ResponseEntity<>(orders, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
		ResponseEntity<Order> response = null;
		Optional<Order> order = service.getById(id);
		if (order.isPresent()) {
			response = new ResponseEntity<>(order.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/order")
	public ResponseEntity<List<Order>> getOrdersByState(@RequestParam(required = false) String state) {
		ResponseEntity<List<Order>> response = null;
		List<Order> orders = service.getByState(state);
		if (orders.isEmpty()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		response = new ResponseEntity<>(orders, HttpStatus.OK);

		return response;
	}

	@PostMapping(value = "/order")
	public ResponseEntity<Order> createOrder(@RequestBody Order order) {
		ResponseEntity<Order> response = null;
		try {
			Order neworder = service.add(order);
			response = new ResponseEntity<>(neworder, HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@PutMapping("/order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {
		ResponseEntity<Order> response = null;
		Optional<Order> orderFound = service.getById(id);
		if (orderFound.isPresent()) {
			orderFound.get().setState(order.getState());
			service.update(orderFound.get());
			response = new ResponseEntity<>(orderFound.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@DeleteMapping("/order/{id}")
	public ResponseEntity<Boolean> deleteOrder(@PathVariable("id") long id) {
		ResponseEntity<Boolean> response = null;
		try {
			boolean removed = service.remove(id);
			response = new ResponseEntity<>(removed, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}