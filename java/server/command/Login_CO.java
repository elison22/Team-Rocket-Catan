package command;

import model.sgame.ServerGame;
import shared.dto.Login_Params;
import user.model.UserManager;

/**
 * @author Chad
 *
 * Makes all necessary calls to log a user in.
 */

public class Login_CO implements ICommandObject {
	
	private Login_Params params;
	private UserManager userManager;

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
	public void setGame(ServerGame game) {}

}
