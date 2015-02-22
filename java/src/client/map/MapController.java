package client.map;

import java.util.Observable;
import java.util.Observer;

import client.map.states.*;
import model.game.TurnState;
import shared.definitions.CatanColor;
import shared.definitions.HexType;
import shared.definitions.PieceType;
import shared.definitions.PortType;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
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
    private TurnState curState;
    private EdgeLocation firstRoad;
    private boolean playSecondRoad;

    public boolean modalOpen;

    public MapController(IMapView view, IRobView robView, ClientFacade facade) {
        super(view);
        setRobView(robView);
        this.facade = facade;
        initFromModel();
        facade.addObserver(this);
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
                int resourceNum = facade.getChit(hexLoc);
                getView().addHex(hexLoc, hexType);
                getView().addNumber(hexLoc, resourceNum);
			}
			
			if (x != 0) {
				int minY = x - 3;
				for (int y = minY; y <= 3; ++y) {
                    HexLocation hexLoc = new HexLocation(-x, y);
                    HexType hexType = facade.getHexType(hexLoc);
                    int resourceNum = facade.getChit(hexLoc);
                    getView().addHex(hexLoc, hexType);
                    getView().addNumber(hexLoc, resourceNum);
				}
			}
		}

        getView().placeRobber(facade.getRobberLoc());

        EdgeLocation portLoc = new EdgeLocation(new HexLocation(-1, -2), EdgeDirection.South);
		PortType portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);

        portLoc = new EdgeLocation(new HexLocation(1, -3), EdgeDirection.South);
        portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);

        portLoc = new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest);
        portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);

        portLoc = new EdgeLocation(new HexLocation(3, -1), EdgeDirection.NorthWest);
        portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);

        portLoc = new EdgeLocation(new HexLocation(2, 1), EdgeDirection.NorthWest);
        portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);

        portLoc = new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North);
        portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);

        portLoc = new EdgeLocation(new HexLocation(-2, 3), EdgeDirection.NorthEast);
        portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);

        portLoc = new EdgeLocation(new HexLocation(-3, 2), EdgeDirection.NorthEast);
        portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);

        portLoc = new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast);
        portType = facade.getPortType(portLoc);
        getView().addPort(portLoc, portType);


	}

	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
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
		
		getView().placeRoad(edgeLoc, facade.getPlayerInfo().getColor());
	}

	public void placeSettlement(VertexLocation vertLoc) {
		
		getView().placeSettlement(vertLoc, facade.getPlayerInfo().getColor());
	}

	public void placeCity(VertexLocation vertLoc) {
		
		getView().placeCity(vertLoc, facade.getPlayerInfo().getColor());
	}

	public void placeRobber(HexLocation hexLoc) {
		
		getView().placeRobber(hexLoc);
		
		getRobView().showModal();
	}
	
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		
		getView().startDrop(pieceType, facade.getPlayerInfo().getColor(), true);
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
        curState = facade.getState();
        if(null == curState){
            return;
        }
        if(facade.getPlayerList().length != 4){
            return;
        }
        initFromModel();
        switch(curState){
            case Rolling:
                mapState = new RollingMapState();
                break;
            case Robbing:
                mapState = new RobbingMapState();
                break;
            case Discarding:
                mapState = new DiscardMapState();
                break;
            case FirstRound:
                mapState = new Round1MapState();
                break;
            case SecondRound:
                mapState = new Round2MapState();
                break;
            case Playing:
                mapState = new PlayingMapState();
                break;
        }
        mapState.start(this);
        modalOpen = true;

    }
}

