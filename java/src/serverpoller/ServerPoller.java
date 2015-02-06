package serverpoller;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import proxy.*;
import javax.swing.Timer;
import facade.ClientFacade;

/**
 * @author Chad
 * Feb 5, 2015
 */
public class ServerPoller {
	
	// Milliseconds between each event firing
	private final int DELAY;
	
	private IProxyFacade proxyFacade;
	private ClientFacade clientFacade;

	// For Testing
	public ServerPoller(int delay) {
		this.DELAY = delay;
		setTimer();
	}
	
	/**Primary Constructor. It will use the specified proxyFacade when its timer
	 * is triggered and will send the results to the clientFacade. 
	 * 
	 * @param delay The time in milliseconds between each event firing.
	 * @param proxyFacade The proxyFacade to poll the server with.
	 * @param clientFacade The clientFacade that will update the game model if 
	 * needed.
	 */
	public ServerPoller(int delay, IProxyFacade proxyFacade, ClientFacade clientFacade) {
		this.DELAY = delay;
		this.proxyFacade = proxyFacade;
		this.clientFacade = clientFacade;
		setTimer();
	}
	
	private void setTimer() {
		Timer timer = new Timer(DELAY, new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				pollServer();
			}
			
		});
		
		timer.start();
	}
	
	
	/**Retrieve the most current game model from the server unless the client
	 * model is already current. When this happens the server will simply 
	 * return the string "true".
	 * 
	 */
	public void pollServer() {
		try {
			String newModel = proxyFacade.model(clientFacade.getVersionNumber());
			
			// If version number matches Server version number
			if (newModel.equals("true")) {
				// Do nothing
				return;
			}
			
			// Update with the new model
			clientFacade.updateGameModel(newModel);
			
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
}
