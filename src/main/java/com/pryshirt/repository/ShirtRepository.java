package com.pryshirt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pryshirt.model.Shirt;

@Repository
public interface ShirtRepository extends JpaRepository<Shirt, Long> {
	List<Shirt> findByColor(String color);
}