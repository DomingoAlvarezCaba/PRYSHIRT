package com.pryshirt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pryshirt.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
	List<State> findByOrderId(long id);
}