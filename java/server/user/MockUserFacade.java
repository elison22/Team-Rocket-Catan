package user;

/**
 * @author Chad
 *
 * This class can be used for testing. All methods will return hard coded 
 * results instead of retrieving data from the UserManager.
 */
public class MockUserFacade implements IUserFacade {

	@Override
	public boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

}
