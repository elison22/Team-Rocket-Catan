package client.devcards;

import java.util.Observable;
import java.util.Observer;

import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import client.base.Controller;
import client.base.IAction;
import facade.ClientFacade;

/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController, Observer {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	private ClientFacade modelFacade;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction, ClientFacade facade) 
	{
		super(view);
		modelFacade = facade;
		facade.addObserver(this);
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
	}

	public IPlayDevCardView getPlayCardView() 
	{
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() 
	{
		return buyCardView;
	}

	@Override
	public void startBuyCard() 
	{
		if (!getBuyCardView().isModalShowing())
			getBuyCardView().showModal();	
	}

	@Override
	public void cancelBuyCard() 
	{
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() 
	{
		modelFacade.buyDevCard();
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() 
	{
		if (!getPlayCardView().isModalShowing()) {
			int soldierCount = modelFacade.getLocalPlayer().getBank().getSoldierCount();
			int monopolyCount = modelFacade.getLocalPlayer().getBank().getMonopolyCount();
			int yearOfPlentyCount = modelFacade.getLocalPlayer().getBank().getYearOfPlentyCount();
			int monumentCount = modelFacade.getLocalPlayer().getBank().getMonumentCount();
			int roadBuildingCount = modelFacade.getLocalPlayer().getBank().getRoadBuildingCount();
			getPlayCardView().setCardAmount(DevCardType.SOLDIER, soldierCount);
			getPlayCardView().setCardAmount(DevCardType.MONOPOLY, monopolyCount);
			getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, yearOfPlentyCount);
			getPlayCardView().setCardAmount(DevCardType.MONUMENT, monumentCount);
			getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, roadBuildingCount);
			getPlayCardView().showModal();
		}
	}

	@Override
	public void cancelPlayCard() 
	{
		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) 
	{
		modelFacade.doUseMonopoly(resource.name());
	}

	@Override
	public void playMonumentCard() 
	{
		modelFacade.doUseMonument();
	}

	@Override
	public void playRoadBuildCard() 
	{
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() 
	{
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) 
	{
		modelFacade.doUseYearOfPlenty(resource1.name(), resource2.name());
	}

	@Override
	public void update(Observable o, Object arg) 
	{
		if (getBuyCardView().isModalShowing() && !modelFacade.canBuyDevCard())
			getBuyCardView().closeModal();
	}

    @Override
    public boolean canPlayDevCard(DevCardType type){
        switch(type){
            case SOLDIER:
                return modelFacade.canUseSoldier();
            case ROAD_BUILD:
                return modelFacade.canUseRoadBuilder();
            case MONOPOLY:
                return modelFacade.canUseMonopoly();
            case MONUMENT:
                return modelFacade.canUseMonument();
            case YEAR_OF_PLENTY:
                return modelFacade.canUseYearOfPlenty();
        }
        return false;
    }
}

