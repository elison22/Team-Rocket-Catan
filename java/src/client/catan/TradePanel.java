package client.catan;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import client.domestic.AcceptTradeOverlay;
import client.domestic.DomesticTradeController;
import client.domestic.DomesticTradeOverlay;
import client.domestic.DomesticTradeView;
import client.maritime.MaritimeTradeController;
import client.maritime.MaritimeTradeOverlay;
import client.maritime.MaritimeTradeView;
import client.misc.WaitView;
import facade.ClientFacade;

@SuppressWarnings("serial")
public class TradePanel extends JPanel
{
	
	private DomesticTradeView domesticView;
	private DomesticTradeOverlay domesticOverlay;
	private WaitView domesticWaitView;
	private AcceptTradeOverlay domesticAcceptOverlay;
	private DomesticTradeController domesticController;
	
	private MaritimeTradeView maritimeView;
	private MaritimeTradeOverlay maritimeOverlay;
	private MaritimeTradeController maritimeController;
	
	public TradePanel(ClientFacade facade)
	{
		
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		domesticView = new DomesticTradeView("DomesticTrade");
		domesticOverlay = new DomesticTradeOverlay("DomesticTrade");
		domesticWaitView = new WaitView("DomesticWait");
		domesticWaitView.setMessage("Waiting for Trade to Go Through");
		domesticAcceptOverlay = new AcceptTradeOverlay("DomesticAccept");
		domesticController = new DomesticTradeController(domesticView,
														 domesticOverlay,
														 domesticWaitView,
														 domesticAcceptOverlay, facade);
		domesticView.setController(domesticController);
		domesticOverlay.setController(domesticController);
		domesticWaitView.setController(domesticController);
		domesticAcceptOverlay.setController(domesticController);
		
		maritimeView = new MaritimeTradeView("MaritimeView");
		maritimeOverlay = new MaritimeTradeOverlay("MaritimeOverlay");
		maritimeController = new MaritimeTradeController(maritimeView,
														 maritimeOverlay, facade);
		maritimeView.setController(maritimeController);
		maritimeOverlay.setController(maritimeController);
		
		this.setOpaque(true);
		this.setBackground(Color.white);
		
		this.add(Box.createHorizontalGlue());
		this.add(domesticView);
		this.add(Box.createRigidArea(new Dimension(3, 0)));
		this.add(maritimeView);
		this.add(Box.createRigidArea(new Dimension(3, 0)));
	}
	
}

