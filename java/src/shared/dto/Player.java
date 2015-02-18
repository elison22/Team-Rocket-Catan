package shared.dto;

public class Player {
	private String color;
	private String name;
	private int id;
	
	public Player() {}
	
	public Player(String color, String name, int id) {
		setColor(color);
		setName(name);
		setId(id);
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "{" + color + "," + name + "," + id + "}";
	}
}
