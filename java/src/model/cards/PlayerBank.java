package model.cards;

/**
 * Created by brandt on 1/17/15.
 */
public class PlayerBank extends CardBank{

    public PlayerBank(){
        //initialize everything to 0
    }

    public boolean takeDevCard(){
        //take from the bank
        //add to card list
        return true;
    }

    public boolean playDevCard(DevCard card){
        //maybe some inheritance or an interface?
        return true;
    }

}
