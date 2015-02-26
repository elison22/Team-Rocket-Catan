package client.main;

import javax.swing.*;

import client.catan.*;
import client.login.*;
import client.join.*;
import client.misc.*;
import client.base.*;
import facade.ClientFacade;
import model.board.BoardException;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame
{
	
	private CatanPanel catanPanel;
	
	public Catan(ClientFacade facade)
	{
		
		client.base.OverlayView.setWindow(this);
		
		this.setTitle("Settlers of Catan");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		catanPanel = new CatanPanel(facade);
		this.setContentPane(catanPanel);
		
		display();
	}
	
	private void display()
	{
		pack();
		setVisible(true);
	}
	
	//
	// Main
	//
	
	public static void main(final String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				//new Catan();
                ClientFacade modelFacade = null;
                try {
                    modelFacade = new ClientFacade("localhost", "8081");
                } catch (BoardException e) {
                    e.printStackTrace();
                }
                new Catan(modelFacade);
				
				PlayerWaitingView playerWaitingView = new PlayerWaitingView("PlayerWaiting");
				final PlayerWaitingController playerWaitingController = new PlayerWaitingController(
																									playerWaitingView,
																									modelFacade);
				playerWaitingView.setController(playerWaitingController);
				
				JoinGameView joinView = new JoinGameView("JoinGame");
				NewGameView newGameView = new NewGameView("NewGame");
				SelectColorView selectColorView = new SelectColorView("SelectColor");
				MessageView joinMessageView = new MessageView("Message");
				final JoinGameController joinController = new JoinGameController(
																				 joinView,
																				 newGameView,
																				 selectColorView,
																				 joinMessageView,
																				 modelFacade);
				joinController.setJoinAction(new IAction() {
					@Override
					public void execute()
					{
						playerWaitingController.start();
					}
				});
				joinView.setController(joinController);
				newGameView.setController(joinController);
				selectColorView.setController(joinController);
				joinMessageView.setController(joinController);
				
				LoginView loginView = new LoginView("Login");
				MessageView loginMessageView = new MessageView("Message");
				LoginController loginController = new LoginController(
																	  loginView,
																	  loginMessageView,
																	  modelFacade);
				loginController.setLoginAction(new IAction() {
					@Override
					public void execute()
					{
						joinController.start();
					}
				});
				loginView.setController(loginController);
				loginView.setController(loginController);
				
				loginController.start();
			}
		});
	}
	
}

