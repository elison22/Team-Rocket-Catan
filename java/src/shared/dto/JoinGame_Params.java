package shared.dto;

public class JoinGame_Params {
	
	private int id;
	
	/** The color the user wants to join as. Valid colors: ['red', 'green', 
	 * 'blue', 'yellow', 'puce', 'brown', 'white', 'purple', 'orange']
	 */
	private String color;

	public JoinGame_Params() {}

	public JoinGame_Params(int id, String color) {
		super();
		this.id = id;
		this.color = color;
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
	
	

}
