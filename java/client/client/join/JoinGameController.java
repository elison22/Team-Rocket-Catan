package client.join;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.dto.Game_DTO;
import shared.dto.Player_DTO;
import client.base.*;
import client.data.*;
import client.misc.*;
import facade.ClientFacade;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController, Observer {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private ClientFacade modelFacade;
	private GameInfo joinGameInfo;
	
	/**
	 * JoinGameController constructor
	 * 
	 * @param view Join game view
	 * @param newGameView New game view
	 * @param selectColorView Select color view
	 * @param messageView Message view (used to display error messages that occur while the user is joining a game)
	 */
	public JoinGameController(IJoinGameView view, INewGameView newGameView, 
								ISelectColorView selectColorView, IMessageView messageView, 
								ClientFacade modelFacade) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
		
		this.modelFacade = modelFacade;
		modelFacade.addObserver(this);
	}
	
	public IJoinGameView getJoinGameView() {
		
		return (IJoinGameView)super.getView();
	}
	
	/**
	 * Returns the action to be executed when the user joins a game
	 * 
	 * @return The action to be executed when the user joins a game
	 */
	public IAction getJoinAction() {
		
		return joinAction;
	}

	/**
	 * Sets the action to be executed when the user joins a game
	 * 
	 * @param value The action to be executed when the user joins a game
	 */
	public void setJoinAction(IAction value) {	
		
		joinAction = value;
	}
	
	public INewGameView getNewGameView() {
		
		return newGameView;
	}

	public void setNewGameView(INewGameView newGameView) {
		
		this.newGameView = newGameView;
	}
	
	public ISelectColorView getSelectColorView() {
		
		return selectColorView;
	}
	public void setSelectColorView(ISelectColorView selectColorView) {
		
		this.selectColorView = selectColorView;
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	public void setMessageView(IMessageView messageView) {
		
		this.messageView = messageView;
	}
	
	// Take game list from facade and turn it into GameInfo list that the
	// JoinGameView can parse
	private void setGameList() {
		ArrayList<GameInfo> gameInfoList = new ArrayList<GameInfo>();
		
		for (Game_DTO gameDTO : modelFacade.getGames()) {
			
			// Convert game to GameInfo's format
			GameInfo gameInfo = new GameInfo();
			gameInfo.setId(gameDTO.getId());
			gameInfo.setTitle(gameDTO.getTitle());
			
			// Add players
			int index = 0;
			for (Player_DTO playerDTO : gameDTO.getPlayers()) {
				if (playerDTO.getName() == null)
					continue;
				
				PlayerInfo playerInfo = new PlayerInfo();
				playerInfo.setId(playerDTO.getId());
				playerInfo.setPlayerIndex(index);
				playerInfo.setName(playerDTO.getName());
				if (playerDTO.getColor() != null)
					playerInfo.setColor(CatanColor.convert(playerDTO.getColor()));
				
				gameInfo.addPlayer(playerInfo);
				++index;
			}
			
			// Add game to list
			gameInfoList.add(gameInfo);
		}
		
		// Convert to array
		GameInfo[] gameInfoArray = new GameInfo[gameInfoList.size()];
		gameInfoArray = gameInfoList.toArray(gameInfoArray);
		
		// Set view's game list
		getJoinGameView().setGames(gameInfoArray, getLocalPlayer(modelFacade.getLocalPlayerInfo()));
	}
	
	// Converts the given local PlayerDTO to a PlayerInfo object that the view
	// can use
	private PlayerInfo getLocalPlayer(Player_DTO player) {
		PlayerInfo localPlayer = new PlayerInfo();
		
		// If the player has an assigned color, retrieve it. Otherwise leave it
		// null.
		if (player.getColor() != null)
			localPlayer.setColor(CatanColor.convert(player.getColor()));
		
		localPlayer.setName(player.getName());
		localPlayer.setId(player.getId());
		
		return localPlayer;
	}
	
	// Determines which colors have already been chosen/assigned to other
	// players and disables them
	private void disableUsedColors() {
		enableColors();
		
		// Retrieve local player's id- since the player gets to rechoose their
		// color when they join we don't want to disable their old color
		int localPlayerID = modelFacade.getLocalPlayerInfo().getId();
		
		for (PlayerInfo p : joinGameInfo.getPlayers()) {
			
			// Make sure not to disable the localPlayer's old color
			if (p.getColor() != null && p.getId() != localPlayerID)
				getSelectColorView().setColorEnabled(p.getColor(), false);
		}
	}
	
	private void enableColors() {
		for (CatanColor color : CatanColor.values()) {
			getSelectColorView().setColorEnabled(color, true);
		}
	}

	@Override
	public void start() {
		
		// Update game list
		setGameList();
		
		getSelectColorView().resetColorButtons();
		getJoinGameView().showModal();
	}

	@Override
	public void startCreateNewGame() {
		getNewGameView().showModal();
	}

	@Override
	public void cancelCreateNewGame() {
		getNewGameView().closeModal();
	}

	@Override
	public void createNewGame() {
		
		if (!getNewGameView().getTitle().isEmpty()) {
			
			// Check that the game name is valid (alphanumeric under 25 chars)
			String namePattern = "^[0-9a-zA-Z ]{1,25}$";
			if (!getNewGameView().getTitle().matches(namePattern)) {
				getNewGameView().showDialog("Game titles must consist of alphanumeric " + 
											"characters and be under 25 characters long.");
				return;
			}
			
			// Create new game
			if (modelFacade.CreateGame(getNewGameView().getTitle(), 
				       getNewGameView().getRandomlyPlaceHexes(), 
				       getNewGameView().getUseRandomPorts(), 
				       getNewGameView().getRandomlyPlaceNumbers())) {
				
				// If successful, update game list
				setGameList();
				
				getNewGameView().closeModal();
			} else getNewGameView().showDialog("Failed to create game!");
		} else getNewGameView().showDialog("Please type in a game title.");
	}

	@Override
	public void startJoinGame(GameInfo game) {
		
		// Remember the selected game
		joinGameInfo = game;
		
		// Disable unavailable colors
		disableUsedColors();
		
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		
		if (getSelectColorView().isModalShowing())
			getSelectColorView().closeModal();
					
		if (getJoinGameView().isModalShowing())
			getJoinGameView().closeModal();
		
		// Attempt to the selected game with the selected color
		if (modelFacade.joinGame(joinGameInfo.getId(), color.toString())) {
			// Success?
			joinAction.execute();
		} else {
			// Failed?
			getJoinGameView().showDialog("Failed to join game!");
			getJoinGameView().showModal();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		
		// Only update if the player is viewing the game list and isn't trying
		// to create a game or join a selected game
		if (getJoinGameView().isModalShowing() && !getNewGameView().isModalShowing() 
											   && !getSelectColorView().isModalShowing()) {
			getJoinGameView().closeModal();
			setGameList();
			getJoinGameView().showModal();
		} else if (arg != null && arg.toString().equals("RESET")) {
			newGameView = new NewGameView("NewGame");
			newGameView.setController(this);
			
			setSelectColorView(new SelectColorView("SelectColor"));
			getSelectColorView().setController(this);
			start();
		}
		
	}

}

