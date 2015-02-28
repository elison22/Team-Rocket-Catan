package serializer;

import java.util.ArrayList;

import model.board.Board;
import model.board.BoardException;
import model.cards.GameBank;
import model.chat.Chat;
import model.game.GameModel;
import model.game.TurnTracker;
import model.player.Player;
import model.trade.TradeOffer;
import serializer.json.JsonClientModel;
import serializer.json.JsonPlayer;
import shared.dto.Game_DTO;
import shared.dto.Player_DTO;

import com.google.gson.Gson;


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
			if (player != null)
				newPlayers.add(new Player(player));
		}
		GameBank newBank = new GameBank(newModel.getBank(), newModel.getDevCards());
		TurnTracker newTracker = new TurnTracker(newModel.getTurnTracker());
		Board newBoard = new Board(newModel.getMap());
		Chat newChat = new Chat(newModel.getChat());
		Chat newGameHistory = new Chat(newModel.getLog());
		TradeOffer tradeOffer = new TradeOffer(newModel.getTradeOffer());
		
		game.setTradeOffer(tradeOffer);
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
	
	public Game_DTO[] deSerializeGameList(String gameListJson) {
		return gson.fromJson(gameListJson, Game_DTO[].class);
	}

	public Game_DTO deSerializeGame(String newGameJson) {
		return gson.fromJson(newGameJson, Game_DTO.class);
	}
	
	public Player_DTO deSerializeUserCookie(String userCookieJson) {
		JsonPlayer jPlayer = gson.fromJson(userCookieJson, JsonPlayer.class);
		return new Player_DTO(null, jPlayer.getName(), jPlayer.getPlayerID());
	}
}
