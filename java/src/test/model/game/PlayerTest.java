package test.model.game;

import model.board.BoardException;
import model.game.GameModel;
import model.game.TurnState;
import model.player.Player;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;
import serializer.Serializer;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
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
        file = new File(System.getProperty("user.dir") + "/java/src/test/JunitJsonFiles/" + filename);
        stream = new Scanner(file);
        builder = new StringBuilder();
        while(stream.hasNext()) builder.append(stream.next());
        json = builder.toString();
        testGameModel = testSerializer.deSerializeFromServer(testGameModel, json);
        stream.close();
    }

    //***General Building Tests***//
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
        assertTrue(testGameModel.getTurnState() == TurnState.Playing);
        initializeModel("Json2.json");
        assertFalse(testGameModel.getTurnState() == TurnState.Playing);
    }

    //***CanBuildRoad Tests***//
    @Test
    public void testRoadsRemaining() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(1);
        assertFalse(player1.getRemainingRoads() > 1);
        assertTrue(player2.getRemainingRoads() > 1);
    }

    @Test
    public void testCanAffordRoad() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(1);
        Player player2 = testGameModel.getPlayerList().get(2);
        assertTrue(player1.getBank().canAffordRoad());
        assertFalse(player2.getBank().canAffordRoad());
    }

    //***CanBuildSettlement Tests***//
    @Test
    public void testSettlementsRemaining() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(1);
        assertFalse(player1.getRemainingSettlements() > 1);
        assertTrue(player2.getRemainingSettlements() > 1);
    }

    @Test
    public void testCanAffordSettlement() throws BoardException, FileNotFoundException{
        initializeModel("Json2.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(2);
        assertTrue(player1.getBank().canAffordSettlement());
        assertFalse(player2.getBank().canAffordSettlement());
    }

    //***CanBuildCity Tests***//
    @Test
    public void testCitiesRemaining() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(1);
        assertFalse(player1.getRemainingCities() > 1);
        assertTrue(player2.getRemainingCities() > 1);
    }

    @Test
    public void testCanAffordCity() throws BoardException, FileNotFoundException{
        initializeModel("Json4.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(1);
        assertTrue(player1.getBank().canAffordSettlement());
        assertFalse(player2.getBank().canAffordSettlement());
    }

    //***CanRollDice Tests***//
    @Test
    public void testAttemptRollDuringTurn() throws BoardException, FileNotFoundException{
        initializeModel("Json2.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(3);
        assertTrue(testGameModel.getPlayerTurn() == player1.getPlayerIdx());
        assertFalse(testGameModel.getPlayerTurn() == player2.getPlayerIdx());
    }

    @Test
    public void testRollTurnState() throws BoardException, FileNotFoundException{
        initializeModel("Json2.json");
        assertTrue(testGameModel.getTurnState() == TurnState.Rolling);
        initializeModel("Json3.json");
        assertFalse(testGameModel.getTurnState() == TurnState.Rolling);
    }

    //***OfferTrade Tests***//
    @Test
    public void testOfferTradeDuringTurn() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        Player player2 = testGameModel.getPlayerList().get(1);
        assertTrue(testGameModel.getPlayerTurn() == player1.getPlayerIdx());
        assertFalse(testGameModel.getPlayerTurn() == player2.getPlayerIdx());
    }

    @Test
    public void testTradeTurnState() throws BoardException, FileNotFoundException{
        initializeModel("Json3.json");
        assertTrue(testGameModel.getTurnState() == TurnState.Playing);
        initializeModel("Json2.json");
        assertFalse(testGameModel.getTurnState() == TurnState.Playing);
    }

    @Test
    public void testOfferOwnedResources() throws BoardException, FileNotFoundException{
        initializeModel("Json3.json");
        Player player = testGameModel.getPlayerList().get(3);
        HashMap<ResourceType, Integer> offeredResources1 = new HashMap<ResourceType, Integer>();
        HashMap<ResourceType, Integer> offeredResources2 = new HashMap<ResourceType, Integer>();
        HashMap<ResourceType, Integer> offeredResources3 = new HashMap<ResourceType, Integer>();
        offeredResources1.put(ResourceType.BRICK, 1);
        offeredResources1.put(ResourceType.WHEAT, 1);
        offeredResources2.put(ResourceType.WOOD, 2);
        offeredResources2.put(ResourceType.ORE, 1);
        offeredResources3.put(ResourceType.BRICK, 3);
        offeredResources3.put(ResourceType.ORE, 2);

        assertTrue(player.canOfferTrade(player.getPlayerIdx(), offeredResources1));
        assertFalse(player.canOfferTrade(player.getPlayerIdx(), offeredResources2));
        assertFalse(player.canOfferTrade(player.getPlayerIdx(), offeredResources3));
    }

    //***MaritimeTrade Tests***//

    //***CanFinishTurn Test***//

    //***CanBuyDevCard Tests***//
    @Test
    public void testBuyDevCardWhenBankHasNone() throws BoardException, FileNotFoundException{
        initializeModel("Json4.json");
        assertTrue(testGameModel.canBuyDevCard(0));
        initializeModel("Json1.json");
        assertFalse(testGameModel.canBuyDevCard(0));
    }

    @Test
    public void testPlayerHasResourcesToBuyDev() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(1);
        Player player2 = testGameModel.getPlayerList().get(2);
        assertTrue(player1.canBuyDevCard());
        assertFalse(player2.canBuyDevCard());
    }

    @Test
    public void testCorrectStateToBuyDev() throws BoardException, FileNotFoundException{
        initializeModel("Json2.json");
        assertFalse(testGameModel.canBuyDevCard(0));
        initializeModel("Json4.json");
        assertTrue(testGameModel.canBuyDevCard(0));
    }

    //***Generic Development Card Tests***//
    @Test
    public void testCannotPlayDevBoughtSameTurn() throws BoardException, FileNotFoundException{
        initializeModel("Json1.json");
        Player player1 = testGameModel.getPlayerList().get(0);
        assertFalse(player1.canPlayDevCard(DevCardType.YEAR_OF_PLENTY));

        //Player has a year of plenty card
        initializeModel("Json4.json");
        Player player2 = testGameModel.getPlayerList().get(0);
        assertTrue(player2.canPlayDevCard(DevCardType.YEAR_OF_PLENTY));

        //Tests a player who has a new Monument dev card who should be able to play it since he has 9 points currently
        initializeModel("Json6.json");
        Player player3 = testGameModel.getPlayerList().get(0);
        assertTrue(player3.canPlayDevCard(DevCardType.MONUMENT));
    }
    //***CanUseYearOfPlenty Tests***//

    //***CanUseRoadBuilder Tests***//

    //***CanUseSoldier Tests***//

    //***CanUseMonopoly Tests***//

    //***CanUseMonument Tests***//

    //***CanPlaceRobber Test***//
}
