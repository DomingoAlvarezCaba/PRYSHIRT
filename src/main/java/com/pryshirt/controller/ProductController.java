package com.pryshirt.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pryshirt.model.Product;
import com.pryshirt.service.ProductService;

@RestController
@RequestMapping("/pryshirt")
public class ProductController {

	@Autowired
	private ProductService service;

	@GetMapping("/products")
	public ResponseEntity<List<Product>> getProducts() {
		ResponseEntity<List<Product>> response = null;
		try {
			List<Product> products = service.getAll();
			if (products.isEmpty()) {
				response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			response = new ResponseEntity<>(products, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") long id) {
		ResponseEntity<Product> response = null;
		Optional<Product> product = service.getById(id);
		if (product.isPresent()) {
			response = new ResponseEntity<>(product.get(), HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return response;
	}

	@GetMapping("/product")
	public ResponseEntity<List<Product>> getProductByDiscount(@RequestParam(required = false) float discount) {
		ResponseEntity<List<Product>> response = null;
		List<Product> products = service.getByDiscount(discount);
		if (products.isEmpty()) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		response = new ResponseEntity<>(products, HttpStatus.OK);

		return response;
	}

	@PostMapping("/product")
	public ResponseEntity<Product> createProduct(@RequestBody Product product) {
		ResponseEntity<Product> response = null;
		try {
			Product newproduct = service.add(product);
			response = new ResponseEntity<>(newproduct, HttpStatus.CREATED);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@PutMapping("/product/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product) {
		ResponseEntity<Product> response = null;
		Optional<Product> productFound = service.getById(id);
		if (productFound.isPresent()) {
			Product productUpdated = productFound.get();
			service.add(productUpdated);
			response = new ResponseEntity<>(productUpdated, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}

	@DeleteMapping("/product/{id}")
	public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") long id) {
		ResponseEntity<Boolean> response = null;
		try {
			boolean removed = service.remove(id);
			response = new ResponseEntity<>(removed, HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return response;
	}
}