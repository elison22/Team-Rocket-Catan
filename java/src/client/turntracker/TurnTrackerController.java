package client.turntracker;

import java.util.Observable;
import java.util.Observer;

import model.game.TurnState;
import model.player.Player;
import client.base.Controller;
import client.data.PlayerInfo;
import facade.ClientFacade;

/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {
	
	private ClientFacade facade;
	private PlayerInfo playerInfo;

	public TurnTrackerController(ITurnTrackerView view, ClientFacade fac) {
		super(view);
		fac.addObserver(this);
		facade = fac;
		initFromModel();
	}
	
	@Override
	public ITurnTrackerView getView() {
		
		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		if(facade.canFinishTurn())
			facade.finishTurn();
	}
	
	private void initFromModel() {
		playerInfo = facade.getPlayerInfo();
		getView().setLocalPlayerColor(playerInfo.getColor());
		getView().initializePlayer(playerInfo.getPlayerIndex(), playerInfo.getName(), playerInfo.getColor());

	}

	@Override
	public void update(Observable o, Object arg) {
		facade = (ClientFacade)o;
		TurnState turnState = facade.getGameState();
		updateGameState(turnState);
		updatePlayer();
	}
	
	public void updateGameState(TurnState turnState) {
		switch(turnState) {
			case Rolling:
				if(facade.isYourTurn())
					getView().updateGameState("Roll the Dice", false);
				else
					getView().updateGameState("Not Your Turn", false);
				break;
			case Robbing:
				if(facade.isYourTurn())
					getView().updateGameState("Place the Robber", false);
				else
					getView().updateGameState("Not Your Turn", false);
				break;
			case Playing:
				if(facade.isYourTurn())
					getView().updateGameState("Finish Turn", true);
				else
					getView().updateGameState("Not Your Turn", false);
				break;
			case Discarding:
				if(facade.isYourTurn())
					getView().updateGameState("Discard Cards", false);
				else
					getView().updateGameState("Not Your Turn", false);
				break;
			case FirstRound:
				if(facade.isYourTurn())
					getView().updateGameState("Place First Settlement", false);
				else
					getView().updateGameState("Not Your Turn", false);
				break;
			case SecondRound:
				if(facade.isYourTurn())
					getView().updateGameState("Place Second Settlement", false);
				else
					getView().updateGameState("Not Your Turn", false);
				break;
		}
	}
	
	public void updatePlayer() {
		playerInfo = facade.getPlayerInfo();
		Player player = facade.getLocalPlayer();
		getView().updatePlayer(player.getPlayerIdx(), player.getVictoryPoints(), facade.isYourTurn(), facade.hasLargestArmy(), facade.hasLongestRoad());
	}

}

