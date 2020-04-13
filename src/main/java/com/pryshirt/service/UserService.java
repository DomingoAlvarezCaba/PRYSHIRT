package com.pryshirt.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pryshirt.model.User;
import com.pryshirt.repository.UserRepository;
import com.pryshirt.service.custom.CustomService;

@Service
public class UserService implements CustomService<User> {

	@Autowired
	private UserRepository repository;

	@Override
	public List<User> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<User> getById(long id) {
		return repository.findById(id);
	}

	@Override
	public User add(User object) {
		return repository.save(object);
	}

	@Override
	public User update(User object) {
		return repository.save(object);
	}

	@Override
	public boolean remove(long id) {
		boolean removed = true;
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			removed = false;
		}
		return removed;
	}
	
	public List<User> getByName(String name) {
		return repository.findByName(name);
	}
	
	public User checkLogin(Map<String,String> credentials) {
		User user = null;
		Optional<User> oUser = repository.findByUserName(credentials.get("userName"));
		if(oUser.isPresent() && oUser.get().getPassword().equals(credentials.get("password"))) {
			user = oUser.get();
		}
		return user;
	}
}
