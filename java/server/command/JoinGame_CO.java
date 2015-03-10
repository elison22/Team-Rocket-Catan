package command;

import shared.dto.JoinGame_Params;

public class JoinGame_CO implements ICommand {
	
	JoinGame_Params params;
	
	public JoinGame_CO(JoinGame_Params params) {
		this.params = params;
	}

	@Override
	public Object execute() {
		return null;
	}

}
