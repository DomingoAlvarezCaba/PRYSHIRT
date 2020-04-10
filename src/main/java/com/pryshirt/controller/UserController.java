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

import com.pryshirt.model.User;
import com.pryshirt.service.UserService;

@RestController
@RequestMapping("/pryshirt")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {
		ResponseEntity<List<User>> response = null;
		try {
			List<User> users = service.getAll();
			if (users.isEmpty()) {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			response = new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		ResponseEntity<User> response = null;
		Optional<User> user = service.getById(id);
		if (user.isPresent()) {
			response = new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/user")
	public ResponseEntity<List<User>> getUserByName(@RequestParam(required = false) String name) {
		ResponseEntity<List<User>> response = null;
		try {
			List<User> users = service.getByName(name);
			if (users.isEmpty()) {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			response = new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		ResponseEntity<User> response = null;
		try {
			User newuser = service.update(user);
			response = new ResponseEntity<>(newuser, HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		ResponseEntity<User> response = null;
		Optional<User> userFound = service.getById(id);
		if (userFound.isPresent()) {
			userFound.get().setAddress(user.getAddress());
			userFound.get().setName(user.getName());
			userFound.get().setPassword(user.getPassword());
			userFound.get().setPhone(user.getPhone());
			userFound.get().setType(user.getType());
			userFound.get().setUserName(user.getUserName());
			service.update(userFound.get());
			response = new ResponseEntity<>(userFound.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id) {
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