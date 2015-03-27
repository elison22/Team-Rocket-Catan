package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.Login_Params;
import user.model.UserManager;

/**
 * @author Chad
 *
 * Makes all necessary calls to register a new user.
 */

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
		
		if (!userManager.hasUser(params.getUser())) {
			return userManager.createNewUser(params.getUser(), params.getPassword());
		}
		
		return false;
	}

	@Override
	public void setGame(ServerGame game) {}

	@Override
	public void setGameManager(GameManager gameManager) {}
}
