package model.game;

import java.util.ArrayList;

import model.trade.ITradeOffer;
import model.board.Board;
import model.cards.GameBank;
import model.player.Player;

public class GameModel {

    private String gameName;
    private int versionNumber;
    private ArrayList<Player> playerList;   // holds all the players
    private GameBank cardBank;                      // holds all the resource cards and all the dev cards
    private TurnTracker turnTracker;        // holds whos turn it is, as well as game state
    private Board Map;
    private ArrayList<String> messageList;
    private ITradeOffer tradeOffer;
    private ArrayList<Integer> players;


    public ITradeOffer getTradeOffer() {
        return tradeOffer;
    }

    public void setTradeOffer(ITradeOffer tradeOffer) {
        this.tradeOffer = tradeOffer;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(ArrayList<Player> playerList) {
        this.playerList = playerList;
    }

    public GameBank getCardBank() {
        return cardBank;
    }

    public void setCardBank(GameBank cardBank) {
        this.cardBank = cardBank;
    }

    public int getPlayerTurn() {
        return turnTracker.getCurrentPlayerIndex();
    }

    public int getLongestRoad() {
        return turnTracker.getLongestRoadPlayerIndex();
    }

    public void setTurnTracker(TurnTracker turnTracker) {
        this.turnTracker = turnTracker;
    }

    public Board getMap() {
        return Map;
    }

    public void setMap(Board map) {
        Map = map;
    }

    public ArrayList<String> getMessageList() {
        return messageList;
    }

    public void setMessageList(ArrayList<String> messageList) {
        this.messageList = messageList;
    }



}