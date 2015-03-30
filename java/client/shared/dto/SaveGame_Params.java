package shared.dto;

public class SaveGame_Params {
	
	/** Ths id of the game to be saved.*/
	int id;
	
	/** The name for the game to be saved as.*/
	String name;

	public SaveGame_Params() {}

	public SaveGame_Params(int id, String name) {
		super();
		setId(id);
		setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
