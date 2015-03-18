package command;

import shared.dto.Login_Params;
import user.IUserFacade;
import user.model.UserManager;

/**
 * @author Chad
 *
 * Makes all necessary calls to register a new user.
 */

@SuppressWarnings("unused")
public class Register_CO implements ICommandObject {
	
	private UserManager userManager;
	private Login_Params params; // Parameters needed are the same as Login_CO.

	/**
	 * @param params Parameters needed to register a new user. They are the
	 * same parameters needed to log an existing user in.
	 */
	public Register_CO(UserManager userManager, Login_Params params) {
		super();
		this.userManager = userManager;
		this.params = params;
	}

	@Override
	public boolean execute() {
		String userPattern = "^[0-9a-zA-Z_-]{3,7}$";
		String passwordPattern = "^[0-9a-zA-Z_-]{5,25}$";
		
		if (params.getUser().matches(userPattern)) {
			if (!userManager.hasUser(params.getUser())) {
				if (params.getPassword().matches(passwordPattern)) {
					userManager.createNewUser(params.getUser(), params.getPassword());
					return true;
				}
			}
		}
		return false;
	}
}
