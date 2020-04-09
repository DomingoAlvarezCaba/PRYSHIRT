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

import com.pryshirt.model.Shirt;
import com.pryshirt.service.ShirtService;

@RestController
@RequestMapping("/pryshirt")
public class ShirtController {

	@Autowired
	private ShirtService service;

	@GetMapping("/shirts")
	public ResponseEntity<List<Shirt>> getShirts() {
		ResponseEntity<List<Shirt>> response = null;
		try {
			List<Shirt> shirts = service.getAll();
			if (shirts.isEmpty()) {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			response = new ResponseEntity<>(shirts, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("/shirt/{id}")
	public ResponseEntity<Shirt> getShirtlById(@PathVariable("id") long id) {
		ResponseEntity<Shirt> response = null;
		Optional<Shirt> shirt = service.getById(id);
		if (shirt.isPresent()) {
			response = new ResponseEntity<>(shirt.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/shirt")
	public ResponseEntity<List<Shirt>> getShirtByColor(@RequestParam(required = false) String color) {
		ResponseEntity<List<Shirt>> response = null;
		List<Shirt> shirts = service.getByColor(color);
		if (shirts.isEmpty()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		response = new ResponseEntity<>(shirts, HttpStatus.OK);

		return response;
	}

	@PostMapping("/shirt")
	public ResponseEntity<Shirt> createShirt(@RequestBody Shirt shirt) {
		ResponseEntity<Shirt> response = null;
		try {
			Shirt newshirt = service.add(shirt);
			response = new ResponseEntity<>(newshirt, HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@PutMapping("/shirt/{id}")
	public ResponseEntity<Shirt> updateShirt(@PathVariable("id") long id, @RequestBody Shirt shirt) {
		ResponseEntity<Shirt> response = null;
		Optional<Shirt> shirtFound = service.getById(id);
		if (shirtFound.isPresent()) {
			Shirt shirtUpdated = shirtFound.get();
			service.add(shirtUpdated);
			response = new ResponseEntity<>(shirtUpdated, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@DeleteMapping("/shirt/{id}")
	public ResponseEntity<Boolean> deleteShirt(@PathVariable("id") long id) {
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