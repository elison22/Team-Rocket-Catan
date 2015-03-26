package command;

import model.sgame.ServerGame;
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
		if (userManager.hasUser(params.getUser()))
			return userManager.checkPassword(params.getUser(), params.getPassword());
		return false;
	}

	@Override
	public void setGame(ServerGame game) {
		// TODO Auto-generated method stub
		
	}

}
