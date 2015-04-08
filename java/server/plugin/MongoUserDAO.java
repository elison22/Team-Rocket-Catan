package plugin;

import user.model.User;

public class MongoUserDAO implements IUserDAO {

	@Override
	public boolean addUser(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserPassword(String userName, String oldPassword,
			String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean validateUser(String userName, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User getUser(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
