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
		}
	}

	@Override
	public void register() {
		
		// Passwords match?
		if (getLoginView().getRegisterPassword().equals(getLoginView().getRegisterPasswordRepeat())) {
			
			// Attempt to register new user (which, if successful, also logs them in)
			if (modelFacade.doUserRegister(getLoginView().getRegisterUsername(), getLoginView().getRegisterPassword())) {
				
				// If register succeeded
				getLoginView().closeModal();
				loginAction.execute();
			}
		}
	}

}

