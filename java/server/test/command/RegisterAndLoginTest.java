package test.command;

import static org.junit.Assert.*;

import org.junit.Test;

import shared.dto.Login_Params;
import user.model.UserManager;
import command.Login_CO;
import command.Register_CO;

public class RegisterAndLoginTest {
	
	Register_CO regCO;
	Login_CO loginCO;

	@Test
	public void testRegister() {
		// Test a password that's too short
		regCO = new Register_CO(new UserManager(), new Login_Params("test", "test"));
		assertFalse(regCO.execute());
		
		// Username that's too short
		regCO = new Register_CO(new UserManager(), new Login_Params("te", "testing"));
		assertFalse(regCO.execute());
		
		// Username and Password that's too long
		regCO = new Register_CO(new UserManager(), new Login_Params("tooolong", "testing"));
		assertFalse(regCO.execute());
		regCO = new Register_CO(new UserManager(), new Login_Params("test", "veryveryveryverymuchtoolong"));
		assertFalse(regCO.execute());
		
		// Username that's been taken
		UserManager userManager = new UserManager();
		regCO = new Register_CO(userManager, new Login_Params("test", "testing"));
		assertTrue(regCO.execute());
		regCO = new Register_CO(userManager, new Login_Params("test", "testing"));
		assertFalse(regCO.execute());
		
		// Username and Password with invalid characters
		regCO = new Register_CO(new UserManager(), new Login_Params("!test", "testing"));
		assertFalse(regCO.execute());
		regCO = new Register_CO(new UserManager(), new Login_Params("test", "test!!"));
		assertFalse(regCO.execute());
		
		// Username and Password with valid characters
		regCO = new Register_CO(new UserManager(), new Login_Params("_Ab-9", "testing"));
		assertTrue(regCO.execute());
		regCO = new Register_CO(new UserManager(), new Login_Params("test", "--__bA0"));
		assertTrue(regCO.execute());
	}

}
