package client.login;

import client.base.*;
import client.misc.*;
import facade.IClientFacade;


/**
 * Implementation for the login controller
 */
public class LoginController extends Controller implements ILoginController {

	private IMessageView messageView;
	private IAction loginAction;
	private IClientFacade modelFacade;
	
	/**
	 * LoginController constructor
	 * 
	 * @param view Login view
	 * @param messageView Message view (used to display error messages that occur during the login process)
	 */
	public LoginController(ILoginView view, IMessageView messageView, IClientFacade modelFacade) {

		super(view);
		
		this.messageView = messageView;
		
		this.modelFacade = modelFacade;
	}
	
	public ILoginView getLoginView() {
		
		return (ILoginView)super.getView();
	}
	
	public IMessageView getMessageView() {
		
		return messageView;
	}
	
	/**
	 * Sets the action to be executed when the user logs in
	 * 
	 * @param value The action to be executed when the user logs in
	 */
	public void setLoginAction(IAction value) {
		
		loginAction = value;
	}
	
	/**
	 * Returns the action to be executed when the user logs in
	 * 
	 * @return The action to be executed when the user logs in
	 */
	public IAction getLoginAction() {
		
		return loginAction;
	}

	@Override
	public void start() {
		
		getLoginView().showModal();
	}

	@Override
	public void signIn() {
		
		// Attempt to log user in
		if (modelFacade.doUserLogin(getLoginView().getLoginUsername(), getLoginView().getLoginPassword())) {
			
			// If login succeeded
			getLoginView().closeModal();
			loginAction.execute();
		} else getLoginView().showDialog("Sign in failed! Please check your username/password and try again.");
	}

	@Override
	public void register() {
		if (!verifyRegister())
			return;
		
		// Passwords match?
		if (getLoginView().getRegisterPassword().equals(getLoginView().getRegisterPasswordRepeat())) {
			
			// Attempt to register new user (which, if successful, also logs them in)
			if (modelFacade.doUserRegister(getLoginView().getRegisterUsername(), getLoginView().getRegisterPassword())) {
				
				// If register succeeded
				getLoginView().closeModal();
				loginAction.execute();
			} else getLoginView().showDialog("Register Failed! Username may already be in use.");
		} else getLoginView().showDialog("Passwords don't match!");
	}
	
	// Check that the username/password are the right length and made up of legal characters.
	private boolean verifyRegister() {
		String userPattern = "^[0-9a-zA-Z_-]{3,7}$";
		String passwordPattern = "^[0-9a-zA-Z_-]{5,25}$";
				
		if (getLoginView().getRegisterUsername().matches(userPattern)) {
			if (getLoginView().getRegisterPassword().matches(passwordPattern))
				return true;
			else getLoginView().showDialog("Password must be between 5-25 characters containing " +
										   "only numbers, letters, underscores, and hyphens.");
		} else getLoginView().showDialog("Username must be between 3-7 characters containing " + 
				                         "only numbers, letters, underscores, and hyphens.");
		return false;
	}

}

