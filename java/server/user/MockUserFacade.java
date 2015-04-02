package user;

import shared.dto.Login_Params;

/**
 * @author Chad
 *
 * This class can be used for testing. All methods will return hard coded 
 * results instead of retrieving data from the UserManager.
 */
public class MockUserFacade implements IUserFacade {

	@Override
	public boolean login(Login_Params params) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean register(Login_Params params) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getUserID(String username) {
		// TODO Auto-generated method stub
		return "1";
	}

	@Override
	public boolean hasUser(String username) {
		// TODO Auto-generated method stub
		return true;
	}

}