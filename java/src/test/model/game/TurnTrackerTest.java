package test.model.game;

import model.board.BoardException;
import model.game.GameModel;
import model.game.TurnTracker;
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
public class TurnTrackerTest {

    GameModel testGame;
    TurnTracker turnTracker;
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
        file = new File(System.getProperty("user.dir") + "/java/src/test/JunitJsonFiles/" + filename);
        stream = new Scanner(file);
        builder = new StringBuilder();
        while(stream.hasNext()) builder.append(stream.next());
        json = builder.toString();
        testGame = testSerializer.deSerializeFromServer(testGame, json);
        turnTracker = testGame.getTurnTracker();
        stream.close();
    }

    //***General Building Tests***//
    @Test
    public void testBuildDuringTurn() throws BoardException, FileNotFoundException{
    	// Import model where it's player[0]'s turn
        initializeModel("Json1.json");
        Player player1 = testGame.getPlayerList().get(0);
        Player player2 = testGame.getPlayerList().get(1);
        
        // Test that the player can build during their turn 
        assertTrue(turnTracker.canPlayerBuild(player1.getPlayerIdx()));
        
        // Test that the player can't build when it's not their turn
        assertFalse(turnTracker.canPlayerBuild(player2.getPlayerIdx()));
    }

    @Test
    public void testBuildDuringCorrectState() throws BoardException, FileNotFoundException{
    	
    	// Import model where it's in the playing state
    	initializeModel("Json1.json");
    	Player player1 = testGame.getPlayerList().get(0);
        
        // Test that the game is in the playing state before player can build
        assertTrue(turnTracker.canPlayerBuild(player1.getPlayerIdx()));
        
        // Import model where it's in the dice roll state
        initializeModel("Json2.json");
        
        // Test that the player can't build when the game isn't in the playing 
        // state
        assertFalse(turnTracker.canPlayerBuild(player1.getPlayerIdx()));
    }

    //***CanRollDice Tests***//
    @Test
    public void testAttemptRollDuringTurn() throws BoardException, FileNotFoundException{
    	
    	// Import model where it's player[0]'s turn
        initializeModel("Json2.json");
        
        Player player1 = testGame.getPlayerList().get(0);
        Player player2 = testGame.getPlayerList().get(3);
        
        // Test that the player can only roll the dice when it's their turn
        assertTrue(turnTracker.canPlayerRoll(player1.getPlayerIdx()));
        
        // Test the the player can't roll the dice when it's not their turn
        assertFalse(turnTracker.canPlayerRoll(player2.getPlayerIdx()));
    }

    @Test
    public void testRollTurnState() throws BoardException, FileNotFoundException{
    	
    	// Import model where it's in the dice roll state
        initializeModel("Json2.json");
        Player player1 = testGame.getPlayerList().get(0);
        
        // Test that the game is in the rolling state before the player can roll
        // the dice
        assertTrue(turnTracker.canPlayerRoll(player1.getPlayerIdx()));
        
        // Import model where it's not in the dice roll state
        initializeModel("Json3.json");
        
        // Test that the player can't roll dice when 
        assertFalse(turnTracker.canPlayerRoll(player1.getPlayerIdx()));
    }

    //***CanFinishTurn Test***//
    @Test
    public void testPlayerCanFinishTurn() throws BoardException, FileNotFoundException{
    	
    	// Import model where it's not in the play state
        initializeModel("Json2.json");
        Player player = testGame.getPlayerList().get(0);
        
        // Test that the player can't finish turn if it's not the play state or
        // or in other words if the player is able to build
        assertFalse(turnTracker.canPlayerBuild(player.getPlayerIdx()));
    }





}
