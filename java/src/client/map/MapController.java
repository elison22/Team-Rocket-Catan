package client.map;

import java.util.Observable;
import java.util.Observer;

import client.map.states.AbstractMapState;
import model.game.TurnState;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import client.base.Controller;
import client.data.RobPlayerInfo;
import facade.ClientFacade;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {
	
	private IRobView robView;
    private ClientFacade facade;
    private AbstractMapState mapState;
    private EdgeLocation firstRoad;
    private boolean playSecondRoad;

    public MapController(IMapView view, IRobView robView, ClientFacade facade) {
        this(view, robView);
        this.facade = facade;
        facade.addObserver(this);
    }

	public MapController(IMapView view, IRobView robView) {
		super(view);
		setRobView(robView);
		
		initFromModel();
	}
	
	public IMapView getView() {
		
		return (IMapView)super.getView();
	}
	
	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	
	protected void initFromModel() {

		for (int x = 0; x <= 3; ++x) {
			
			int maxY = 3 - x;			
			for (int y = -3; y <= maxY; ++y) {
				HexLocation hexLoc = new HexLocation(x, y);
                HexType hexType = facade.getHexType(hexLoc);
				getView().addHex(hexLoc, hexType);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//						CatanColor.RED);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//						CatanColor.BLUE);
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//						CatanColor.ORANGE);
//				getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
//				getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
                    HexLocation hexLoc = new HexLocation(x, y);
                    HexType hexType = facade.getHexType(hexLoc);
                    getView().addHex(hexLoc, hexType);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//							CatanColor.RED);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//							CatanColor.BLUE);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//							CatanColor.ORANGE);
//					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.GREEN);
//					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.PURPLE);
				}
			}
		}
		
		PortType portType = PortType.BRICK;
		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
		
		getView().placeRobber(new HexLocation(0, 0));
		
		getView().addNumber(new HexLocation(-2, 0), 2);
		getView().addNumber(new HexLocation(-2, 1), 3);
		getView().addNumber(new HexLocation(-2, 2), 4);
		getView().addNumber(new HexLocation(-1, 0), 5);
		getView().addNumber(new HexLocation(-1, 1), 6);
		getView().addNumber(new HexLocation(1, -1), 8);
		getView().addNumber(new HexLocation(1, 0), 9);
		getView().addNumber(new HexLocation(2, -2), 10);
		getView().addNumber(new HexLocation(2, -1), 11);
		getView().addNumber(new HexLocation(2, 0), 12);
		
		//</temp>
	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {

        TurnState curState = facade.getState();
        switch(curState){
            case Rolling:
            case Robbing:
            case Discarding:
                return false;
            case FirstRound:
            case SecondRound:
            case Playing:
//                if(playSecondRoad) return facade.canBuildRoad(firstRoad, edgeLoc);
                /*else*/ return facade.canBuildRoad(edgeLoc);
            default:


        }
		return true;
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		
		return true;
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		
		return true;
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		
		return true;
	}

	public void placeRoad(EdgeLocation edgeLoc) {
		
		getView().placeRoad(edgeLoc, CatanColor.ORANGE);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		
		getView().placeSettlement(vertLoc, CatanColor.ORANGE);
	}

	public void placeCity(VertexLocation vertLoc) {
		
		getView().placeCity(vertLoc, CatanColor.ORANGE);
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		
		getView().startDrop(pieceType, CatanColor.ORANGE, true);
	}
	
	public void cancelMove() {
		
	}
	
	public void playSoldierCard() {	
		
	}
	
	public void playRoadBuildingCard() {	
		//build first road
        playSecondRoad = true;


        playSecondRoad = false;
	}
	
	public void robPlayer(RobPlayerInfo victim) {	
		
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
}

