package shared.dto;



/**@author Chad
 * Jan 26, 2015
 * 
 * This class contains the parameters needed by the /games/create server method.
 */


public class CreateGame_Params {
	
	
	private Boolean randomTiles;
	private Boolean randomNumbers;
	private Boolean randomPorts;
	private String name;

	public CreateGame_Params() {}
	
	public CreateGame_Params( Boolean randomTiles, Boolean randomNumbers,
			Boolean randomPorts, String name) {
		setRandomTiles(randomTiles);
		setRandomNumbers(randomNumbers);
		setRandomPorts(randomPorts);
		setName(name);
	}
	
	public Boolean getRandomTiles() {
		return randomTiles;
	}

	public void setRandomTiles(Boolean randomTiles) {
		this.randomTiles = randomTiles;
	}

	public Boolean getRandomNumbers() {
		return randomNumbers;
	}

	public void setRandomNumbers(Boolean randomNumbers) {
		this.randomNumbers = randomNumbers;
	}

	public Boolean getRandomPorts() {
		return randomPorts;
	}

	public void setRandomPorts(Boolean randomPorts) {
		this.randomPorts = randomPorts;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
