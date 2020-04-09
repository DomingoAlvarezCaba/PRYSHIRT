package com.pryshirt.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"state\"")
public class State {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "state", length = 30)
	private String state;
	
	@Column(name = "date")
	private Calendar date;
	
	@Column(name = "order_id")
	private long orderId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Calendar getDateState() {
		return date;
	}

	public void setDateState(Calendar date) {
		this.date = date;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	
	public State() {
		
	}
	
	public State(long id) {
		this.id = id;
	}
}