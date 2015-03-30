package test.user;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import user.model.UserManager;

public class UserManagerTest {
	
	UserManager userManager;

	@Before
	public void setUp() throws Exception {
		userManager = new UserManager();
	}

	@Test
	public void test() {
		// Test that users are properly assigned unique ids
		userManager.createNewUser("beef", "beefy");
		userManager.createNewUser("beef1", "beefy");
		userManager.createNewUser("beef2", "beefy");
		
		assertTrue(userManager.getUser("beef2").getId() == 2);
		assertTrue(userManager.getUser("beef1").getId() == 1);
		assertTrue(userManager.getUser("beef").getId() == 0);
		
		// Test that the userManager can handle large amounts of users
		for (int i = 0; i <= 50000; ++i) {
			userManager.createNewUser(new String("be" + i), "beefy");
		}
		assertTrue(userManager.getUser("be50000").getId() == 50003);
	}

}
