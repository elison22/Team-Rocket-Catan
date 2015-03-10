package shared.dto;

/**@author Chad
 * Jan 28, 2015
 * 
 * Store all parameters needed to load a game that has been saved to the 
 * server.
 */
public class LoadGame_Params {
	
	// name of the file the game was saved as
	private String name;

	public LoadGame_Params() {}

	public LoadGame_Params(String name) {
		super();
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
