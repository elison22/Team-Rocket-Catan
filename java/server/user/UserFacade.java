package user;

import command.ICommandObject;
import command.Login_CO;
import command.Register_CO;
import shared.dto.Login_Params;
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
	public boolean login(Login_Params params) {
		ICommandObject commandObject = new Login_CO(userManager, params);
		return commandObject.execute();
	}

	@Override
	public boolean register(Login_Params params) {
		ICommandObject commandObject = new Register_CO(userManager, params);
		return commandObject.execute();
	}

	@Override
	public String getUserID(String username) {
		return new Integer(userManager.getUser(username).getId()).toString();
	}

	@Override
	public boolean hasUser(String username) {
		return userManager.hasUser(username);
	}
}