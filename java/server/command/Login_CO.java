package command;

import facade.GameManager;
import model.sgame.ServerGame;
import shared.dto.Login_Params;
import user.model.UserManager;

/**
 * @author Chad
 *
 * Makes all necessary calls to log a user in.
 */

public class Login_CO implements ICommandObject {
	
	private String type;
	private Login_Params loginParams;
	private UserManager userManager;

	/**
	 * @param params Parameters needed to log a user in.
	 */
	public Login_CO(UserManager userManager, Login_Params params) {
		super();
		type = "Login";
		this.userManager = userManager;
		this.loginParams = params;
	}

	@Override
	public boolean execute() {
		if (userManager.hasUser(loginParams.getUser()))
			return userManager.checkPassword(loginParams.getUser(), loginParams.getPassword());
		return false;
	}

	@Override
	public void setGame(ServerGame game) {}

	@Override
	public void setGameManager(GameManager gameManager) {}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
