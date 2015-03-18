package command;

import shared.dto.Login_Params;
import user.IUserFacade;
import user.model.UserManager;

/**
 * @author Chad
 *
 * Makes all necessary calls to log a user in.
 */

@SuppressWarnings("unused")
public class Login_CO implements ICommandObject {
	
	private UserManager userManager;
	private Login_Params params;

	/**
	 * @param params Parameters needed to log a user in.
	 */
	public Login_CO(UserManager userManager, Login_Params params) {
		super();
		this.userManager = userManager;
		this.params = params;
	}

	@Override
	public boolean execute() {
		
		return false;
	}

}
