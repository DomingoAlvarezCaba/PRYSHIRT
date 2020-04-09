package com.pryshirt.service.custom;

import java.util.List;
import java.util.Optional;

public interface CustomService<T> {
	
	List<T> getAll();
	Optional<T> getById(long id);
	T add(T object);
	T update(T object);
	boolean remove(long id);
}
