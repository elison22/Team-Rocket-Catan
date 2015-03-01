package model.trade;

import java.util.HashMap;

import shared.definitions.ResourceType;

public class DomesticTrade {

	private int sender;
	private int receiver;
	private HashMap<ResourceType,Integer> offer;
	
	public DomesticTrade(int sender) {
		this.sender = sender;
		receiver = -1;
		offer = null;
	}

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