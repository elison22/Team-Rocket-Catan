package model.trade;

import shared.definitions.ResourceType;

/**
 * This class is meant to only hold 1 card to trade at a time 
 * (i.e. if you want to trade 4 cards to receive 2 on 2:1 port, 2 of these objects would need to be made)
 */
public class MaritimeTrade {
	
	private int sender;
	private int ratio;	// (ie. put 3 for a 3:1 trade)
	private ResourceType resourceType;
	
	public MaritimeTrade(int sender, int ratio, ResourceType resType) {
		this.sender = sender;
		this.ratio = ratio;
		this.resourceType = resType;
	}

    public void setSender(int sender) {
        this.sender = sender;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
    
    public void setTradeType(ResourceType resourceType) {
    	this.resourceType = resourceType;
    }
    
    public int getSender() {
		return sender;
	}

	public int getRatio() {
		return ratio;
	}
	
	public ResourceType getResourceType() {
		return resourceType;
	}

}