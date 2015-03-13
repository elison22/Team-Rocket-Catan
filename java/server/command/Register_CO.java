package command;

import shared.dto.Login_Params;
import user.IUserFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls to register a new user.
 */

@SuppressWarnings("unused")
public class Register_CO implements ICommandObject {
	
	private IUserFacade userFacade;
	private Login_Params params; // Parameters needed are the same as Login_CO.

	/**
	 * @param userFacade Implementation of IUserfacade to be used.
	 * @param params Parameters needed to register a new user. They are the
	 * same parameters needed to log an existing user in.
	 */
	public Register_CO(IUserFacade userFacade, Login_Params params) {
		super();
		this.userFacade = userFacade;
		this.params = params;
	}

	@Override
	public Object execute() {
		// TODO Auto-generated method stub
		return null;
	}

}
