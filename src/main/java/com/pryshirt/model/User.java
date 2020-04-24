package com.pryshirt.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="\"user\"")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "user_name", length = 65, unique = true)
	private String userName;
	
	@Column(name = "password", length = 256)
	private String password;
	
	@Column(name = "address", length = 95)
	private String address;
	
	@Column(name = "phone", length = 9)
	private String phone;
	
	@Column(name = "type", length = 20)
	private String type;
	
	@OneToMany(mappedBy="user",fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public User () {
		
	}
	
	public User(long id) {
		this.id = id;
	}

	public User(long id, String address, String name, String password, String phone, String type, String userName) {
		this.id = id;
		this.address = address;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.type = type;
		this.userName = userName;
	}
	
	public User(String address, String name, String password, String phone, String type, String userName) {
		this.address = address;
		this.name = name;
		this.password = password;
		this.phone = phone;
		this.type = type;
		this.userName = userName;
	}
}
