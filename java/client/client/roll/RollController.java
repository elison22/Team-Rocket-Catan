package client.roll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.Timer;

import client.base.Controller;
import client.base.OverlayView;
import facade.ClientFacade;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController, Observer {

	private IRollResultView resultView;
	private ClientFacade modelFacade;
    private Timer timer;

	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView, ClientFacade facade) {
		super(view);
		modelFacade = facade;
		facade.addObserver(this);
		
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
		timer.stop();
        timer = null;
		int min = 1;
		int max = 6;
		
		Random rand = new Random();
	    int randNum1 = rand.nextInt((max - min) + 1) + min;
	    rand = new Random();
	    int randNum2 = rand.nextInt((max - min) + 1) + min;
	    int randomDiceRoll = randNum1 + randNum2;

	    getResultView().setRollValue(randomDiceRoll);
	    getResultView().showModal();
	    modelFacade.rollDice(randomDiceRoll);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null) return;
		
		// Do nothing if the view is already showing
		if (getRollView().isModalShowing())
			return;
		
		if(modelFacade.getTurnTacker() != null && modelFacade.canRollDice()) {
            OverlayView.killView("wait");
			getRollView().setMessage("Roll the dice!");
			getRollView().showModal();
            timedRoll();
		}
	}
    
    public void timedRoll() {
		timer = new Timer(3000, new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				getRollView().closeModal();
				rollDice();
			}
		});
		timer.setRepeats(false);
		timer.start();
	}

}

