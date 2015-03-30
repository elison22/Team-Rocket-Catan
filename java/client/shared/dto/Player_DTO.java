package shared.dto;

public class Player_DTO {
	private String color;
	private String name;
	private Integer id;
	
	public Player_DTO() {
		color = null;
		name = null;
		id = null;
	}
	
	public Player_DTO(String color, String name, int id) {
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
		if (id == null)
			return -1;
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
