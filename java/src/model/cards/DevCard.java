package model.cards;

import shared.definitions.DevCardType;

/**
 * Created by brandt on 1/17/15.
 */
public class DevCard {

    private DevCardType type;
    /**
     * Creates a DevCard object
     * @param type The type of development card to create
     */
    public DevCard(DevCardType type){
        this.type = type;
    }

    public void setEffect(DevCardType type){
        //a switch statement probably?
    }
}
