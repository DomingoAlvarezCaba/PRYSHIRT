package com.pryshirt.controller.mockserver;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.pryshirt.model.User;

@RunWith(SpringRunner.class)
public class MockServerTests{

	private static ClientAndServer mockServer;
	private Gson gson = new Gson();

	@BeforeAll
	public static void startServer() {
		mockServer = ClientAndServer.startClientAndServer(1080);
	}
	
	@Test
	public void doTheJob() {
		addUser(mockServer);
		getUser(mockServer);
		removeUser(mockServer);
	}

	@AfterAll
	public static void stopServer() {
		mockServer.stop();
	}

	public void removeUser(ClientAndServer mockServer) {

		mockServer
			.when(request().withMethod("POST").withHeader("Content-Type", "application/json")
			.withPath("/pryshirt/user/1"))
			.respond(response().withStatusCode(20));

	}

	public void getUser(ClientAndServer mockServer) {
		User user = createUserObjectToPut();
		mockServer
			.when(request().withMethod("GET").withHeader("Accept", "application/json").withPath("/pryshirt/user/1"))
			.respond(response().withStatusCode(200).withBody(gson.toJson(user)));

	}

	public void addUser(ClientAndServer mockServer) {
		User user = createUserObjectToPut();
		mockServer
			.when(request().withMethod("POST").withHeader("Content-Type", "application/json")
			.withPath("/pryshirt/user").withBody(gson.toJson(createUserObjectToPut())))
			.respond(response().withStatusCode(200).withBody(gson.toJson(user)));
	}

	public static User createUserObjectToPut() {
		User user = new User();
		user.setAddress("example");
		return user;
	}
}