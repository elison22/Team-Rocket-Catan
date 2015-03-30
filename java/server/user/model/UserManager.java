package user.model;

import java.util.HashSet;
import java.util.Hashtable;

/**
 * @author Chad
 *
 * This class maintains a list of all currently registered users. This class
 * stores each user in a hashset based on the username, so username uniqueness
 * is verified before adding any new users.
 */
@SuppressWarnings("unused")
public class UserManager {

	private Hashtable<String, User> users;
	private int players;
	
	public UserManager() {
		users = new Hashtable<String, User>();
		players = 0;
	}
	
	/**Determine whether or not a user is registered.
	 * 
	 * @param username Username of the user to find.
	 * @return True if found, false if otherwise.
	 */
	public boolean hasUser(String username) {
		if (!validateUsername(username))
			return false;
		return users.containsKey(username);
	}
	
	/**Verify that the given password matches the one stored with the given
	 * username.
	 * 
	 * @param username Username whose password is to be checked.
	 * @param password The given password to be compared with the stored password.
	 * @return True if passwords match, false if otherwise.
	 */
	public boolean checkPassword(String username, String password) {
		return getUser(username).getPassword().equals(password);
	}
	
	/**Validates the given username and password then creates a new user.
	 * 
	 * @param username Desired username.
	 * @param password Desired password to be stored with the given username.
	 * @return True if user was successfully created, false if otherwise.
	 */
	public boolean createNewUser(String username, String password) {
		if (!validateUsername(username) || !validatePassword(password))
			return false;
		users.put(username, new User(username, password, players++));
		return true;
	}
	
	/**Returns the user object with the given username.
	 * 
	 * @param username Username of the user object to be retrieved.
	 * @return User object with the given username, null if it can't be found.
	 */
	public User getUser(String username) {
		return users.get(username);
	}
	
	/**Makes sure the given username is unique and that it contains 3 to 7 
	 * characters consisting of only letters, numbers, underscores, and dashes.
	 * 
	 * @param username Username to be validated.
	 * @return True if valid, false if otherwise.
	 */
	private boolean validateUsername(String username) {
		String userPattern = "^[0-9a-zA-Z_-]{3,7}$";
		return username.matches(userPattern);
	}
	
	/**Makes sure the given password contains 5 to 25 characters consisting of 
	 * only letters, numbers, underscores, and dashes.
	 * 
	 * @param password Password to be validated.
	 * @return True if valid, false if otherwise.
	 */
	private boolean validatePassword(String password) {
		String passwordPattern = "^[0-9a-zA-Z_-]{5,25}$";
		return password.matches(passwordPattern);
	}
	
	public int getUserID() {
		return players;
	}
}
