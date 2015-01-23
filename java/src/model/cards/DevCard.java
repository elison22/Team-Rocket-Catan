package model.cards;

import shared.definitions.DevCardType;

/**
 * @author Hayden
 * DevCard contains the type of development card the object represents as
 * well as the effect each card has when played
 */
public class DevCard {

    private DevCardType type;
    private boolean hasBeenPlayed;
    /**
     * Creates a DevCard object
     * @param type The type of development card to create
     */
    public DevCard(DevCardType type){
        this.type = type;
        this.hasBeenPlayed = false;
    }

    /**
     * Called by the constructor to set the effect of the development card
     * @param type The type of the dev card
     */
    public void setEffect(DevCardType type){
        //a switch statement probably?
        return;
    }

    /**
     * Similates the effect of the card
     */
    public void effect(){
        return;
    }
}
