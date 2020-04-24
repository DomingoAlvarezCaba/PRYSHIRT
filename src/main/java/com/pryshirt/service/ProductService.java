package com.pryshirt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pryshirt.model.Product;
import com.pryshirt.repository.ProductRepository;
import com.pryshirt.service.generic.CustomService;

@Service
public class ProductService implements CustomService<Product> {

	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> getAll() {
		return repository.findAll();
	}

	@Override
	public Optional<Product> getById(long id) {
		return repository.findById(id);
	}

	@Override
	public Product create(Product object) {
		return repository.save(object);
	}

	@Override
	public Product update(Product object) {
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
	
	public List<Product> getByDiscount(float discount){
		return repository.findByDiscount(discount);
	}
}