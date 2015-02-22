package client.join;

import java.util.Observable;
import java.util.Observer;

import model.game.TurnState;
import shared.definitions.CatanColor;
import shared.dto.Player_DTO;
import client.base.Controller;
import client.data.PlayerInfo;
import facade.ClientFacade;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {
	
	private ClientFacade modelFacade;
	int playersInGame;

	public PlayerWaitingController(IPlayerWaitingView view, ClientFacade modelFacade) {

		super(view);
		this.modelFacade = modelFacade;
		this.modelFacade.addObserver(this);
		playersInGame = 1;
	}
	
	// Retrieves the list of players from the facade and converts them to an
	// array of PlayerInfo that the view can use.
	private void setPlayers() {
		
		// Retrieve list of players
		Player_DTO[] players = modelFacade.getPlayerList();
		
		// Convert each player to a PlayerInfo object and add it to an array
		PlayerInfo[] playerInfoList = new PlayerInfo[players.length];
		for (int i = 0; i < players.length; ++i) {
			playerInfoList[i] = new PlayerInfo();
			playerInfoList[i].setColor(CatanColor.convert(players[i].getColor()));
			playerInfoList[i].setName(players[i].getName());
			playerInfoList[i].setId(players[i].getId());
		}
		
		// Set the current number of players to the size of the PlayerInfo array
		playersInGame = playerInfoList.length;
		
		// Set the view to reflect the new list of players
		getView().setPlayers(playerInfoList);
	}
	
	// Refreshes the view by closing and reopening it.
	private void refreshView() {
		getView().closeModal();
		getView().showModal();
	}

	@Override
	public IPlayerWaitingView getView() {
		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {

		// Retrieve the list of valid AI types
		getView().setAIChoices(modelFacade.getAIList());
		
		// Set the current number of players in the game
		setPlayers();
		
		// If there are less than 4 players in the game, wait for more
		if (playersInGame < 4)
			getView().showModal();
	}

	@Override
	public void addAI() {
		
		// Add selected AI type
		if (modelFacade.doAddAI(getView().getSelectedAI())) {
			
			// Update player list
			setPlayers();
			
			// Refresh view or leave it closed if 4 players have joined
			if (getView().isModalShowing()) {
				getView().closeModal();
			}
			if (playersInGame < 4)
				getView().showModal();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		// If PlayerWaiting view isn't showing, don't do anything
		if (getView().isModalShowing()) {
			playersInGame = modelFacade.getPlayersOfGame().size();
			
			// Close modal if ready to start, otherwise update player list
			if(playersInGame == 4 && modelFacade.getState() == TurnState.FirstRound)
				getView().closeModal();
			else if(playersInGame < 4) {
				setPlayers();
				refreshView();
			}
		}
		
	}

}

