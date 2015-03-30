package client.communication;

import java.util.Observable;
import java.util.Observer;
import client.base.Controller;
import facade.ClientFacade;


/**
 * Game history controller implementation
 */
public class GameHistoryController extends Controller implements IGameHistoryController, Observer {

	private ClientFacade modelFacade;
	
	public GameHistoryController(IGameHistoryView view, ClientFacade facade) {
		super(view);
		modelFacade = facade;
		facade.addObserver(this);
		initFromModel();
	}
	
	@Override
	public IGameHistoryView getView() {
		
		return (IGameHistoryView)super.getView();
	}
	
	private void initFromModel() {
	}
	
	private void setHistoryEntries() {
		
		getView().setEntries(modelFacade.getHistoryMessages());
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) return;
		
		setHistoryEntries();
	}

	
	
}

