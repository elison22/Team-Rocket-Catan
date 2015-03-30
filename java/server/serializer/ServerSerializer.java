package serializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import model.board.BoardException;
import model.sboard.ServerBoard;
import model.sboard.ServerBoardException;
import model.sboard.ServerConstructable;
import model.sboard.ServerHexTile;
import model.scards.ServerDevCard;
import model.scards.ServerGameBank;
import model.schat.ServerChat;
import model.schat.ServerMessage;
import model.sgame.ServerGame;
import model.sgame.ServerTurnTracker;
import model.splayer.ServerPlayer;
import model.strade.ServerTradeOffer;
import serializer.json.JsonClientModel;
import serializer.json.JsonDevCardList;
import serializer.json.JsonEdgeLocation;
import serializer.json.JsonHex;
import serializer.json.JsonHexLocation;
import serializer.json.JsonMap;
import serializer.json.JsonMessageLine;
import serializer.json.JsonMessageList;
import serializer.json.JsonPlayer;
import serializer.json.JsonPort;
import serializer.json.JsonResourceList;
import serializer.json.JsonRoad;
import serializer.json.JsonTradeOffer;
import serializer.json.JsonTurnTracker;
import serializer.json.JsonVertexLocation;
import serializer.json.JsonVertexObject;
import shared.definitions.HexType;
import shared.definitions.PortType;
import shared.definitions.ResourceType;
import shared.dto.Game_DTO;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.HexLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;

import com.google.gson.Gson;

import command.AcceptTrade_CO;
import command.BuildCity_CO;
import command.BuildRoad_CO;
import command.BuildSettlement_CO;
import command.BuyDevCard_CO;
import command.CommandWrapper;
import command.CreateGame_CO;
import command.DiscardCards_CO;
import command.FinishTurn_CO;
import command.ICommandObject;
import command.JoinGame_CO;
import command.MaritimeTrade_CO;
import command.Monopoly_CO;
import command.Monument_CO;
import command.OfferTrade_CO;
import command.RoadBuilding_CO;
import command.RobPlayer_CO;
import command.RollNumber_CO;
import command.SendChat_CO;
import command.Soldier_CO;
import command.YearOfPlenty_CO;

/**
 * @author Chad
 *
 * This class serializes all data that needs to reach the client in JSON format.
 */
public class ServerSerializer {
	
	Gson gson;

	public ServerSerializer() {
		gson = new Gson();
	}
	
	/**Converts a given model into json format.
	 * 
	 * @param serverModel The model of the game to be serialized.
	 * @return The json format of the model as a String.
	 */
	public String serializeGameModel(ServerGame serverModel) {
		return gson.toJson(convertServerModel(serverModel));
	}
	
	/**Converts the current list of games to a json array inside a String.
	 * 
	 * @param games The array of games to be serialized.
	 * @return The String containing the json array of games.
	 */
	public String serializeGameList(Game_DTO[] games) {
		return gson.toJson(games);
	}
	
	/**Serializes a newly created game in json format.
	 * 
	 * @param newGame The newly created game to be serialized.
	 * @return The String containing the json format of the newly created game.
	 */
	public String serializeNewGame(Game_DTO newGame) {
		return gson.toJson(newGame);
	}
	
	/**Serializes the player data to be transmitted as a cookie.
	 * 
	 * @param player The player whose info needs to be serialized.
	 * @return The String containing the json data.
	 */
	public String serializeCookie(JsonPlayer player) {
		return null;
	}
	
	public String serializeCommand(ICommandObject command) {
		return gson.toJson(command);
	}
	
	public String serializeCommands(ICommandObject[] commands)
	{
		return gson.toJson(commands);
	}
	
