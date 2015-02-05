package test.model.game;

import model.board.BoardException;
import model.game.GameModel;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;
import serializer.Serializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Hayden on 2/1/15.
 *
 * This will test the canBuildRoad method the facade will call on the GameModel
 * to determine if a player meets the qualifications to build a road (it is the
 * player's turn, it is the right turn state, the player has a road piece left,
 * the player has the resources necessary, and the location of the road is a
 * legal placement for a road).
 */
public class PlayerTest {

    GameModel testGameModel;
    Serializer testSerializer;
    File file;
    Scanner stream;
    StringBuilder builder;
    String json;

    @Before
    public void setup() throws FileNotFoundException, BoardException {
        testGameModel = new GameModel();
        testSerializer = new Serializer();
    }

    public void initializeModel(String filename) throws BoardException, FileNotFoundException {
        file = new File(System.getProperty("user.dir") + "/JunitJsonFiles/" + filename);
        stream = new Scanner(file);
        builder = new StringBuilder();
        while(stream.hasNext()) builder.append(stream.next());
        json = builder.toString();
        testGameModel = testSerializer.deSerializeFromServer(testGameModel, json);
        stream.close();
    }

    //***CanBuildRoadTests***//
    @Test
    public void testRoadsRemaining() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(1);
        assertFalse(player1.getRemainingRoads() > 2);
        assertTrue(player2.getRemainingRoads() > 2);
    }

    @Test
    public void testCanAffordRoad() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(1);
        Player player2 = testGameModel.getPlayerList().get(2);
        assertTrue(player1.getBank().canAffordRoad());
        assertFalse(player2.getBank().canAffordRoad());
    }

    @Test
    public void testBuildDuringTurn() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(1);
        assertTrue(testGameModel.getPlayerTurn() == player1.getPlayerIdx());
        assertFalse(testGameModel.getPlayerTurn() == player2.getPlayerIdx());
    }

    @Test
    public void testBuildDuringCorrectState() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");

    }
}
