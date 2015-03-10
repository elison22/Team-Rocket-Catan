package command;

import shared.dto.CreateGame_Params;

public class CreateGame_CO implements ICommand {
	
	@SuppressWarnings("unused")
	private CreateGame_Params params;
	
	public CreateGame_CO(CreateGame_Params params) {
		this.params = params;
	}

	@Override
	public Object execute() {
		// Request the modelfacade to make a new game with the given params
		return null;
	}

}