	public ICommandObject[] deSerializeCommands(String commandsJson) throws Exception {
		// Deserialize to the CommandWrappers class. From here we will be able
		// to determine which explicit command object to build
		CommandWrapper[] commandWrappers = gson.fromJson(commandsJson, CommandWrapper[].class);
		
		// The array of the explicit commands
		ICommandObject[] commands = new ICommandObject[commandWrappers.length];
		
		// Determine what type of command it is and build it while adding it
		// to the commands array.
		int i = 0;
		for (CommandWrapper command : commandWrappers) {
			switch (command.getType()) {
			case "AcceptTrade":
				commands[i] = new AcceptTrade_CO(command.getAcceptTradeParams(), null);
				break;
			case "BuildCity":
				commands[i] = new BuildCity_CO(command.getBuildCityParams(), null);
				break;
			case "BuildRoad":
				commands[i] = new BuildRoad_CO(command.getBuildRoadParams(), null);
				break;
			case "BuildSettlement":
				commands[i] = new BuildSettlement_CO(command.getBuildSettlementParams(), null);
				break;
			case "BuyDevCard":
				commands[i] = new BuyDevCard_CO(command.getBuyDevCardParams(), null, command.getSeed());
				break;
			case "CreateGame":
				commands[i] = new CreateGame_CO(command.getCreateGameParams(), null, command.getSeed());
				break;
			case "DiscardCards":
				commands[i] = new DiscardCards_CO(command.getDiscardCardsParams(), null);
				break;
			case "FinishTurn":
				commands[i] = new FinishTurn_CO(command.getFinishTurnParams(), null);
				break;
			case "JoinGame":
				commands[i] = new JoinGame_CO(command.getJoinGameParams(), null);
				break;
			case "MaritimeTrade":
				commands[i] = new MaritimeTrade_CO(command.getMaritimeTradeParams(), null);
				break;
			case "Monopoly":
				commands[i] = new Monopoly_CO(command.getMonopolyParams(), null);
				break;
			case "Monument":
				commands[i] = new Monument_CO(command.getMonumentParams(), null);
				break;
			case "OfferTrade":
				commands[i] = new OfferTrade_CO(command.getOfferTradeParams(), null);
				break;
			case "RoadBuilding":
				commands[i] = new RoadBuilding_CO(command.getRoadBuildingParams(), null);
				break;
			case "RobPlayer":
				commands[i] = new RobPlayer_CO(command.getRobPlayerParams(), null, command.getSeed());
				break;
			case "RollNumber":
				commands[i] = new RollNumber_CO(command.getRollNumberParams(), null);
				break;
			case "SendChat":
				commands[i] = new SendChat_CO(command.getChatParams(), null);
				break;
			case "Soldier":
				commands[i] = new Soldier_CO(command.getSoldierParams(), null, command.getSeed());
				break;
			case "YearOfPlenty":
				commands[i] = new YearOfPlenty_CO(command.getYearOfPlentyParams(), null);
				break;
			default:
				throw new Exception("No case found for: \n" + gson.toJson(command));
			}
			++i;
		}
		return commands;
	}
	
