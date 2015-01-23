package model.board;

import model.board.mapunits.Edge;
import model.board.mapunits.HexTile;
import model.board.mapunits.Vertex;
import shared.definitions.HexType;
import shared.locations.HexLocation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by brandt on 1/17/15.
 * <p>
 * This class
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
     * @throws BoardException Thrown only when there's a problem with the code that initializes this object.
     */
    public Board() throws BoardException {

        Random random = new Random();
        int nextRand;
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

        for(int i = 0; i < 19; i++) {
            nextRand = random.nextInt(typeSetupArray.size());
            nextType = typeSetupArray.get(nextRand);
            tiles.add(
                    new HexTile(
                            nextType,
                            nextType != HexType.DESERT ? diceNumSetupArray[i - toSubtract] : setRobber(toSubtract++, x[i], y[i]),
                            new HexLocation(x[i], y[i])
                    ));
            typeSetupArray.remove(nextRand);
        }
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

}
