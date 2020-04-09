package com.pryshirt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pryshirt.model.State;
import com.pryshirt.repository.StateRepository;
import com.pryshirt.service.custom.CustomService;

@Service
public class StateService implements CustomService<State> {

	@Autowired
	private StateRepository repository;

	@Override
	public List<State> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<State> getById(long id) {
		return repository.findById(id);
	}

	@Override
	public State add(State object) {
		return repository.save(object);
	}

	@Override
	public State update(State object) {
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
	public List<State> getByOrderId(long id){
		return repository.findByOrderId(id);
	}
}
