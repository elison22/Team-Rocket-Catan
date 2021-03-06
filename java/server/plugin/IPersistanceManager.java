package plugin;

/**
 * This class manages access to the data access objects for the rest of the program
 * @author gseccles
 *
 */
public interface IPersistanceManager {
	
	/**
	 * Provides access to the game data access object, which provides access to the database
	 * @return Abstract game data access object (works for multiple types of databases)
	 */
	public IGameDAO getGameDAO();
	
	/**
	 * Provides access to the user data access object, which provides access to the database
	 * @return Abstract user data access object (works for multiple types of databases)
	 */
	public IUserDAO getUserDAO();
	
	/**
	 * Allows the DAO objects to connect to and start a transaction with the database
	 */
	public void startTransaction();
	
	/**
	 * Allows the DAO objects to end a transaction with the database
	 */
	public void endTransaction();
}
