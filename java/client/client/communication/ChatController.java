package client.communication;

import java.util.Observable;
import java.util.Observer;

import client.base.Controller;
import facade.ClientFacade;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController, Observer {

	private ClientFacade modelFacade;
	
	public ChatController(IChatView view, ClientFacade facade) {
		super(view);
		modelFacade = facade;
		modelFacade.addObserver(this);
	}
	
	private void setChatEntries() {
		getView().setEntries(modelFacade.getChatMessages());
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		modelFacade.doSendChat(message);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) return;
		
		setChatEntries();
	}

}

