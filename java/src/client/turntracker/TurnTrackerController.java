package client.turntracker;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
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
	private boolean noneShallPass;

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
		//playerInfo = facade.getPlayerInfo();
		//getView().updateGameState("Waiting for other Players", false);
		//getView().setLocalPlayerColor(playerInfo.getColor());
		//getView().initializePlayer(playerInfo.getPlayerIndex(), playerInfo.getName(), playerInfo.getColor());

	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) return;
		
		if(facade.getPlayersOfGame().size() == 4)
			noneShallPass = true;
		
		if(!noneShallPass)
			return;
		
		playerInfo = facade.getPlayerInfo();
		TurnState turnState = facade.getState();
		
		for (Player p : facade.getPlayersOfGame()) {
			getView().initializePlayer(p.getPlayerIdx(), p.getName(), CatanColor.convert(p.getColor()));
			getView().updatePlayer(p.getPlayerIdx(), p.getVictoryPoints(), facade.isYourTurn(p.getPlayerIdx()), facade.hasLargestArmy(p.getPlayerIdx()), facade.hasLongestRoad(p.getPlayerIdx()));
		}
		getView().setLocalPlayerColor(playerInfo.getColor());

		updateGameState(turnState);
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

}

