package model.board;

import model.board.mapunits.Edge;
import model.board.mapunits.HexTile;
import model.board.mapunits.Vertex;
import shared.definitions.HexType;
import shared.locations.HexLocation;
import shared.locations.VertexLocation;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by brandt on 1/17/15.
 */
public class Board {

    private ArrayList<HexTile> tiles = new ArrayList<HexTile>() {};
    private ArrayList<Vertex> vertices = new ArrayList<Vertex>() {};
    private ArrayList<Edge> edges = new ArrayList<Edge>() {};
    private HexLocation robber;

    /**
     * Creates a new Board object with HexTile objects for the whole board including
     * diceNums, and actual HexLocations. It also initializes the robbers location to
     * the Desert, which has a diceNum of 0.
     */
    public Board() {

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

    private int setRobber(int temp, int x, int y){
        robber = new HexLocation(x, y);
        return temp;
    }


}
