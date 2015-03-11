package command;

import facade.IModelFacade;

/**
 * @author Chad
 *
 * This class retrieves a current list of all the games stored in the server,
 * including the players in each game and their colors.
 */
@SuppressWarnings("unused")
public class List_CO implements ICommandObject {
	
	private IModelFacade modelFacade;
	
	/**
	 * @param modelFacade The implementation of IModelFacde to be used.
	 */
	public List_CO(IModelFacade modelFacade) {
		this.modelFacade = modelFacade;
	}

	@Override
	public Object execute() {
		// Query modelfacade for a list of the currently running games.
		return null;
	}
}
