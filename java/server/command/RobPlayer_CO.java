package command;

import model.sboard.ServerBoardException;
import model.sgame.ServerGame;
import shared.definitions.ResourceType;
import shared.dto.RobPlayer_Params;
import facade.IModelFacade;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to rob another player in a given game.
 */
@SuppressWarnings("unused")
public class RobPlayer_CO implements ICommandObject {
	
	private ServerGame game;
	private RobPlayer_Params params;
	private ResourceType stolenResource;
	private boolean beenCalled;

	/**
	 * @param game
	 * @param params Parameters need for a player to rob another player.
	 */
	public RobPlayer_CO(RobPlayer_Params params, ServerGame game) {
		super();
		this.game = game;
		this.params = params;
		stolenResource = null;
		beenCalled = false;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {
        try {
            stolenResource = game.doPlaceRobber(params.getPlayerIndex(), params.getVictimIndex(), params.getTargetLocation(), stolenResource, beenCalled);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
        beenCalled = true;
    	return true;
	}

}
