package client.catan;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import shared.definitions.PieceType;
import client.base.IAction;
import client.devcards.BuyDevCardView;
import client.devcards.DevCardController;
import client.devcards.PlayDevCardView;
import client.map.IMapController;
import client.points.GameFinishedView;
import client.points.PointsController;
import client.points.PointsView;
import client.resources.ResourceBarController;
import client.resources.ResourceBarElement;
import client.resources.ResourceBarView;
import facade.ClientFacade;

@SuppressWarnings("serial")
public class RightPanel extends JPanel
{
	
	private PlayDevCardView playCardView;
	private BuyDevCardView buyCardView;
	private DevCardController devCardController;
	private PointsView pointsView;
	private GameFinishedView finishedView;
	private PointsController pointsController;
	private ResourceBarView resourceView;
	private ResourceBarController resourceController;
	
	public RightPanel(final IMapController mapController, ClientFacade facade)
	{
		
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		// Initialize development card views and controller
		//
		playCardView = new PlayDevCardView("PlayDevCard");
		buyCardView = new BuyDevCardView("BuyDevCard");
		IAction soldierAction = new IAction() {
			@Override
			public void execute()
			{
				mapController.playSoldierCard();
			}
		};
		IAction roadAction = new IAction() {
			@Override
			public void execute()
			{
				mapController.playRoadBuildingCard();
			}
		};
		devCardController = new DevCardController(playCardView, buyCardView,
												  soldierAction, roadAction, facade);
		playCardView.setController(devCardController);
		buyCardView.setController(devCardController);
		
		// Initialize victory point view and controller
		//
		pointsView = new PointsView("Points");
		finishedView = new GameFinishedView("GameFinished");
		pointsController = new PointsController(pointsView, finishedView, facade);
		pointsView.setController(pointsController);
		
		// Initialize resource bar view and controller
		//
		resourceView = new ResourceBarView("ResourceBar");
		resourceController = new ResourceBarController(resourceView, facade);
		resourceController.setElementAction(ResourceBarElement.ROAD,
											createStartMoveAction(mapController,
																  PieceType.ROAD));
		resourceController.setElementAction(ResourceBarElement.SETTLEMENT,
											createStartMoveAction(mapController,
																  PieceType.SETTLEMENT));
		resourceController.setElementAction(ResourceBarElement.CITY,
											createStartMoveAction(mapController,
																  PieceType.CITY));
		resourceController.setElementAction(ResourceBarElement.BUY_CARD,
											new IAction() {
												@Override
												public void execute()
												{
													devCardController.startBuyCard();
												}
											});
		resourceController.setElementAction(ResourceBarElement.PLAY_CARD,
											new IAction() {
												@Override
												public void execute()
												{
													devCardController.startPlayCard();
												}
											});
		resourceView.setController(resourceController);
		
		this.add(pointsView);
		this.add(resourceView);
	}
	
	private IAction createStartMoveAction(final IMapController mapController,
										  final PieceType pieceType)
	{
		
		return new IAction() {
			
			@Override
			public void execute()
			{
				boolean isFree = false;
				boolean allowDisconnected = false;
				mapController.startMove(pieceType, isFree, allowDisconnected);
			}
		};
	}
	
}

