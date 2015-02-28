package client.domestic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import model.trade.DomesticTrade;
import model.trade.TradeOffer;
import shared.definitions.ResourceType;
import client.base.Controller;
import client.misc.IWaitView;
import facade.ClientFacade;

/**
 * Domestic trade controller implementation
 */
public class DomesticTradeController extends Controller implements IDomesticTradeController, Observer {

	private IDomesticTradeOverlay tradeOverlay;
	private IWaitView waitOverlay;
	private IAcceptTradeOverlay acceptOverlay;
	private ClientFacade modelFacade;
	
	private DomesticTrade tradeOffer;
	
    private HashMap<ResourceType, Integer> cardsToTrade;
    
    private HashSet<ResourceType> give;
    private HashSet<ResourceType> receive;	
    
    private String SETTRADE = "set the trade you want to make";
    private String SENDTRADE = "Trade!";
    private boolean readyToTrade;

	/**
	 * DomesticTradeController constructor
	 * 
	 * @param tradeView Domestic trade view (i.e., view that contains the "Domestic Trade" button)
	 * @param tradeOverlay Domestic trade overlay (i.e., view that lets the user propose a domestic trade)
	 * @param waitOverlay Wait overlay used to notify the user they are waiting for another player to accept a trade
	 * @param acceptOverlay Accept trade overlay which lets the user accept or reject a proposed trade
	 */
	public DomesticTradeController(IDomesticTradeView tradeView, IDomesticTradeOverlay tradeOverlay,
									IWaitView waitOverlay, IAcceptTradeOverlay acceptOverlay, ClientFacade facade) {
		super(tradeView);
		modelFacade = facade;
		facade.addObserver(this);
		setTradeOverlay(tradeOverlay);
		setWaitOverlay(waitOverlay);
		setAcceptOverlay(acceptOverlay);
	}
	
	public IDomesticTradeView getTradeView() {
		
		return (IDomesticTradeView)super.getView();
	}

	public IDomesticTradeOverlay getTradeOverlay() {
		return tradeOverlay;
	}

	public void setTradeOverlay(IDomesticTradeOverlay tradeOverlay) {
		this.tradeOverlay = tradeOverlay;
	}

	public IWaitView getWaitOverlay() {
		return waitOverlay;
	}

	public void setWaitOverlay(IWaitView waitView) {
		this.waitOverlay = waitView;
	}

	public IAcceptTradeOverlay getAcceptOverlay() {
		return acceptOverlay;
	}

	public void setAcceptOverlay(IAcceptTradeOverlay acceptOverlay) {
		this.acceptOverlay = acceptOverlay;
	}

	@Override
	public void startTrade() {
		cardsToTrade = new HashMap<ResourceType, Integer>();
		cardsToTrade.put(ResourceType.BRICK, 0);
		cardsToTrade.put(ResourceType.ORE, 0);
		cardsToTrade.put(ResourceType.SHEEP, 0);
		cardsToTrade.put(ResourceType.WHEAT, 0);
		cardsToTrade.put(ResourceType.WOOD, 0);
		tradeOffer = new DomesticTrade(modelFacade.getLocalPlayerIndex());
		
		getTradeOverlay().setPlayers(modelFacade.getNonActivePlayerInfo());
		getTradeOverlay().reset();
		getTradeOverlay().showModal();
	}

	@Override
	public void decreaseResourceAmount(ResourceType resource) {
		int val = modelFacade.getLocalPlayer().getBank().getResCards().get(resource);
		Integer amount = new Integer(getTradeOverlay().getResourceAmount(resource));
		
		int value = 0;
		if(receive != null && receive.contains(resource)) {
			value = cardsToTrade.get(resource) + 1;
			if(value > 0)
				value = 0;
			cardsToTrade.put(resource, value);
			
		} else if(give != null && give.contains(resource)) {
			value = cardsToTrade.get(resource) - 1;
			if(value < 0) 
				value = 0;
			cardsToTrade.put(resource, value);
		}
		
		getTradeOverlay().setResourceAmountChangeEnabled(resource, true, (amount - 1 > 0));
		
		isReadyToTrade();
	}

