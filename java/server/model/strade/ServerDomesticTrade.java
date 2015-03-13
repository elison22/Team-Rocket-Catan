package model.strade;

import java.util.HashMap;

import shared.definitions.ResourceType;

public class ServerDomesticTrade {

    private int sender;
    private int receiver;
    private HashMap<ResourceType,Integer> offer;

    public ServerDomesticTrade(int sender) {
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
    
    /**Performs the specified domestic trade.
     * 
     * @return True if successful, false if otherwise.
     */
    public boolean doDomesticTrade() {
    	return false;
    }
}