package com.pryshirt.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pryshirt.model.Order;
import com.pryshirt.repository.OrderRepository;
import com.pryshirt.service.custom.CustomService;
import com.pryshirt.util.Date;

@Service
public class OrderService implements CustomService<Order> {

	@Autowired
	private OrderRepository repository;

	@Override
	public List<Order> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Order> getById(long id) {
		return repository.findById(id);
	}

	@Override
	public Order add(Order object) {
		object.setDate(Date.getCurrentDate());
		object.setDateState(Date.getCurrentDate());
		return repository.save(object);
	}

	@Override
	public Order update(Order object) {
		object.setDateState(Date.getCurrentDate());
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
	
	public List<Order> getByState(String state){
		return repository.findByState(state);
	}
}