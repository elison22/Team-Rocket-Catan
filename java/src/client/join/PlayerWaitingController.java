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
	
	private PlayerInfo[] getPlayers(Player_DTO[] players) {
		PlayerInfo[] playerInfoList = new PlayerInfo[players.length];
		for (int i = 0; i < players.length; ++i) {
			playerInfoList[i] = new PlayerInfo();
			playerInfoList[i].setColor(CatanColor.convert(players[i].getColor()));
			playerInfoList[i].setName(players[i].getName());
			playerInfoList[i].setId(players[i].getId());
		}
		playersInGame = playerInfoList.length;
		return playerInfoList;
	}

	@Override
	public IPlayerWaitingView getView() {

		return (IPlayerWaitingView)super.getView();
	}

	@Override
	public void start() {

		getView().setAIChoices(modelFacade.getAIList());
		getView().setPlayers(getPlayers(modelFacade.getPlayerList()));
		getView().showModal();
	}

	@Override
	public void addAI() {
		
		getView().setPlayers(getPlayers(modelFacade.doAddAI(getView().getSelectedAI())));
		if (getView().isModalShowing()) {
			getView().closeModal();
		}
		if (playersInGame < 4)
			getView().showModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		modelFacade = (ClientFacade)o;
		playersInGame = modelFacade.getPlayersOfGame().size();
		if(playersInGame == 4 && modelFacade.getGameState() == TurnState.FirstRound)
			getView().closeModal();
		else if(playersInGame < 4)
			getView().setPlayers(modelFacade.getPlayersInfos());
	}

}

