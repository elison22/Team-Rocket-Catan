package serializer;

import java.util.ArrayList;

import proxy.MockProxy;
import model.board.Board;
import model.board.BoardException;
import model.cards.*;
import model.chat.Chat;
import model.game.GameModel;
import model.game.TurnTracker;
import model.player.*;
import serializer.json.*;

import com.google.gson.*;


/**
 * This class handles the translation of Java objects to and from Json, which is used for data transfer between server and client
 * @author Owner
 *
 */
public class Serializer {
	
	/**
	 * Automates translation of Json
	 */
	private Gson gson;	
	
	/**
	 * Constructor
	 */
	public Serializer()
	{
		gson = new Gson();
	}
	
	/**
	 * Converts Json string into java object(s)
	 * @param json String received from the server that needs to be made into Java object(s)
	 * @throws BoardException 
	 */
	public GameModel deSerializeFromServer(GameModel game, String json) throws BoardException
	{
		JsonClientModel newModel = gson.fromJson(json, JsonClientModel.class);
		ArrayList<Player> newPlayers = new ArrayList<Player>();
		for(JsonPlayer player : newModel.getPlayers())
		{
			newPlayers.add(new Player(player));
		}
		GameBank newBank = new GameBank(newModel.getBank(), newModel.getDevCards());
		TurnTracker newTracker = new TurnTracker(newModel.getTurnTracker());
		Board newBoard = new Board(newModel.getMap());
		Chat newChat = new Chat(newModel.getChat());
		Chat newGameHistory = new Chat(newModel.getLog());
		
		
		game.setChat(newChat);
		game.setGameHistory(newGameHistory);
		game.setMap(newBoard);
		game.setTurnTracker(newTracker);
		game.setWinner(newModel.getWinner());
		game.setVersionNumber(newModel.getVersion());
		game.setCardBank(newBank);
		game.setPlayerList(newPlayers);
				
		return game;
	}
	
	public static void main(String[] args) {
		Serializer s = new Serializer();
		MockProxy mp = new MockProxy();
		GameModel gm = new GameModel();
		try {
			gm = s.deSerializeFromServer(gm, mp.model(-1));
			System.out.println("OK");
		} catch (BoardException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
