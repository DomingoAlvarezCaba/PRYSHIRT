package com.pryshirt.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes = User.class)
public class UserModelTests {

	@Mock
	private User user;

	@Test
	public void checkIfGetUserNameWasCalled() {
		Mockito.when(user.getUserName()).thenReturn("Federico");
	    assertEquals("Federico", user.getUserName());
	    Mockito.verify(user, Mockito.times(1)).getUserName();
	}
	
	@Test
	public void checkIfTheExceptionWasThrown() {
		Mockito.when(user.getUserName()).thenThrow(DataIntegrityViolationException.class);
	    assertThrows(DataIntegrityViolationException.class, () -> {
	    	user.getUserName();
	    });
	    Mockito.verify(user, Mockito.times(1)).getUserName();
	}
	
}