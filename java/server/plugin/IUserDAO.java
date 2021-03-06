package plugin;

import user.model.User;

/**
 * This class defines all of the methods that will be called by the server
 * to perform any user-related operation with any given database.
 * @author gseccles
 *
 */
public interface IUserDAO {
	
	/**
	 * Adds a new user to the current database
	 * @param userName
	 * @param password
	 * @return true if the operations succeeds, otherwise false
	 */
	public boolean addUser(String userName, String password);
	
	/**
	 * Sets a new password for the user if the oldPassword matches the password in the database for the given userName
	 * @param oldPassword
	 * @param userName
	 * @param newPassword
	 * @return true if the operations succeeds, otherwise false
	 */
	public boolean updateUserPassword(String userName, String oldPassword, String newPassword);
	
	/**
	 * Authenticates user login information
	 * @param userName
	 * @param password
	 * @return true if the operations succeeds, otherwise false
	 */
	public boolean validateUser(String userName, String password);
	
	/**
	 * Retrieves a user from the database based on its username and password
	 * @param userName
	 * @param password
	 * @return User with the matching username and password
	 */
	public User getUser(String userName, String password);
}
