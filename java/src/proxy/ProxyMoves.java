package proxy;

/**@author Chad
 * Jan 19, 2015
 * 
 * This class communicates all in-game moves a player can do during their turn 
 * as well as accepting/rejecting player-offered trades. The parameters 
 * expected by the server are also specified. All parameters are Json formatted 
 * unless otherwise specified.
 */
public class ProxyMoves {
	
	public ProxyMoves() {}

	/**(POST) Sends a player's chat message to the server.
	 * 
	 * @param sendChatParams Should contain the playerIndex (int)
	 * and the message content (String)
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String sendChat(Object sendChatParams) {
		
		return null;
	}
	
	/**(POST) Sends the current player's dice roll to the server.
	 * 
	 * @param rollNumberParams Should contain the playerIndex (int 0-3)
	 * and the rolled number (int 2-12)
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String rollNumber(Object rollNumberParams) {
		return null;
	}
	
	/**(POST) Sends the new robber location as well as the index of
	 * the player to be robbed to the server.
	 * 
	 * @param robPlayerParams Should contain the current player's 
	 * playerIndex (int 0-3), the victimIndex (int 0-3), and the 
	 * robber's new location (HexLocation).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String robPlayer(Object robPlayerParams) {
		return null;
	}
	
	/**(POST) Tells the server to end the current player's turn.
	 * 
	 * @param finishTurnParams Should contain the playerIndex (int).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String finishTurn(Object finishTurnParams) {
		return null;
	}
	
	/**(POST) Tells the server that the current player wants to buy a 
	 * development card.
	 * 
	 * @param buyDevCardParams Should contain the playerIndex(integer).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String buyDevCard(Object buyDevCardParams) {
		return null;
	}
	
	/**(POST) Sends the server the current player and their two 
	 * resources to receive.
	 * 
	 * @param yearOfPlentyParams Should contain the playerIndex (int 0-3),
	 * resource1 (Resource), and resource2 (Resource).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String Year_of_Plenty(Object yearOfPlentyParams) {
		return null;
	}
	
	/**(POST) Sends the server the locations for the two roads to be
	 * build.
	 * 
	 * @param roadBuildingParams Should contain the playerIndex (int 0-3),
	 * spot1 (EdgeLocation), and spot2 (EdgeLocation).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String Road_Building(Object roadBuildingParams) {
		return null;
	}
	
	/**(POST) Tells the server the robber's new location as well as 
	 * which player will be robbed.
	 * 
	 * @param soldierParams Should contain the playerIndex (int 0-3),
	 * victimIndex (int 0-3), and the robber's new location (HexLocation).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String Soldier(Object soldierParams) {
		return null;
	}
	
	/**(POST) Tells the server to reward the current player with a 
	 * victory Point.
	 * 
	 * @param monumentParams Should contain the current 
	 * playerIndex (int 0-3).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String Monument(Object monumentParams) {
		return null;
	}
	
	/**(POST) Sends the server where the current player is building a
	 * road. It also tells the server whether or not the road is an 
	 * initial placement (free).
	 * 
	 * @param buildRoadParams Should contain
	 * the current player's playerIndex (int) and the 
	 * roadLocation (EdgeLocation) and whether or not the road is
	 * free (Boolean).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String buildRoad(Object buildRoadParams) {
		return null;
	}
	
	/**(POST) Tells the server to build a settlement for the current
	 * player at the specified location. Also tells the server whether
	 * or not the settlement is an initial placement (free).
	 * 
	 * @param buildSettlementParams Should contain the current 
	 * playerIndex (int 0-3), the settlement's vertexLocation 
	 * (VertexLocation), and whether or not it is free (Boolean).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String buildSettlement(Object buildSettlementParams) {
		return null;
	}
	
	/**(POST) Tells the server to build a city at the specified
	 * settlement location.
	 * 
	 * @param buildCityParams Should contain the current 
	 * playerIndex (int 0-3), and the city's vertexLocation
	 * (VertexLocation).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String buildCity(Object buildCityParams) {
		return null;
	}
	
	/**(POST) Tells the server to offer a trade from the current
	 * player to the receiving player as well as the trade offer.
	 * The offering player will send a ResourceList with positive
	 * numbers representing what they will receive, and negative
	 * numbers representing what they will give.
	 * 
	 * @param offerTradeParams Should contain the current 
	 * playerIndex (int 0-3), the offer (ResourceList), and the
	 * receive (int 0-3).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String offerTrade(Object offerTradeParams) {
		return null;
	}
	
	/**(POST) Tells the server to accept or reject and offered
	 * trade from another player.
	 * 
	 * @param acceptTradeParams Should contain the receiving
	 * player's playerIndex (int 0-3) and they're decision
	 * willAccept (boolean).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String acceptTrade(Object acceptTradeParams) {
		return null;
	}
	
	/**(POST) Sends the server the current player's desired
	 * maritime trade. The trade includes the resources to 
	 * be traded as well as the desired resource, and the 
	 * maritime trade's ratio.
	 * 
	 * @param maritimeTradeParams Should contain the current
	 * player's playerIndex (int 0-3). Optional parameters
	 * include the ratio (int), the player's inputResource
	 * (String), and the bank's outputResource (String).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String maritimeTrade(Object maritimeTradeParams) { 
		return null;
	}
	
	/**(POST) Tells the server to discard the current player's 
	 * specified resources.
	 * 
	 * @param discardCardsParams Should contain the current
	 * player's playerIndex (int) and the discardedCards 
	 * (ResourceList).
	 * @return Returns the game's json client model (See Game.model()).
	 */
	public String discardCards(Object discardCardsParams) {
		return null;
	}
	
}
