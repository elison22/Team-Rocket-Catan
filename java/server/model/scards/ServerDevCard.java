package model.scards;

import shared.definitions.DevCardType;

/**
 * @author Hayden
 * DevCard contains the type of development card the object represents as
 * well as the effect each card has when played
 */
public class ServerDevCard {

    private DevCardType type;
    private boolean hasBeenPlayed;
    /**
     * Creates a DevCard object
     * @param type The type of development card to create
     */
    public ServerDevCard(DevCardType type) {
        this.type = type;
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
     * Simulates the effect of the card
     */
    public void effect(){
        return;
    }

    public boolean hasBeenPlayed() {
        return hasBeenPlayed;
    }

    public void playDevCard() {
        hasBeenPlayed = true;
    }

    public DevCardType getType() {
        return type;
    }
}
