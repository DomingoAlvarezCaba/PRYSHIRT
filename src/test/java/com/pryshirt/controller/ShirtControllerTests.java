package com.pryshirt.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.pryshirt.PryshirtApplication;
import com.pryshirt.model.Shirt;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PryshirtApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShirtControllerTests {

	@Autowired
	private ShirtController controller;
	
	@Test
	public void testCreateShirt() {
		Map<String, String> map = new HashMap<>();
		map.put("price", "16.25f");
		map.put("discount", "1.75f");
		map.put("color", "yellow");
		map.put("size", "44");
		ResponseEntity<Shirt> newShirt = controller.createShirt(map);
		assertNotNull(newShirt);
		assertNotNull(newShirt.getBody());
	}

	@Test
	public void testGetShirtById() {
		long shirtId = 9;
		Shirt shirt = controller.getShirtlById(shirtId).getBody();
		assertNotNull(shirt);
	}

	@Test
	public void testGetShirtsByColor() {
		String color = "black";
		List<Shirt> shirts = controller.getShirtByColor(color).getBody();
		assertNotNull(shirts);
	}

	@Test
	public void testUpdateShirt() {
		long shirtId = 9;
		Shirt shirt = controller.getShirtlById(shirtId).getBody();
		shirt.setColor("white");
		Shirt updatedShirt = controller.updateShirt(shirtId, shirt).getBody();
		assertNotNull(updatedShirt);
	}

	@Test
	public void testDeleteShirt() {
		long shirtId = 9;
		Shirt shirt = controller.getShirtlById(shirtId).getBody();
		assertNotNull(shirt);
		boolean erased = controller.deleteShirt(shirtId).getBody();
		assertTrue(erased);
	}
}