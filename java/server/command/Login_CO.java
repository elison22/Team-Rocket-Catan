package command;

import shared.dto.Login_Params;
import user.IUserFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls to log a user in.
 */

@SuppressWarnings("unused")
public class Login_CO implements ICommandObject {
	
	private Login_Params params;

	/**
	 * @param params Parameters needed to log a user in.
	 */
	public Login_CO(Login_Params params) {
		super();
		this.params = params;
	}

	@Override
	public void execute() {
	}

}
