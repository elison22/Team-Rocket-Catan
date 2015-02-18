package shared.dto;

public class GameList {
	
	private String title;
	private int id;
	private Player[] players;

	public GameList() {}
	
	public GameList(String title, int id, Player[] players) {
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

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

}
