package model.trade;

import java.util.HashMap;
import java.util.Map;

import model.cards.ResourceSet;
import shared.definitions.PortType;
import shared.definitions.ResourceType;

/**
 * This class is meant to only hold 1 card to trade at a time 
 * (i.e. if you want to trade 4 cards to receive 2 on 2:1 port, 2 of these objects would need to be made)
 */
public class MaritimeTrade {
	
	private int sender;
	private int ratio;	// (ie. put 3 for a 3:1 trade)
	private PortType portType;
	private ResourceSet resources;
	private ResourceType resType;
	
	public MaritimeTrade(int sender, int ratio, PortType portType, ResourceSet resources) {
		this.sender = sender;
		this.ratio = ratio;
		this.portType = portType;
		this.resources = resources;
		
		HashMap<ResourceType, Integer> res = resources.getResources();
		for(Map.Entry<ResourceType, Integer> entry : res.entrySet()){
    		if(entry.getValue() == -1)
    			resType = entry.getKey();
    	}
	}

    public void setSender(int sender) {
        this.sender = sender;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }
    
    public void setTradeType(PortType resourceType) {
    	this.portType = resourceType;
    }
    
    public int getSender() {
		return sender;
	}

	public int getRatio() {
		return ratio;
	}
	
	public PortType getPortType() {
		return portType;
	}
	
	public ResourceSet getResources() {
		return resources;
	}
	
	public ResourceType getResourceType() {
		return resType;
	}

}