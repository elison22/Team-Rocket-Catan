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
	
	private IUserFacade userFacade;
	private Login_Params params;

	/**
	 * @param userFacade Implementation of IUserfacade to be used.
	 * @param params Parameters needed to log a user in.
	 */
	public Login_CO(IUserFacade userFacade, Login_Params params) {
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
