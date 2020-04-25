package com.pryshirt.service.generic;

import java.util.List;
import java.util.Optional;

public interface CustomService<T> {
	
	List<T> getAll();
	Optional<T> getById(long id);
	T create(T object);
	T update(T object);
	boolean delete(long id);
}
