package test.model.game;

import model.board.BoardException;
import model.game.GameModel;
import model.game.TurnState;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;
import serializer.Serializer;

import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Brandt on 2/1/15.
 */
public class GameModelTest {

    GameModel testGame;
    Serializer testSerializer;
    File file;
    Scanner stream;
    StringBuilder builder;
    String json;

    @Before
    public void setup() throws FileNotFoundException, BoardException {
        testGame = new GameModel();
        testSerializer = new Serializer();
    }

    public void initializeModel(String filename) throws BoardException, FileNotFoundException {
        file = new File(System.getProperty("user.dir") + "/JunitJsonFiles/" + filename);
        stream = new Scanner(file);
        builder = new StringBuilder();
        while(stream.hasNext()) builder.append(stream.next());
        json = builder.toString();
        testGame = testSerializer.deSerializeFromServer(testGame, json);
        stream.close();
    }

    @Test
    public void example() throws BoardException, FileNotFoundException {
        initializeModel("Json2.json");
        //do test stuff
    }

    //***General Building Tests***//
    @Test
    public void testBuildDuringTurn() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGame.getPlayerList().get(0);
        Player player2 = testGame.getPlayerList().get(1);
        assertTrue(testGame.getPlayerTurn() == player1.getPlayerIdx());
        assertFalse(testGame.getPlayerTurn() == player2.getPlayerIdx());
    }

    @Test
    public void testBuildDuringCorrectState() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        assertTrue(testGame.getTurnState() == TurnState.Playing);
        initializeModel("Json2.json");
        assertFalse(testGame.getTurnState() == TurnState.Playing);
    }

    //***CanRollDice Tests***//
    @Test
    public void testAttemptRollDuringTurn() throws BoardException, FileNotFoundException{
        initializeModel("Json2.json");
        Player player1 = testGame.getPlayerList().get(0);
        Player player2 = testGame.getPlayerList().get(3);
        assertTrue(testGame.getPlayerTurn() == player1.getPlayerIdx());
        assertFalse(testGame.getPlayerTurn() == player2.getPlayerIdx());
    }

    @Test
    public void testRollTurnState() throws BoardException, FileNotFoundException{
        initializeModel("Json2.json");
        assertTrue(testGame.getTurnState() == TurnState.Rolling);
        initializeModel("Json3.json");
        assertFalse(testGame.getTurnState() == TurnState.Rolling);
    }

    //***CanFinishTurn Test***//
    @Test
    public void testPlayerCanFinishTurn() throws BoardException, FileNotFoundException{
        initializeModel("Json2.json");
        Player player = testGame.getPlayerList().get(0);
        assertFalse(testGame.canFinishTurn(player.getPlayerIdx()));
    }





}