	@Override
	public void increaseResourceAmount(ResourceType resource) {
		int val = modelFacade.getLocalPlayer().getBank().getResCards().get(resource);
		Integer amount = new Integer(getTradeOverlay().getResourceAmount(resource));
				
		int value = 0;
		if(receive != null && receive.contains(resource)) {
			value = cardsToTrade.get(resource) - 1;
			cardsToTrade.put(resource, value);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, true, true);
		} else if(give != null && give.contains(resource)) {
			value = cardsToTrade.get(resource) + 1;
			cardsToTrade.put(resource, value);
			getTradeOverlay().setResourceAmountChangeEnabled(resource, (val > amount + 1), (amount + 1 > 0));
		}
		isReadyToTrade();
	}

	@Override
	public void sendTradeOffer() {
		tradeOffer.setOffer(cardsToTrade);
		if(modelFacade.canOfferTrade(tradeOffer))
			modelFacade.doOfferTrade(tradeOffer);
		
		getTradeOverlay().closeModal();
		getWaitOverlay().setMessage("Waiting for Trade to Go Through");
		getWaitOverlay().showModal();
	}

	@Override
	public void setPlayerToTradeWith(int playerIndex) {
		tradeOffer.setReceiver(playerIndex);
		isReadyToTrade();
	}

	@Override
	public void setResourceToReceive(ResourceType resource) {
		getTradeOverlay().setResourceAmountChangeEnabled(resource, true, false);
		if(give != null && give.contains(resource))
			give.remove(resource);
		
		if(receive == null) 
			receive = new HashSet<ResourceType>();
		
		receive.add(resource);	
		isReadyToTrade();
	}

	@Override
	public void setResourceToSend(ResourceType resource) {
		int val = modelFacade.getLocalPlayer().getBank().getResCards().get(resource);
		getTradeOverlay().setResourceAmountChangeEnabled(resource, (val > 0), false);
		
		if(receive != null && receive.contains(resource))
			receive.remove(resource);
		
		if(give == null) 
			give = new HashSet<ResourceType>();
		
		give.add(resource);	
		isReadyToTrade();
	}

	@Override
	public void unsetResource(ResourceType resource) {
		if(give != null && give.contains(resource))
			give.remove(resource);
		else if(receive != null && receive.contains(resource))
			receive.remove(resource);
		
		if(cardsToTrade.containsKey(resource)) 
			cardsToTrade.put(resource, 0);
		
		isReadyToTrade();
	}

	@Override
	public void cancelTrade() {
		tradeOffer = null;
		cardsToTrade.clear();
		give = null;
		receive = null;
		getTradeOverlay().closeModal();
	}

	@Override
	public void acceptTrade(boolean willAccept) {
		modelFacade.doAcceptTrade(tradeOffer, willAccept);
		tradeOffer = null;
		if(cardsToTrade != null)
			cardsToTrade.clear();
		give = null;
		receive = null;
		getAcceptOverlay().closeModal();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		//handle receiving a trade here?
		if(getWaitOverlay().isModalShowing() && tradeOffer != null) {
			getWaitOverlay().closeModal();
			tradeOffer = null;
			cardsToTrade.clear();
			give = null;
			receive = null;
		}
		
		handleAcceptTrade();
	}
	
	public void handleAcceptTrade() {
		TradeOffer trade = modelFacade.getTradeOffer();
		if(trade != null && trade.getReceiver() == modelFacade.getLocalPlayerIndex()) {
			HashMap<ResourceType, Integer> resources = trade.getResources();
			
			getAcceptOverlay().reset();
			getAcceptOverlay().setPlayerName(modelFacade.getNameByIndex(trade.getSender()));
			getAcceptOverlay().setAcceptEnabled(true);
			for(ResourceType res : resources.keySet()) {
				if(resources.get(res) > 0) {
					getAcceptOverlay().addGetResource(res, resources.get(res));
				} else if(resources.get(res) < 0) {
					getAcceptOverlay().addGiveResource(res, resources.get(res) * -1);
					if(Math.abs(resources.get(res)) > modelFacade.getLocalPlayer().getBank().getResCards().get(res))
						getAcceptOverlay().setAcceptEnabled(false);
				}
			}
			
//			modelFacade.doAcceptTrade(trade, accept);
			getAcceptOverlay().showModal();
		}
	}
	
	public void isReadyToTrade() {
		if(give == null || receive == null)
			return;
		
		readyToTrade = true;
		if(give.size() > 0 && receive.size() > 0) {
			for(ResourceType res : cardsToTrade.keySet()) {
				if(cardsToTrade.get(res) == 0 && (give.contains(res) || receive.contains(res))) {
					readyToTrade = false;
					break;
				}
			}
			if(tradeOffer.getReceiver() == -1)
				readyToTrade = false;
		} 
		else 
			readyToTrade = false;
		
		if(readyToTrade) {
			getTradeOverlay().setStateMessage(SENDTRADE);
			getTradeOverlay().setTradeEnabled(true);
		}
		else {
			getTradeOverlay().setStateMessage(SETTRADE);
			getTradeOverlay().setTradeEnabled(false);
		}
	}

}