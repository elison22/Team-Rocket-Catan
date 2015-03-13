package model.strade;

import shared.definitions.PortType;
import shared.definitions.ResourceType;

/**
 * This class is meant to only hold 1 card to trade at a time 
 * (i.e. if you want to trade 4 cards to receive 2 on 2:1 port, 2 of these objects would need to be made)
 */
public class ServerMaritimeTrade {

    private int ratio;	// (ie. put 3 for a 3:1 trade)
    private PortType portType;
    private ResourceType resourceToReceive;
    private ResourceType resourceToGive;

    public ServerMaritimeTrade(ResourceType give, ResourceType get, int ratio) {
        resourceToGive = give;
        resourceToReceive = get;
        this.ratio = ratio;

        determinePortType();
    }

    private void determinePortType() {
        if (ratio == 2)
            portType = PortType.convertResourceType(resourceToGive);
        if (ratio == 3)
            portType = PortType.THREE_FOR_ONE;
        if (ratio == 4)
            portType = null;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public void setTradeType(PortType resourceType) {
        this.portType = resourceType;
    }

    public int getRatio() {
        return ratio;
    }

    public PortType getPortType() {
        return portType;
    }

    public ResourceType getResourceToGive() {
        return resourceToGive;
    }

    public ResourceType getResourceToReceive() {
        return resourceToReceive;
    }

}