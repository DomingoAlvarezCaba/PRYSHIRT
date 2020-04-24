package com.pryshirt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pryshirt.model.Shirt;
import com.pryshirt.repository.ShirtRepository;
import com.pryshirt.service.generic.CustomService;

@Service
public class ShirtService implements CustomService<Shirt> {

	@Autowired
	private ShirtRepository repository;

	@Override
	public List<Shirt> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Shirt> getById(long id) {
		return repository.findById(id);
	}

	@Override
	public Shirt create(Shirt object) {
		return repository.save(object);
	}

	@Override
	public Shirt update(Shirt object) {
		return repository.save(object);
	}

	@Override
	public boolean delete(long id) {
		boolean removed = true;
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			removed = false;
		}
		return removed;
	}
	
	public List<Shirt> getByColor(String color){
		return repository.findByColor(color);
	}
}