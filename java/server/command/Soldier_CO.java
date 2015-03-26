package command;

import model.sboard.ServerBoardException;
import model.sgame.ServerGame;
import shared.definitions.ResourceType;
import shared.dto.Soldier_Params;

/**
 * @author Chad
 *
 * Makes all necessary calls for a player to play the soldier card.
 */
@SuppressWarnings("unused")
public class Soldier_CO implements ICommandObject {
	
	private Soldier_Params params;
    private ServerGame game;
    private ResourceType stolenResource;
    private boolean beenCalled;

	/**
	 * @param params Parameters needed to play the soldier card.
	 */
	public Soldier_CO(ServerGame game, Soldier_Params params) {
		super();
		this.params = params;
        this.game = game;
        this.stolenResource = null;
        beenCalled = false;
	}
	
	public void setGame(ServerGame game)
	{
		this.game = game;
	}

	@Override
	public boolean execute() {

        try {
            stolenResource = game.doSoldier(params.getPlayerIndex(), params.getVictimIndex(), params.getLocation(), stolenResource, beenCalled);
        } catch (ServerBoardException e) {
            e.printStackTrace();
            return false;
        }
        beenCalled = true;
        return true;
	}

}
