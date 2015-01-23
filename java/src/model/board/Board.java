package model.board;

import model.board.mapunits.HexTile;
import shared.definitions.HexType;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by brandt on 1/17/15.
 * <p>
 * This class contains all the data that describes the Catan map. It also contains methods
 * needed to update the information in this Board. The Board keeps track of the HexTiles,
 * Buildings, Roads, and the robber.
 * </p>
 */
public class Board {

    /** The HexTile objects that make up this Board */
    private ArrayList<HexTile> tiles = new ArrayList<HexTile>() {};
    /** The HexLocation that represents the robber */
    private HexLocation robber;

    /**
     * Creates a new Board object with HexTile objects for the whole board including
     * diceNums, and actual HexLocations. It also initializes the robbers location to
     * the Desert, which has a diceNum of 0.
     * @param randomTileTypes Whether or not the tiles resource types should be randomized.
     * @param randomDiceNums Whether or not the diceNums should be randomized.
     * @throws BoardException Thrown only when there's a problem with the code that initializes this object.
     */
    public Board(boolean randomTileTypes, boolean randomDiceNums) throws BoardException {

        Random randomTypes = new Random();
        Random randomNums = new Random();
        int nextTypeRand;
        int nextNumRand;
        int toSubtract = 0;
        HexType nextType;

        ArrayList<HexType> typeSetupArray = new ArrayList<HexType>();
        typeSetupArray.add(HexType.DESERT);
        for(int i = 0; i < 3; i++) typeSetupArray.add(HexType.BRICK);
        for(int i = 0; i < 3; i++) typeSetupArray.add(HexType.ORE);
        for(int i = 0; i < 4; i++) typeSetupArray.add(HexType.WHEAT);
        for(int i = 0; i < 4; i++) typeSetupArray.add(HexType.WOOD);
        for(int i = 0; i < 4; i++) typeSetupArray.add(HexType.SHEEP);

        int[] diceNumSetupArray = {5, 2, 6, 3, 8, 10, 9, 12, 11, 4, 8, 10, 9, 4, 5, 6, 3, 11};
        int[] x = {0, 1, 2, 2, 2, 1, 0, -1, -2, -2 ,-2, -1, 0, 1, 1, 0, -1, -1, 0};
        int[] y = {2, 1, 0, -1, -2, -2, -2, -1, 0, 1, 2, 2, 1, 0, -1, -1, 0, 1, 0};

        //Beginning for random types but set dice numbers
        for(int i = 0; i < 19; i++) {
            nextTypeRand = randomTypes.nextInt(typeSetupArray.size());
            nextType = typeSetupArray.get(nextTypeRand);
            tiles.add(
                    new HexTile(
                            nextType,
                            nextType != HexType.DESERT ? diceNumSetupArray[i - toSubtract] : setRobber(toSubtract++, x[i], y[i]),
                            new HexLocation(x[i], y[i])
                    ));
            typeSetupArray.remove(nextTypeRand);
        }
        //End for random types but set dice numbers
    }

    /**
     * This is called in the constructor to simultaneously set the diceNum of a
     * HexTile and assign the robber to that same HexTile.
     * @param diceNum The diceNum for the HexTile to receive the robber.
     * @param x The x location for the robber.
     * @param y The y location for the robber.
     * @return The diceNum for the HexTile to receive the robber.
     * @throws BoardException Thrown when the x or y values are outside the range -2 to 2
     */
    private int setRobber(int diceNum, int x, int y) throws BoardException {
        setRobber(x, y);
        return diceNum;
    }

    /**
     * Set the robber's new location using x/y coordinates
     * @param x The robber's new x location
     * @param y The robber's new y location
     * @throws BoardException Thrown when the x or y values are outside the range -2 to 2
     */
    public void setRobber(int x, int y) throws BoardException {
        //TODO: implement the check to make sure the robber is actually on the board
        robber = new HexLocation(x, y);
    }

    /**
     * Set the robber's new location using a HexLocation object
     * @param newLocation The HexLocation for the robber's new location
     * @throws BoardException Thrown when the x or y values are outside the range -2 to 2
     */
    public void setRobber(HexLocation newLocation) throws BoardException {
        //TODO: implement the check to make sure the robber is actually on the board
        robber = newLocation;
    }

    /**
     * Determines whether or not a player can build a Settlement on this location. The
     * conditions are that no Building is already present at the location param
     * or any of its neighbors. Verifies with all 3 adjacent HexTiles.
     * @param location Where to check for availability.
     * @return True if the vertex is available for a Settlement.
     */
    public boolean canBuildSettlement(VertexLocation location) { return true; }

    /**
     * Determines whether or not a player can build a City on this location. The
     * conditions are that there is a Settlement already on this location.
     * Verifies with all 3 adjacent HexTiles.
     * @param location Where to check for availability.
     * @return True if the vertex is available for a City.
     */
    public boolean canBuildCity(VertexLocation location) { return true; }

    /**
     * Determines whether or not a player can build a Road on this location. The
     * conditions are that no Road is already present at the location param or any
     * of its neighbors. Verifies with both adjacent HexTiles.
     */
    public boolean canBuildRoad(EdgeLocation location) { return true; }

    /**
     * This orchestrates the process of building a Settlement by making sure Settlement
     * objects get assigned to all the HexTile objects adjacent to the location parameter.
     * @param location The location where the Settlement should be built.
     */
    public void buildSettlement(VertexLocation location) {}

    /**
     * This orchestrates the process of building a City by making sure City
     * objects get assigned to all the HexTile objects adjacent to the location parameter.
     * @param location The location where the City should be built.
     */
    public void buildCity(VertexLocation location) {}

    /**
     * This orchestrates the process of building a Road by making sure Road
     * objects get assigned to all the HexTile objects adjacent to the location parameter.
     * @param location The location where the Road should be built.
     */
    public void buildRoad(EdgeLocation location) {}

    /**
     * Finds out what players should get resources, then updates the PlayerBank
     * for each player and the GameBank. Resource allocation may eventually be
     * move to another class improve single responsibility.
     * @param diceNum The dice value was rolled.
     */
    public void distributeResources(int diceNum) {}



}
