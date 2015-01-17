package model.cards;

/**
 * Created by brandt on 1/17/15.
 */
public class GameBank extends CardBank{


    public GameBank(){
        //initialize to 19 res cards
        //create all dev cards
    }

    public boolean canGiveDevCard(){
        //check if there are any
        return true;
    }

    public DevCard giveDevCard(){
        //random dev card
        return null;
    }

}
