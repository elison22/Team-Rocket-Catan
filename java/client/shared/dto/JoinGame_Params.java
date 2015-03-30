package shared.dto;

public class JoinGame_Params {
	
	private int id;
	
	/** The color the user wants to join as. Valid colors: ['red', 'green', 
	 * 'blue', 'yellow', 'puce', 'brown', 'white', 'purple', 'orange']
	 */
	private String color;
	
	private String player;
	private int playerId;

	public JoinGame_Params() {}

	public JoinGame_Params(int id, String color) {
		super();
		setId(id);
		setColor(color);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
}
