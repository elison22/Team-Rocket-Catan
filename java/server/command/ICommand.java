package command;

/**@author Chad
 * 
 * Interface for our command objects.
 */
public interface ICommand {
	
	// execute() will return an object for the sake of those commandobjects that 
	// will need to return a model or another form of json (games/list, etc).
	// The alternative would be to have the handlers make direct calls to the
	// facade which seems to eliminate the purpose of using commandobjects to
	// begin with.
	public Object execute();
}
