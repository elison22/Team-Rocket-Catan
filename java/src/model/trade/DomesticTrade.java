package model.trade;

import java.util.HashMap;

import shared.definitions.ResourceType;
import model.cards.ResourceSet;

public class DomesticTrade {

	private int sender;
	private int receiver;
	//private ResourceSet offer;
	private HashMap<ResourceType,Integer> offer;
	
	/*public DomesticTrade(int sender, int receiver, ResourceSet offer) {
		this.sender = sender;
		this. receiver = receiver;
		this.offer = offer;
	}*/
	
	public DomesticTrade(int sender) {
		this.sender = sender;
		receiver = -1;
		offer = null;
	}
	
    /*public void setSender(int sender) {
        this.sender = sender;
    }*/

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public void setOffer(HashMap<ResourceType,Integer> offer) {
        this.offer = offer;
    }

	public int getSender() {
		return sender;
	}

	public int getReceiver() {
		return receiver;
	}

	public HashMap<ResourceType,Integer> getOffer() {
		return offer;
	}

}