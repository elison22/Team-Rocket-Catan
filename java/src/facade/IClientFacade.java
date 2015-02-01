package facade;

import java.util.ArrayList;

import model.cards.ResourceSet;
import model.game.GameModel;
import model.trade.DomesticTrade;
import model.trade.MaritimeTrade;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public interface IClientFacade {

	/**
     * @param user user name of person trying to login
     * @param password password of person trying to login
     * @return true if user is able to login, false otherwise
     */
    public boolean canUserLogin(String user, String password);
	
	/**
     * @param user user name of person trying to login
     * @param password password of person trying to login
     * @return true if user succesfully logged in, false if otherwise
     */
    public boolean doUserLogin(String user, String password);

    /**
     * @param user user name of person trying to register
     * @param password password of user trying to register
     * @return true if username and password is valid and available and game state is in register mode
     */
    public boolean canUserRegister(String user, String password);

    /**
     * @param user user name of person trying to register
     * @param password password of user trying to register
     * @return true if person successfully registered with credentials
     */
    public boolean doUserRegister(String user, String password);

    /**
     * @return list of all the games in progress
     */
    public ArrayList<GameModel> getGames();

    /**
     * @param gameName name of the game user is trying to create
     * @param randTiles whether or not user wants tiles to be randomly placed
     * @param randPorts whether or not user wants ports to be randomly placed
     * @param randNums whether or not user wants numbers to be randomly placed
     */
    public void CreateGame(String gameName, boolean randTiles, boolean randPorts, boolean randNums);

    /**
     * @return true if user state is in join mode
     */
    public boolean canJoinGame();

    /**
     * Adds a player to the game
     * @param playerId id of the player who is joining game
     * @param color the color the player chose to be
     */
    public void joinGame(int playerId, String color);

    /**
     * @return true if game is in state that can be saved
     */
    public boolean canSave();

    /**
     * @return true if game successfully saved
     */
    public boolean doSave();

    /**
     * @param name name of the saved game file you want to load
     * @return true if game successfully loaded
     */
    public boolean doLoad(String name);

    /**
     * Checks to see if player can reset the game to the beginning state
     * @return whether or not the player can restart the game
     */
    public boolean canReset();

    /**
     * Resets the current game to its beginning state
     * @return true if game successfully restarted
     */
    public boolean doReset();

    /**
     * Used to know what cards are in hand
     * @return list of all the player's resources
     */
    public ResourceSet getPlayerResources();

    /**
     * @return the games card bank to see resources/devcards available
     */
   /* public GameBank getCardBank() {
        return cardBank;
    }*/

    /**
     * @return the current client model version number
     */
    public int getVersionNumber();

    /**
     * Checks to see if player can add an AI player to the game right now
     * @return true if player can add an AI player to game
     */
    public boolean canAddAI();

    /**
     * @param AIType type of AI to add to the game
     */
    public void doAddAI(String AIType);

    /**
     * @return list of the supoorted AI player types
     */
    public ArrayList<String> getAIList();

    /**
     * Adds new message to the list of messages
     * @param message the message to be added to the message window
     * @return whether or not it successfully sent
     */
    public boolean doSendChat(String message);

    /**
     * Checks to see if it is player's turn and if he hasn't rolled yet
     * @return whether or not he can roll the dice now
     */
    public boolean canRollDice();

    /**
     * Allows the user to roll the dice to start his turn
     */
    public void rollDice();

    /**
     * Checks if player can move robber piece
     * @return whether or not the player can move the robber and select a player to rob
     */
    public boolean canRobPlayer();

    /**
     * Allows player to move robber piece and select a player to rob
     */
    public void doRobPlayer();

    /**
     * @return whether or not a player can finish his turn
     */
    public boolean canFinishTurn();

    /**
     * Allows a player to send to the server that he has finished his turn
     */
    public void finishTurn();

    /**
     * Checks to see if player has adequate resources and if there are remaining devCards to be bought
     * @return whether or not a player can buy a dev card
     */
    public boolean canBuyDevCard();

    /**
     * Allows a player to purchase a devCard
     * by updating his / the banks resource cards and devCards
     */
    public void buyDevCard();

    /**
     * Checks to see if player can play this dev card right now
     * @return
     */
    public boolean canPlayYearOfPlenty();


    /**
     * Plays this dev card
     */
    public void doPlayYearOfPlenty();

    /**
     * Checks to see if player can play this dev card right now
     * @return
     */
    public boolean canPlayRoadBuilding();

    /**
     * Plays this dev card
     */
    public void doPlayRoadBuilding();

    /**
     * Checks to see if player can play this dev card right now
     * @return
     */
    public boolean canPlaySoldier();

    /**
     * Plays this dev card
     */
    public void doPlaySoldier();

    /**
     * Checks to see if player can play this dev card right now
     * @return
     */
    public boolean canPlayMonopoly();

    /**
     * Plays this dev card
     */
    public void doPlayMonopoly();

    /**
     * Checks to see if player can play this dev card right now
     * @return
     */
    public boolean canPlayMonument();

    /**
     * Plays this dev card
     */
    public void doPlayMonument();

    /**
     * Checks to see if player can build a road right now
     * @param location where the user wants to build the road
     * @return true if user is able to build road at location right now
     */
    public boolean canBuildRoad(EdgeLocation location);

    /**
     * Builds a road for player at given location
     * @param location where the user wants to build the road
     */
    public void doBuildRoad(EdgeLocation location);

    /**
     * Checks to see if player can build a settlement right now
     * @param location where the user wants to build the road
     * @return true if user is able to build road at location right now
     */
    public boolean canBuildSettlement(VertexLocation location);

    /**
     * Builds a settlement for player at given location
     * @param location where the user wants to build the road
     */
    public void doBuildSettlement(VertexLocation location);

    /**
     * Checks to see if player can build a city right now
     * @param location where the user wants to build the road
     * @return true if user is able to build road at location right now
     */
    public boolean canBuildCity(VertexLocation location);

    /**
     * Builds a city for player at given location
     * @param location where the user wants to build the road
     */
    public void doBuildCity(VertexLocation location);

    /**
     * Checks to see if player can offer a given trade right now
     * @param trade details of the trade being offered
     * @return true if trade can be made right now
     */
    public boolean canOfferTrade(DomesticTrade trade);

    /**
     * Sends a trade offer to player that was selected
     * @param trade details of the trade being offered
     */
    public void doOfferTrade(DomesticTrade trade);

    /**
     * Checks to see if player is able to accept a given trade offer
     * @param trade details of the trade being offered
     * @return true if player is able to accept trade
     */
    public boolean canAcceptTrade(DomesticTrade trade);

    /**
     * Allows player to accept trade that was offered them
     * @param trade details of the trade being made
     */
    public void doAcceptTrade(DomesticTrade trade);

    /**
     * Checks to see if player can offer a maritime trade right now
     * @param trade details of the trade being offered
     * @return true if trade can be made right now
     */
    public boolean canMaritimeTrade(MaritimeTrade trade);

    /**
     * Sends a trade offer to bank
     * @param trade details of the trade being offered
     */
    public void doMaritimeTrade(MaritimeTrade trade);

    /**
     * Checks to see if player can discard the selected resource cards
     * @param cards the resource cards the player is discarding
     * @return true if cards can be discarded
     */
    public boolean canDiscardCards(ResourceSet cards);

    /**
     * Discards the selected cards from a players resource list
     * @param cards the resource cards the player is discarding
     */
    public void doDiscardCards(ResourceSet cards);
	
	
}
