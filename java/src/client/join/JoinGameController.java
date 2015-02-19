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
import facade.IClientFacade;


/**
 * Implementation for the join game controller
 */
public class JoinGameController extends Controller implements IJoinGameController, Observer {

	private INewGameView newGameView;
	private ISelectColorView selectColorView;
	private IMessageView messageView;
	private IAction joinAction;
	private IClientFacade modelFacade;
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
								IClientFacade modelFacade) {

		super(view);

		setNewGameView(newGameView);
		setSelectColorView(selectColorView);
		setMessageView(messageView);
		
		this.modelFacade = modelFacade;
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
		ArrayList<GameInfo> gameInfo = new ArrayList<GameInfo>();
		
		for (Game_DTO gL : modelFacade.getGames()) {
			GameInfo gI = new GameInfo();
			gI.setId(gL.getId());
			gI.setTitle(gL.getTitle());
			
			int index = 0;
			for (Player_DTO p : gL.getPlayers()) {
				if (p.getName() == null)
					continue;
				
				PlayerInfo pI = new PlayerInfo();
				pI.setId(p.getId());
				pI.setPlayerIndex(index);
				pI.setName(p.getName());
				if (p.getColor() != null)
					pI.setColor(CatanColor.convert(p.getColor()));
				
				gI.addPlayer(pI);
				++index;
			}
			gameInfo.add(gI);
		}
		
		GameInfo[] gameInfoArray = new GameInfo[gameInfo.size()];
		gameInfoArray = gameInfo.toArray(gameInfoArray);
		getJoinGameView().setGames(gameInfoArray, getLocalPlayer(modelFacade.getLocalPlayerInfo()));
	}
	
	private PlayerInfo getLocalPlayer(Player_DTO player) {
		PlayerInfo localPlayer = new PlayerInfo();
		if (player.getColor() != null)
			localPlayer.setColor(CatanColor.convert(player.getColor()));
		localPlayer.setName(player.getName());
		localPlayer.setId(player.getId());
		return localPlayer;
	}
	
	private void disableUsedColors() {
		int localPlayerID = modelFacade.getLocalPlayerInfo().getId();
		for (PlayerInfo p : joinGameInfo.getPlayers()) {
			if (p.getColor() != null && p.getId() != localPlayerID)
				getSelectColorView().setColorEnabled(p.getColor(), false);
		}
	}

	@Override
	public void start() {
		setGameList();
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
		getNewGameView().closeModal();
		
		// Create new game
		if (modelFacade.CreateGame(getNewGameView().getTitle(), 
							       getNewGameView().getRandomlyPlaceHexes(), 
							       getNewGameView().getUseRandomPorts(), 
							       getNewGameView().getRandomlyPlaceNumbers())) {
			// If successful, update game list
			setGameList();
		}
	}

	@Override
	public void startJoinGame(GameInfo game) {
		
		joinGameInfo = game;
		disableUsedColors();
		getSelectColorView().showModal();
	}

	@Override
	public void cancelJoinGame() {
	
		getJoinGameView().closeModal();
	}

	@Override
	public void joinGame(CatanColor color) {
		System.out.println(color.toString());
		if (modelFacade.joinGame(joinGameInfo.getId(), color.toString())) {
			// If join succeeded
			getSelectColorView().closeModal();
			getJoinGameView().closeModal();
			joinAction.execute();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

