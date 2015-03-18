package user;

import shared.dto.*;

/**
 * @author Chad
 *
 * This interface defines all methods that involve accessing the UserManager.
 */
public interface IUserFacade {

	/**This method attempts to log in an already registered user. If the user
	 * hasn't been registered or if the password is wrong this method will 
	 * false.
	 * 
	 * @param username Username of the user attempting to log in.
	 * @param password Password associated with the given username.
	 * @return True if login was successful, false if otherwise.
	 */
	public boolean login(Login_Params params);
	
	/**Attempts to register a new user. For this to be successful, the username
	 * and password must fit the defined character constraints (numbers,
	 * letters, underscores, and dashes), and the username must be unique (not 
	 * already in use).
	 * 
	 * @param username Desired username to register. Between 3 and 7 characters
	 * long (inclusive).
	 * @param password Password to associate with the username. Between 5 and 25
	 * characters long (inclusive).
	 * @return True if all constraints are met, false otherwise.
	 */
	public boolean register(Login_Params params);
}
