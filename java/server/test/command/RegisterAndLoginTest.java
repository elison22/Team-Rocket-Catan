package test.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import shared.dto.Login_Params;
import user.IUserFacade;
import user.UserFacade;
import command.Login_CO;
import command.Register_CO;

public class RegisterAndLoginTest {
	
	IUserFacade userFacade;
	Register_CO regCO;
	Login_CO loginCO;
	
	@Before
	public void setup() {
		userFacade = new UserFacade();
	}

	@Test
	public void testRegister() {
		
		// Test a password that's too short
		assertFalse(userFacade.register(new Login_Params("test", "test")));
		
		// Username that's too short
		assertFalse(userFacade.register(new Login_Params("te", "testing")));
		
		// Username and Password that's too long
		assertFalse(userFacade.register(new Login_Params("tooolong", "testing")));
		assertFalse(userFacade.register(new Login_Params("test", "ThisPasswordIsWayTooFreakingLong")));
		
		// Username that's been taken
		assertTrue(userFacade.register(new Login_Params("test", "testing")));
		assertFalse(userFacade.register(new Login_Params("test", "testing")));
		
		// Username and Password with invalid characters
		assertFalse(userFacade.register(new Login_Params("!test", "testing")));
		assertFalse(userFacade.register(new Login_Params("\n\"\\", "testing")));
		assertFalse(userFacade.register(new Login_Params("", "testing")));
		assertFalse(userFacade.register(new Login_Params("test2", "tested!")));
		
		// Username and Password with valid characters
		assertTrue(userFacade.register(new Login_Params("_Ab-9", "testing")));
		assertTrue(userFacade.register(new Login_Params("test2", "--__bA0")));
	}
	
	@Test
	public void testLogin() {
		
		// Try to login with a non-existant user
		assertFalse(userFacade.login(new Login_Params("beef", "beefy")));
		
		// Login with a newly created user
		assertTrue(userFacade.register(new Login_Params("beef", "beefy")));
		assertTrue(userFacade.login(new Login_Params("beef", "beefy")));
		
		// Test caps sensitivity
		assertFalse(userFacade.login(new Login_Params("BEEF", "beefy")));
		assertFalse(userFacade.login(new Login_Params("beef", "BeeFy")));
		
		// Create multiple users and log them all in
		assertTrue(userFacade.register(new Login_Params("one", "testone")));
		assertTrue(userFacade.register(new Login_Params("two", "testtwo")));
		assertTrue(userFacade.register(new Login_Params("three", "testthree")));
		assertTrue(userFacade.register(new Login_Params("four", "testfour")));
		
		assertTrue(userFacade.login(new Login_Params("three", "testthree")));
		assertTrue(userFacade.login(new Login_Params("one", "testone")));
		assertTrue(userFacade.login(new Login_Params("four", "testfour")));
		assertTrue(userFacade.login(new Login_Params("beef", "beefy")));
		assertTrue(userFacade.login(new Login_Params("two", "testtwo")));
	}

}
