package user;

import user.model.UserManager;

/**
 * @author Chad
 *
 * This class handles all calls that require accessing user data from the user
 * manager.
 */
public class UserFacade implements IUserFacade {
	
	UserManager userManager;
	
	public UserFacade() {
		userManager = new UserManager();
	}

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
