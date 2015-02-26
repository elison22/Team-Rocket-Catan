package client.discard;

import java.util.Observable;
import java.util.Observer;

import model.game.TurnState;
import shared.definitions.ResourceType;
import client.base.Controller;
import client.misc.IWaitView;
import facade.ClientFacade;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IWaitView waitView;
	private ClientFacade facade;
    private boolean hasDiscarded = false;
	
	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView, ClientFacade facade) {
		super(view);
		this.facade = facade;
		facade.addObserver(this);
		this.waitView = waitView;
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}
	
	public IWaitView getWaitView() {
		return waitView;
	}

	@Override
	public void increaseAmount(ResourceType resource) {
		getDiscardView();
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		
	}

	@Override
	public void discard() {
		
		getDiscardView().closeModal();
	}

	@Override
	public void update(Observable o, Object arg) {

        if (facade.getState() != TurnState.Discarding) return;
        if (getDiscardView().isModalShowing()) return;

        int totalRes = 0;
        for (int i : facade.getPlayerResources().values()) {
            totalRes += i;
        }
        if (totalRes > 7) {
            if (!hasDiscarded && !getDiscardView().isModalShowing()){
                getDiscardView().showModal();

            }
            else if (hasDiscarded && !getWaitView().isModalShowing()){
                getWaitView().showModal();
            }
        }
        else
            if (!getWaitView().isModalShowing()) {
                getWaitView().showModal();
            }
	}

}

