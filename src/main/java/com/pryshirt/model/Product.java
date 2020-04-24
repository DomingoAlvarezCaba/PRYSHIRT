package com.pryshirt.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "discount")
	private float discount;
	
	@ManyToMany(mappedBy = "products")
	@JsonBackReference
	private Set<Order> orders = new HashSet<>();
	
	@OneToOne(cascade = CascadeType.ALL)
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

	public float getDiscount() {
		return discount;
	}

	public void setDiscount(float discount) {
		this.discount = discount;
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

	public Product(long id, float price, float discount) {
		this.id = id;
		this.price = price;
		this.discount = discount;
	}
	
	public Product(float discount, float price) {
		this.discount = discount;
		this.price = price;
	}
	
	public Product(float discount, float price, Shirt shirt) {
		this.discount = discount;
		this.price = price;
		this.shirt = shirt;
	}
}
