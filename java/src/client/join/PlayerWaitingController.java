package client.join;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.CatanColor;
import shared.dto.Player;
import client.base.*;
import client.data.PlayerInfo;
import facade.IClientFacade;


/**
 * Implementation for the player waiting controller
 */
public class PlayerWaitingController extends Controller implements IPlayerWaitingController, Observer {
	
	private IClientFacade modelFacade;
	int playersInGame;

	public PlayerWaitingController(IPlayerWaitingView view, IClientFacade modelFacade) {

		super(view);
		this.modelFacade = modelFacade;
		playersInGame = 1;
	}
	
	private PlayerInfo[] getPlayers(Player[] players) {
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
		// TODO Auto-generated method stub
		
	}

}

