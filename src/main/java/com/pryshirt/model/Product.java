package com.pryshirt.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="\"product\"")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "state")
	private float state;
	
	@ManyToMany(mappedBy = "products",fetch = FetchType.EAGER)
	@JsonBackReference
	private Set<Order> orders = new HashSet<>();
	
	@OneToOne(mappedBy = "product")
    private Shirt shirt;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getState() {
		return state;
	}

	public void setState(float state) {
		this.state = state;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Shirt getShirt() {
		return shirt;
	}
	
	public void setShirt(Shirt shirt) {
		this.shirt = shirt;
	}

	public Product () {
		
	}
	
	public Product(long id) {
		this.id = id;
	}
}
