package client.points;

import java.util.Observable;
import java.util.Observer;

import client.base.Controller;
import facade.ClientFacade;


/**
 * Implementation for the points controller
 */
public class PointsController extends Controller implements IPointsController, Observer {

	private IGameFinishedView finishedView;
	private ClientFacade modelFacade;
	
	/**
	 * PointsController constructor
	 * 
	 * @param view Points view
	 * @param finishedView Game finished view, which is displayed when the game is over
	 */
	public PointsController(IPointsView view, IGameFinishedView finishedView, ClientFacade facade) {
		super(view);
		
		setFinishedView(finishedView);
		modelFacade = facade;
		facade.addObserver(this);
	}
	
	public IPointsView getPointsView() {
		
		return (IPointsView)super.getView();
	}
	
	public IGameFinishedView getFinishedView() {
		return finishedView;
	}
	public void setFinishedView(IGameFinishedView finishedView) {
		this.finishedView = finishedView;
	}

	private void initFromModel() {
		getPointsView().setPoints(modelFacade.getLocalVictoryPoints());
		
		if (modelFacade.getWinner() == modelFacade.getLocalPlayer().getPlayerIdx())
			getFinishedView().setWinner(modelFacade.getLocalPlayer().getName(), true);
		else
			getFinishedView().setWinner(modelFacade.getPlayerList()[modelFacade.getWinner()].getName(), false);
		
		getFinishedView().showModal();
		
		modelFacade.resetGame();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) return;
		
		if (modelFacade.getWinner() > -1)
			initFromModel();
	}
	
}

