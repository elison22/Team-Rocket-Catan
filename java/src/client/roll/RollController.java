package client.roll;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import client.base.Controller;
import facade.ClientFacade;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, Observer {

	private IRollResultView resultView;
	private ClientFacade modelFacade;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView, ClientFacade facade) {
		super(view);
		modelFacade = facade;
		
		setResultView(resultView);
	}
	
	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}
	
	@Override
	public void rollDice() {
		getResultView().showModal();
		int min = 2;
		int max = 12;
		
		Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    
	    resultView.setRollValue(randomNum);
	    modelFacade.rollDice(randomNum);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(modelFacade.canRollDice())
			rollDice();
		
	}

}

