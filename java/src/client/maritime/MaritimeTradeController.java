package client.maritime;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import shared.definitions.PortType;
import shared.definitions.ResourceType;
import client.base.Controller;
import facade.ClientFacade;


/**
 * Implementation for the maritime trade controller
 */
public class MaritimeTradeController extends Controller implements IMaritimeTradeController, Observer {

	private IMaritimeTradeOverlay tradeOverlay;
	private ClientFacade modelFacade;
	
	private HashMap<ResourceType,Integer> resCards;
	private HashSet<PortType> playerPorts;
	private ResourceType[] possibleGives;
	private ResourceType[] possibleGets;
	
	ResourceType resourceToGive;
	ResourceType resourceToGet;
	int ratio;
	
	private final int TWOtoONE = 2;
	private final int THREEtoONE = 3;
	private final int FOURtoONE = 4;
	
	public MaritimeTradeController(IMaritimeTradeView tradeView, IMaritimeTradeOverlay tradeOverlay, ClientFacade facade) {
		super(tradeView);
		modelFacade = facade;
		facade.addObserver(this);
		setTradeOverlay(tradeOverlay);
	}
	
	public IMaritimeTradeView getTradeView() {
		
		return (IMaritimeTradeView)super.getView();
	}
	
	public IMaritimeTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IMaritimeTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}
	
	private void initPossibleGets() {
		possibleGets = new ResourceType[5];
		
		int i = 0;
		for (ResourceType t : ResourceType.values()) {
			if (t != resourceToGive && modelFacade.getBankResCards().get(t) > 0)
				possibleGets[i] = t;
			i++;
		}
		
		getTradeOverlay().showGetOptions(possibleGets);
	}
	
	private void initPossibleGives() {
		possibleGives = new ResourceType[5];
		resCards = modelFacade.getPlayerResources();
		playerPorts = modelFacade.getPlayerPorts();
		
		// Check for ports, then for right amount of resources
		for (PortType p : playerPorts) {
			switch (p) {
			
			case BRICK:		
				if (hasResources(ResourceType.BRICK, TWOtoONE)) {
					possibleGives[1] = ResourceType.BRICK;
				}
				break;
			case ORE:
				if (hasResources(ResourceType.ORE, TWOtoONE)) {
					possibleGives[4] = ResourceType.ORE;
				}
				break;
			case SHEEP:
				if (hasResources(ResourceType.SHEEP, TWOtoONE)) {
					possibleGives[2] = ResourceType.SHEEP;
				}
				break;
			case WHEAT:
				if (hasResources(ResourceType.WHEAT, TWOtoONE)) {
					possibleGives[3] = ResourceType.WHEAT;
				}
				break;
			case WOOD:
				if (hasResources(ResourceType.WOOD, TWOtoONE)) {
					possibleGives[0] = ResourceType.WOOD;
				}
				break;
			case THREE_FOR_ONE:
				checkGenericTrades(THREEtoONE);
				break;
			}
		}
		
		// Check if player can do maritime trade without ports
		checkGenericTrades(FOURtoONE);
		
		// Enable/Disable possible trades
		getTradeOverlay().showGiveOptions(possibleGives);
	}
	
	private void checkGenericTrades(int amount) {
		int i = 0;
		for (ResourceType t : ResourceType.values()) {
			if (hasResources(t, amount)) {
				possibleGives[i] = t;
			}
			i++;
		}
	}
	
	private boolean hasResources(ResourceType t, int amount) {
		if (resCards.get(t) >= amount)
			return true;
		return false;
	}

	@Override
	public void startTrade() {
		getTradeOverlay().setTradeEnabled(false);
		
		if(!modelFacade.canPlayerTrade())
			return;
		else 
			initPossibleGives();
		
		if (!getTradeOverlay().isModalShowing())
			getTradeOverlay().showModal();
	}

	@Override
	public void makeTrade() {
		if (modelFacade.canMaritimeTrade(resourceToGive, resourceToGet, ratio)) {
			if (!modelFacade.doMaritimeTrade(resourceToGive, resourceToGet, ratio))
				getTradeOverlay().showDialog("Maritime trade failed!");
		}
		
		getTradeOverlay().hideGiveOptions();
		getTradeOverlay().hideGetOptions();
		getTradeOverlay().closeModal();
	}

	@Override
	public void cancelTrade() {

		getTradeOverlay().closeModal();
	}

	@Override
	public void setGetResource(ResourceType resource) {
		resourceToGet = resource;
		
		getTradeOverlay().selectGetOption(resource, 1);
		
		if (modelFacade.canMaritimeTrade(resourceToGive, resourceToGet, ratio)) {
			getTradeOverlay().setTradeEnabled(true);
		}
	}

	@Override
	public void setGiveResource(ResourceType resource) {
		resourceToGive = resource;
		
		if (playerPorts.contains(PortType.convertResourceType(resource))) {
			getTradeOverlay().selectGiveOption(resource, TWOtoONE);
			ratio = TWOtoONE;
		} else if (playerPorts.contains(PortType.THREE_FOR_ONE)) {
			getTradeOverlay().selectGiveOption(resource, THREEtoONE);
			ratio = THREEtoONE;
		} else {
			getTradeOverlay().selectGiveOption(resource, FOURtoONE);
			ratio = FOURtoONE;
		}
		
		initPossibleGets();
	}

	@Override
	public void unsetGetValue() {
		getTradeOverlay().showGetOptions(possibleGets);
		getTradeOverlay().setTradeEnabled(false);
		
	}

	@Override
	public void unsetGiveValue() {
		getTradeOverlay().showGiveOptions(possibleGives);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

