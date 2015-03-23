package model.splayer;

import java.util.ArrayList;
import java.util.HashMap;

import model.scards.ServerDevCard;
import model.scards.ServerPlayerBank;
import serializer.json.JsonPlayer;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.ResourceType;

/**
 * @author Hayden
 * Player holds all data for a specific player in the game. That data includes
 * the player's hand of cards called a PlayerBank, their name, the number of
 * victory points they have earned, and their index in the list of players.
 * This index also functions as an identifier for each player. This class also
 * implements the methods the Facade will call to check if a trade can be offered,
 * accepted, or if a certain constructable can be built.
 */
public class ServerPlayer {

    private ServerPlayerBank bank;			        // The player's bank of cards
    private String      color;                  // The player's color
    private boolean     discarded;              // True if the player has discarded this turn.
    @SuppressWarnings("unused")
	private int         monumentDevs;           // The number of monument cards a player holds. Used to determine when the cards can be played.
    private boolean     playedDevCard;          // True if the player has used a dev card this turn.
    private int         playerID;               // The player's unique ID
    private int         playerIdx;				// The player's index in list of players
    private String      playerName;			    // The player's name
    private int         remainingCities;		// The number of cities the player can build
    private int         remainingRoads;			// The number of roads the player has left to build
    private int         remainingSettlements;	// The number of settlements the player has left to build
    private int         soldierDevs;            // The number of played soldier cards. Used to determine largest army
    private int         victoryPoints;			// The player's victory points

    /**
     * Creates a new Player object with an empty PlayerBank object and zero victory points
     * @param playerIdx Initializes the player's index in the list of players
     * @param playerName The name the user entered upon starting the game.
     */
    public ServerPlayer(int playerIdx, int playerID, String playerName, String color){
        this.bank = new ServerPlayerBank();
        this.color = color;
        this.discarded = false;
        this.monumentDevs = 0;
        this.remainingCities = 4;
        this.remainingRoads = 15;
        this.remainingSettlements = 5;
        this.playedDevCard = false;
        this.playerID = playerID;
        this.playerIdx = playerIdx;
        this.playerName = playerName;
        this.soldierDevs = 0;
        this.victoryPoints = 0;
    }

    public ServerPlayer(JsonPlayer jsonPlayer){
        this.bank = new ServerPlayerBank(jsonPlayer.getResources(), jsonPlayer.getOldDevCards(), jsonPlayer.getNewDevCards());
        this.color = jsonPlayer.getColor();
        this.discarded = jsonPlayer.isDiscarded();
        this.monumentDevs = jsonPlayer.getMonuments();
        this.remainingCities = jsonPlayer.getCities();
        this.remainingRoads = jsonPlayer.getRoads();
        this.remainingSettlements = jsonPlayer.getSettlements();
        this.playedDevCard = jsonPlayer.isPlayedDevCard();
        this.playerID = jsonPlayer.getPlayerID();
        this.playerIdx = jsonPlayer.getPlayerIndex();
        this.playerName = jsonPlayer.getName();
        this.soldierDevs = jsonPlayer.getSoldiers();
        this.victoryPoints = jsonPlayer.getVictoryPoints();
    }

    /**
     * Called by the Facade to update players after polling
     * @param bank The player's bank of cards
     * @param victoryPoints The player's victoryPoints
     * @param remainingRoads The number of roads the player has remaining
     * @param remainingSettlements The number of settlements the player has remaining
     * @param remainingCities The number of cities the player has remaining
     */
    public void update(ServerPlayerBank bank, int victoryPoints, int remainingRoads, int remainingSettlements, int remainingCities){
        this.bank = bank;
        this.victoryPoints = victoryPoints;
        this.remainingRoads = remainingRoads;
        this.remainingSettlements = remainingSettlements;
        this.remainingCities = remainingCities;
    }

    public int getRemainingRoads() {
        return remainingRoads;
    }

    public int getRemainingCities() {
        return remainingCities;
    }

    public int getRemainingSettlements() {
        return remainingSettlements;
    }

    public ServerPlayerBank getBank(){
        return bank;
    }

    public int getVictoryPoints() {
        return victoryPoints;
    }

    public int getPlayerIdx() {
        return playerIdx;
    }

    public String getName() {
        return playerName;
    }

    public int getSoldierDevs() {
        return soldierDevs;
    }
    
    public boolean getPlayerDevCard() {
    	return playedDevCard;
    }

    public void setPlayedDevCard(boolean hasPlayedDev){
        playedDevCard = hasPlayedDev;
    }
    
    public void resetPlayer()
    {
    	this.bank = new ServerPlayerBank();
    	this.discarded = false;
        this.monumentDevs = 0;
        this.remainingCities = 4;
        this.remainingRoads = 15;
        this.remainingSettlements = 5;
        this.playedDevCard = false;
        this.soldierDevs = 0;
        this.victoryPoints = 0;
    }

    /**
     * Called by the Facade to determine if a player has the resources necessary to offer
     * a certain trade
     * @param playerIdx The player that offered the trade
     * @param offeredRes A map of offered resource cards
     * @return return whether a trade can be offered or not
     */
    public boolean canOfferTrade(int playerIdx, HashMap<ResourceType,Integer> offeredRes){
        return bank.hasResCards(offeredRes);
    }

