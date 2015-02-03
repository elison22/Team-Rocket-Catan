package shared.dto;

public class Login_Params {
	
	private String username;
	private String password;

	public Login_Params() {}

	public Login_Params(String user, String password) {
		super();
		setUser(user);
		setPassword(password);
	}

	public String getUser() {
		return username;
	}

	public void setUser(String user) {
		this.username = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
