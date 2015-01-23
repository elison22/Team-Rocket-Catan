package model.cards;

/**
 * @author Hayden
 * DevCard contains the type of development card the object represents as
 * well as the effect each card has when played
 */
public class DevCard {

    private String type;
    private boolean hasBeenPlayed;
    /**
     * Creates a DevCard object
     * @param type The type of development card to create
     */
    public DevCard(String type){
        this.type = type;
        this.hasBeenPlayed = false;
    }

    /**
     * Called by the constructor to set the effect of the development card
     * @param type The type of the dev card
     */
    public void setEffect(String type){
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
