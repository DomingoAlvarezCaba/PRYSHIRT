package com.pryshirt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="\"shirt\"")
public class Shirt {
	
	@Id
	@JoinColumn(name = "id")
	@Column(name = "id")
	private long id;
	
	@Column(name = "size", length = 10)
	private String size;
	
	@Column(name = "color", length = 20)
	private String color;
	
	@OneToOne
	@MapsId
	@JsonBackReference
	private Product product;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Shirt () {
		
	}
	
	public Shirt(long id) {
		this.id = id;
	}

}
