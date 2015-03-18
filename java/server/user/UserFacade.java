package user;

import command.ICommandObject;
import command.Login_CO;
import shared.dto.*;
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
		// TODO Auto-generated method stub
		return false;
	}

}
