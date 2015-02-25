package client.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.ResourceType;
import client.base.Controller;
import client.base.IAction;
import facade.ClientFacade;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController, Observer {

	private Map<ResourceBarElement, IAction> elementActions;
	private ClientFacade modelFacade;
	
	public ResourceBarController(IResourceBarView view, ClientFacade facade) {
		super(view);
		modelFacade = facade;
		facade.addObserver(this);
		elementActions = new HashMap<ResourceBarElement, IAction>();
	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {

		elementActions.put(element, action);
	}

	@Override
	public void buildRoad() {
		executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}
	
	private void executeElementAction(ResourceBarElement element) {
		
		if (elementActions.containsKey(element)) {
			
			IAction action = elementActions.get(element);
			action.execute();
		}
	}
	
	private void setResources() {
		
		HashMap<ResourceType,Integer> resCards = modelFacade.getPlayerResources();
		
		getView().setElementAmount(ResourceBarElement.BRICK, resCards.get(ResourceType.BRICK));
		getView().setElementAmount(ResourceBarElement.ORE, resCards.get(ResourceType.ORE));
		getView().setElementAmount(ResourceBarElement.SHEEP, resCards.get(ResourceType.SHEEP));
		getView().setElementAmount(ResourceBarElement.WHEAT, resCards.get(ResourceType.WHEAT));
		getView().setElementAmount(ResourceBarElement.WOOD, resCards.get(ResourceType.WOOD));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) return;
		
		setResources();
		
		if (modelFacade.canBuildRoad())
			getView().setElementEnabled(ResourceBarElement.ROAD, true);
		else
			getView().setElementEnabled(ResourceBarElement.ROAD, false);
		
		if (modelFacade.canBuildSettlement())
			getView().setElementEnabled(ResourceBarElement.SETTLEMENT, true);
		else
			getView().setElementEnabled(ResourceBarElement.SETTLEMENT, false);
		
		if (modelFacade.canBuildCity())
			getView().setElementEnabled(ResourceBarElement.CITY, true);
		else
			getView().setElementEnabled(ResourceBarElement.CITY, false);
		
		if (modelFacade.canBuyDevCard())
			getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
		else
			getView().setElementEnabled(ResourceBarElement.BUY_CARD, false);
	}

}

