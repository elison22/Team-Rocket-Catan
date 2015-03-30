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
	private boolean waitingForOtherPlayers;
	private boolean inGame;

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
		this.inGame = false;
		setTimer();
	}
	
	private void setTimer() {
		Timer timer = new Timer(DELAY, new ActionListener() {
			
			public void actionPerformed(ActionEvent actionEvent) {
				pollServer();
				//System.out.println("Working!!!!");
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
			
			// If the client is currently in a game
			if (isInGame()) {
				String newModel;
				
				if (isWaitingForOtherPlayers())
					newModel = proxyFacade.model(-1);
				else
					newModel = proxyFacade.model(clientFacade.getVersionNumber());
				
				// If version number matches Server version number
				if (newModel.equals("\"true\"\n")) {
					
					// Do nothing
					return;
				}
				
				// Update with the new model
				clientFacade.updateGameModel(newModel);
			} else {
				String newGameList = proxyFacade.list();
				
				// Update with the new GameList
				clientFacade.updateGameList(newGameList);
			}
			
		} catch (ServerException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isInGame() {
		return inGame;
	}

	public void setInGame(boolean inGame) {
		this.inGame = inGame;
	}

	public boolean isWaitingForOtherPlayers() {
		return waitingForOtherPlayers;
	}

	public void setWaitingForOtherPlayers(boolean waitingForOtherPlayers) {
		this.waitingForOtherPlayers = waitingForOtherPlayers;
	}
}
