package client.discard;

import java.util.HashMap;
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
    private boolean maxed = false;
    private HashMap<ResourceType, Integer> discardRes;
    private HashMap<ResourceType, Integer> playerRes;
    private int totalRes;
    private int count;

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

        discardRes.put(resource, discardRes.get(resource) + 1);
        count++;
        updateMessage();
        getDiscardView().setResourceDiscardAmount(resource, discardRes.get(resource));
        if (discardRes.get(resource) == playerRes.get(resource))
            getDiscardView().setResourceAmountChangeEnabled(resource, false, true);
        else getDiscardView().setResourceAmountChangeEnabled(resource, true, true);
        if (count == totalRes/2) maxAll();

	}

	@Override
	public void decreaseAmount(ResourceType resource) {

        discardRes.put(resource, discardRes.get(resource) -1);
        count--;
        updateMessage();
        getDiscardView().setResourceDiscardAmount(resource, discardRes.get(resource));
        if (discardRes.get(resource) == 0)
            getDiscardView().setResourceAmountChangeEnabled(resource, true, false);
        else getDiscardView().setResourceAmountChangeEnabled(resource, true, true);
        if (maxed) unmaxAll();

	}

	@Override
	public void discard() {
//        assert facade.canDiscardCards(discardRes);
        getDiscardView().closeModal();
        hasDiscarded = true;
        facade.doDiscardCards(discardRes);
	}

	@Override
	public void update(Observable o, Object arg) {

        if (facade.getState() != TurnState.Discarding) {
            hasDiscarded = false;
            return;
        }
        if (getDiscardView().isModalShowing()) return;

        int total = 0;
        for (int i : facade.getPlayerResources().values()) {
            total += i;
        }
        if (total > 7) {
            if (!hasDiscarded && !getDiscardView().isModalShowing()){
                getDiscardView().showModal();
                start(total);
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

    private void start(int total) {
        count = 0;
        totalRes = total;
        discardRes = new HashMap<ResourceType, Integer>();
        playerRes = facade.getPlayerResources();
        getDiscardView().setDiscardButtonEnabled(false);
        updateMessage();
        setupResource(ResourceType.WHEAT);
        setupResource(ResourceType.ORE);
        setupResource(ResourceType.SHEEP);
        setupResource(ResourceType.WOOD);
        setupResource(ResourceType.BRICK);

    }

    private void setupResource(ResourceType type) {
        getDiscardView().setResourceMaxAmount(type, playerRes.get(type));
        getDiscardView().setResourceDiscardAmount(type, 0);
        discardRes.put(type, 0);
        if (playerRes.get(type) == 0)
            getDiscardView().setResourceAmountChangeEnabled(type, false, false);
        else
            getDiscardView().setResourceAmountChangeEnabled(type, true, false);
    }

    private void maxAll() {

        maxed = true;
        getDiscardView().setDiscardButtonEnabled(true);
        maxRes(ResourceType.WHEAT);
        maxRes(ResourceType.ORE);
        maxRes(ResourceType.SHEEP);
        maxRes(ResourceType.WOOD);
        maxRes(ResourceType.BRICK);

    }

    private void maxRes(ResourceType type) {

        if (discardRes.get(type) == 0)
            getDiscardView().setResourceAmountChangeEnabled(type, false, false);
        else
            getDiscardView().setResourceAmountChangeEnabled(type, false, true);

    }

    private void unmaxAll() {

        maxed = false;
        getDiscardView().setDiscardButtonEnabled(false);
        unmaxRes(ResourceType.WHEAT);
        unmaxRes(ResourceType.ORE);
        unmaxRes(ResourceType.SHEEP);
        unmaxRes(ResourceType.WOOD);
        unmaxRes(ResourceType.BRICK);
    }

    private void unmaxRes(ResourceType type) {

        boolean increase;
        boolean decrease;
        if (discardRes.get(type) == playerRes.get(type))
            increase = false;
        else increase = true;
        if (discardRes.get(type) == 0)
            decrease = false;
        else decrease = true;
        getDiscardView().setResourceAmountChangeEnabled(type, increase, decrease);
    }

    private void updateMessage() {
        getDiscardView().setStateMessage("Discard: " + Integer.toString(count) + "/" + Integer.toString(totalRes/2));
    }

}