    /**
     * Called by the Facade to determine if a player has the resources necessary to accept
     * a certain trade
     * @param playerIdx The player that offered the trade
     * @param desiredRes A list of resource cards desired by the other player
     * @return return whether a trade was accepted or not
     */
    public boolean canAcceptTrade(int playerIdx, HashMap<ResourceType,Integer> desiredRes){
        return bank.hasResCards(desiredRes);
    }

    /**
     * @param devCard type of dev card player wants to play
     * @return true if player has that dev card, hasn't already played it, and meets the
     * requirement to play that certain dev card.
     */
    public boolean canPlayDevCard(DevCardType devCard) {
        if (playedDevCard && devCard != DevCardType.MONUMENT){
            return false;
        }
        ArrayList<ServerDevCard> oldDevCards = bank.getOldDevCards();
        ArrayList<ServerDevCard> newDevCards = bank.getNewDevCards();
        for(ServerDevCard playerDev : oldDevCards){
            if(playerDev.getType() == devCard){
                switch(devCard){
                    case ROAD_BUILD:
                        return remainingRoads >= 2;
                    case MONUMENT:
                        int monuments = countMonumentDevs();
                        return (victoryPoints + monuments) >= 10;
                    default:
                    	break;
                }
                return true;
            }
        }
        for(ServerDevCard playerDev : newDevCards){
            if(playerDev.getType() == DevCardType.MONUMENT){
                int monuments = countMonumentDevs();
                return (victoryPoints + monuments) >= 10;
            }
        }
        return false;
    }

    public int countMonumentDevs(){
        int monuments = 0;
        ArrayList<ServerDevCard> oldDevCards = bank.getOldDevCards();
        ArrayList<ServerDevCard> newDevCards = bank.getNewDevCards();
        for(ServerDevCard playerDev : oldDevCards){
            if (playerDev.getType() == DevCardType.MONUMENT)
                monuments++;
        }
        for(ServerDevCard playerDev : newDevCards){
            if(playerDev.getType() == DevCardType.MONUMENT)
                monuments++;
        }
        return monuments;
    }

    public boolean canBuyDevCard() {
        return bank.canAffordDevCard();
    }

    public boolean canDiscardCards(HashMap<ResourceType, Integer> cards) {
        bank.hasResCards(cards);
        return true;
    }

    public boolean canBuildRoad(){
        if(remainingRoads > 13){
            return true;
        }
        if(remainingRoads > 0){
            if(bank.canAffordRoad()){
                return true;
            }
        }
        return false;
    }

    public boolean canBuildSettlement(){
        if(remainingSettlements > 0){
            if(bank.canAffordSettlement()){
                return true;
            }
        }
        return false;
    }

    public boolean canBuildCity(){
        if(remainingCities > 0){
            if(bank.canAffordCity()){
                return true;
            }
        }
        return false;
    }

    /**
     * Increments the resource count for the given type.
     * @param type The resource to take.
     */
    public void incResource(ResourceType type){
        bank.receiveResourceCard(type);
    }

    /**
     * Decrements the resource count for the given type.
     */
    public boolean decResource(ResourceType type){
        return bank.removeResourceCard(type);
    }

    /**
     * Decrements the road count. Also decrements the necessary resources if
     * isFree is false.
     * @param isFree Whether or not the road is free.
     */
    public void doBuildRoad(boolean isFree){
        remainingRoads--;
        if(!isFree)
            bank.buyPiece(PieceType.ROAD);
    }

    /**
     * Decrements the settlement count. Also decrements the necessary resources.
     */
    public void doBuildSettlement(boolean isFree){
        remainingSettlements--;
        if(!isFree) bank.buyPiece(PieceType.SETTLEMENT);
    }

    /**
     * Decrements the city count. Also decrements the necessary resources.
     */
    public void doBuildCity(){
        remainingCities--;
        bank.buyPiece(PieceType.CITY);
    }

    /**
     * Gives the player a dev card.
     * @param type The dev card type.
     */
    public void addDevCard(DevCardType type){
        bank.buyDevCard(new ServerDevCard(type));
        if(type == DevCardType.MONUMENT) monumentDevs++;
    }

    /**
     * Takes a dev card from the player. If it's a soldier, it also
     * increments their soldierDevs.
     * @return The dev card type.
     */
    public void playDevCard(DevCardType type){
        bank.playDevCard(new ServerDevCard(type));
        playedDevCard = true;
        if(type == DevCardType.SOLDIER) soldierDevs++;
    }

    public String getColor()
    {
        return color;
    }
    
    public void setColor(String color) {
    	this.color = color;
    }

    public int getPlayerID()
    {
        return playerID;
    }

    public int getResCount() {
        return bank.getResCount();
    }

    public boolean hasDiscarded() {
        return discarded;
    }

    public ResourceType getRandRes() {

        return bank.removeRandRes();

    }

    public int addPoint() {
        return ++victoryPoints;
    }

    public void endTurn() {

        playedDevCard = false;
        //other things that happen at the end of a turn
    }

}
