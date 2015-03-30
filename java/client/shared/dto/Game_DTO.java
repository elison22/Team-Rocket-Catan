package shared.dto;

public class Game_DTO {
	
	private String title;
	private int id;
	private Player_DTO[] players;

	public Game_DTO() {}
	
	public Game_DTO(String title, int id, Player_DTO[] players) {
		setTitle(title);
		setId(id);
		setPlayers(players);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Player_DTO[] getPlayers() {
		return players;
	}

	public void setPlayers(Player_DTO[] players) {
		this.players = players;
	}

}