	public ServerGame deSerializeFromServer(String json) throws BoardException, ServerBoardException
	{
		JsonClientModel newModel = gson.fromJson(json, JsonClientModel.class);
		ArrayList<ServerPlayer> newPlayers = new ArrayList<ServerPlayer>();
		for(JsonPlayer player : newModel.getPlayers())
		{
			if (player != null)
				newPlayers.add(new ServerPlayer(player));
		}
		ServerGameBank newBank = new ServerGameBank(newModel.getBank(), newModel.getDevCards());
		ServerTurnTracker newTracker = new ServerTurnTracker(newModel.getTurnTracker());
		ServerBoard newBoard = new ServerBoard(newModel.getMap());
		ServerChat newChat = new ServerChat(newModel.getChat());
		ServerChat newGameHistory = new ServerChat(newModel.getLog());
		
		ServerTradeOffer tradeOffer = null;
		if(newModel.getTradeOffer() != null) 
			tradeOffer = new ServerTradeOffer(newModel.getTradeOffer());
			
		ServerGame game = new ServerGame();
		
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
	
	/**************************************************************************
	 *  All following methods are used in convert the serverModel into a client
	 *  compatible Json String. 
	 **************************************************************************/
	
	private JsonClientModel convertServerModel(ServerGame serverModel) {
		return new JsonClientModel(convertResourceList(serverModel.getCardBank()),
								   convertChatList(serverModel.getChat()),
								   convertChatList(serverModel.getGameHistory()),
								   convertMap(serverModel.getMap()),
								   convertPlayerList(serverModel.getPlayerList()),
								   convertTradeOffer(serverModel.getTradeOffer()),
								   convertDevCardList(serverModel.getDevBank()),
								   convertTurnTracker(serverModel.getTurnTracker()),
								   serverModel.getVersionNumber(),
								   serverModel.getWinner());
	}
	
	private JsonResourceList convertResourceList(Map<ResourceType, Integer> resourceMap) {
		return new JsonResourceList(resourceMap.get(ResourceType.BRICK), 
									resourceMap.get(ResourceType.ORE),
									resourceMap.get(ResourceType.SHEEP),
									resourceMap.get(ResourceType.WHEAT),
									resourceMap.get(ResourceType.WOOD));
	}
	
	private JsonMessageList convertChatList(List<ServerMessage> messages) {
		JsonMessageLine[] jsonMessages = new JsonMessageLine[messages.size()];
		
		int i = 0;
		for (ServerMessage sm : messages) {
			jsonMessages[i] = new JsonMessageLine(sm.getMessage(), sm.getOwner());
			++i;
		}
		return new JsonMessageList(jsonMessages);
	}
	
	private JsonMap convertMap(ServerBoard map) {
		return new JsonMap(convertHexes(map.getTiles()),
						   convertPorts(map.getPortTypes()),
						   convertRoads(map.getRoadPieces()),
						   convertSettlements(map.getBuildingPieces()),
						   convertCities(map.getBuildingPieces()),
						   3,
						   convertRobber(map.getRobberLoc()));
	}
	
	private JsonHex[] convertHexes(HashMap<HexLocation, ServerHexTile> tiles) {
		JsonHex[] jsonHexes = new JsonHex[tiles.size()];
		
		int i = 0;
		for (Map.Entry<HexLocation, ServerHexTile> entry : tiles.entrySet()) {
			HexLocation hexLocation = entry.getKey();
			ServerHexTile serverHexTile = entry.getValue();
			
			// For desert tiles:
			if (serverHexTile.getType() == HexType.DESERT) {
				jsonHexes[i] = new JsonHex(new JsonHexLocation(hexLocation.getX(), hexLocation.getY()),
						   				   null,
						   				   null);
			}
			
			// For everything else:
			else {
				jsonHexes[i] = new JsonHex(new JsonHexLocation(hexLocation.getX(), hexLocation.getY()),
						   				   serverHexTile.getType().toString().toLowerCase(),
						   				   serverHexTile.getDiceNum());
			}
			
			++i;
		}
		return jsonHexes;
	}
	
	private JsonPort[] convertPorts(HashMap<EdgeLocation, PortType> ports) {
		JsonPort[] jsonPorts = new JsonPort[ports.size()];
		
		int i = 0;
		for (Map.Entry<EdgeLocation, PortType> entry : ports.entrySet()) {
			EdgeLocation edge = entry.getKey();
			PortType portType = entry.getValue();
			
			String resource;
			int ratio;
			switch (portType) {
				case THREE_FOR_ONE:
					resource = null;
					ratio = 3;
					break;
				default:
					resource = portType.toString();
					ratio = 2;
					break;
			}
			
			jsonPorts[i] = new JsonPort(resource,
									    new JsonHexLocation(edge.getHexLoc().getX(), edge.getHexLoc().getY()),
									    edge.getDir().toString(),
									    ratio);
			++i;
		}
		return jsonPorts;
	}
	
	private JsonRoad[] convertRoads(HashMap<EdgeLocation, ServerConstructable> roadLoc) {
	
		ArrayList<JsonRoad> roads = new ArrayList<JsonRoad>();
		Iterator<Entry<EdgeLocation, ServerConstructable>> it = roadLoc.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry<EdgeLocation, ServerConstructable> pair = (Entry<EdgeLocation, ServerConstructable>)it.next();
	        EdgeLocation edgeLoc = pair.getKey();
	        HexLocation hex = edgeLoc.getHexLoc();
	        String dir = EdgeDirection.acronym(edgeLoc.getDir());
	        JsonEdgeLocation jEdgeLoc = new JsonEdgeLocation(hex.getX(), hex.getY(), dir);
	        int owner = pair.getValue().getOwner();
	        roads.add(new JsonRoad(owner, jEdgeLoc));
	    }
		
		return roads.toArray(new JsonRoad[roads.size()]);
	}
	
	private JsonVertexObject[] convertSettlements(HashMap<VertexLocation, ServerConstructable> buildings) {
		ArrayList<JsonVertexObject> settlements = new ArrayList<JsonVertexObject>();
		Iterator<Entry<VertexLocation, ServerConstructable>> it = buildings.entrySet().iterator();
		
		while (it.hasNext()) {
	        Map.Entry<VertexLocation, ServerConstructable> pair = (Entry<VertexLocation, ServerConstructable>)it.next();
            if(!pair.getValue().isSettlement()) continue;
	        VertexLocation vertLoc = pair.getKey();
	        HexLocation hex = vertLoc.getHexLoc();
	        String dir = VertexDirection.acronym(vertLoc.getDir());
	        JsonVertexLocation jEdgeLoc = new JsonVertexLocation(hex.getX(), hex.getY(), dir);
	        int owner = pair.getValue().getOwner();
	        settlements.add(new JsonVertexObject(owner, jEdgeLoc));
	    }

		return settlements.toArray(new JsonVertexObject[settlements.size()]);
	}
	
