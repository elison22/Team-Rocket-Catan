package model.trade;

import model.cards.ResourceSet;

public class DomesticTrade {

	private int sender;
	private int receiver;
	private ResourceSet offer;
	
	public DomesticTrade() {
		
	}
	
    public void setSender(int sender) {
        this.sender = sender;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public void setOffer(ResourceSet offer) {
        this.offer = offer;
    }

	public int getSender() {
		return sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public ResourceSet getOffer() {
		return offer;
	}

}