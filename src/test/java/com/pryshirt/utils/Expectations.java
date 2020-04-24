package com.pryshirt.utils;

import java.util.Calendar;

import com.pryshirt.model.Order;
import com.pryshirt.model.Product;
import com.pryshirt.model.Shirt;
import com.pryshirt.model.State;
import com.pryshirt.model.User;

public class Expectations {

	public static User createUser(String address, String name, String password, String phone, String type,
			String userName) {
		return new User(address, name, password, phone, type, userName);
	}

	public static State createState(String state, Calendar dateState, long orderId) {
		return new State(state, dateState, orderId);
	}

	public static Shirt createShirt(String color, String size) {
		return new Shirt(color, size);
	}

	public static Product createProduct(float discount, float price) {
		return new Product(discount, price);
	}

	public static Product createProduct(float discount, float price, Shirt shirt) {
		return new Product(discount, price, shirt);
	}

	public static Order createOrder(String state, Calendar date, Calendar dateState, long userId) {
		return new Order(state, date, dateState, userId);
	}
}