	private JsonVertexObject[] convertCities(HashMap<VertexLocation, ServerConstructable> buildings) {
		ArrayList<JsonVertexObject> cities = new ArrayList<JsonVertexObject>();
		Iterator<Entry<VertexLocation, ServerConstructable>> it = buildings.entrySet().iterator();
		
		while (it.hasNext()) {
	        Map.Entry<VertexLocation, ServerConstructable> pair = (Entry<VertexLocation, ServerConstructable>)it.next();
            if(pair.getValue().isSettlement()) continue;
	        VertexLocation vertLoc = pair.getKey();
	        HexLocation hex = vertLoc.getHexLoc();
	        String dir = VertexDirection.acronym(vertLoc.getDir());
	        JsonVertexLocation jEdgeLoc = new JsonVertexLocation(hex.getX(), hex.getY(), dir);
	        int owner = pair.getValue().getOwner();
	        cities.add(new JsonVertexObject(owner, jEdgeLoc));
	    }
		
		return cities.toArray(new JsonVertexObject[cities.size()]);
	}
	
	private JsonHexLocation convertRobber(HexLocation loc) {
		return new JsonHexLocation(loc.getX(), loc.getY());
	}
	
	private JsonPlayer[] convertPlayerList(List<ServerPlayer> players) {
		JsonPlayer[] jsonPlayers = new JsonPlayer[4];
		
		int i = 0;
		for (ServerPlayer player : players) {
			jsonPlayers[i] = new JsonPlayer(player.getRemainingCities(),
									 player.getColor(),
									 player.hasDiscarded(),
									 player.getBank().getMonumentCount(),
									 player.getName(),
									 convertDevCardList(player.getBank().getNewDevCards()),
									 convertDevCardList(player.getBank().getOldDevCards()),
									 player.getPlayerIdx(),
									 player.getPlayerDevCard(),
									 player.getPlayerID(),
									 convertResourceList(player.getBank().getResCards()),
									 player.getRemainingRoads(),
									 player.getRemainingSettlements(),
									 player.getSoldierDevs(),
									 player.getVictoryPoints());
			++i;
		}
		return jsonPlayers;
	}
	
	private JsonDevCardList convertDevCardList(List<ServerDevCard> devCards) {
		int monopoly = 0;
		int monument = 0;
		int roadBuilding = 0;
		int soldier = 0;
		int yearOfPlenty = 0;
		
		for (ServerDevCard devCard : devCards) {
			switch (devCard.getType()) {
				case MONOPOLY:
					++monopoly;
					break;
				case MONUMENT:
					++monument;
					break;
				case ROAD_BUILD:
					++roadBuilding;
					break;
				case SOLDIER:
					++soldier;
					break;
				case YEAR_OF_PLENTY:
					++yearOfPlenty;
					break;
			}
		}
		return new JsonDevCardList(monopoly, monument, roadBuilding, soldier, yearOfPlenty);
	}
	
	private JsonTurnTracker convertTurnTracker(ServerTurnTracker turnTracker) {
		int whoseTurn = turnTracker.getCurrentPlayerIndex();
		String state = turnTracker.getCurrentState().toString();
		int longestRoad = turnTracker.getLongestRoadPlayerIndex();
		int largestArmy = turnTracker.getLargestArmyPlayerIndex();
		return new JsonTurnTracker(whoseTurn, state, longestRoad, largestArmy);
	}
	
	private JsonTradeOffer convertTradeOffer(ServerTradeOffer tradeOffer) {
		if(tradeOffer != null) {
			int sender = tradeOffer.getSender();
			int receiver = tradeOffer.getReceiver();
			HashMap<ResourceType, Integer> offer = tradeOffer.getResources();
			Iterator<Entry<ResourceType, Integer>> it = offer.entrySet().iterator();
			
			int brick = 0;
			int ore = 0; 
			int sheep = 0;
			int wheat = 0;
			int wood = 0;
		    while (it.hasNext()) {
		        Map.Entry<ResourceType, Integer> pair = (Entry<ResourceType, Integer>)it.next();
		        if(pair.getKey() == ResourceType.BRICK)
		        	brick = pair.getValue();
		        else if(pair.getKey() == ResourceType.ORE)
		        	ore = pair.getValue();
		        else if(pair.getKey() == ResourceType.SHEEP)
		        	sheep = pair.getValue();
		        else if(pair.getKey() == ResourceType.WHEAT)
		        	wheat = pair.getValue();
		        else if(pair.getKey() == ResourceType.WOOD)
		        	wood = pair.getValue();
		    }
			JsonResourceList jResList = new JsonResourceList(brick, ore, sheep, wheat, wood);
			
			return new JsonTradeOffer(sender, receiver, jResList);
		}
		return null;
	}
}
