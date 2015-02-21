package client.communication;

import client.base.Controller;
import facade.ClientFacade;


/**
 * Chat controller implementation
 */
public class ChatController extends Controller implements IChatController {

	private ClientFacade modelFacade;
	
	public ChatController(IChatView view, ClientFacade facade) {
		super(view);
		modelFacade = facade;
	}

	@Override
	public IChatView getView() {
		return (IChatView)super.getView();
	}

	@Override
	public void sendMessage(String message) {
		
	}

}

