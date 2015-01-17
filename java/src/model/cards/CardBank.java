package model.cards;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by brandt on 1/17/15.
 */
public class CardBank {

    protected TreeMap<ResCard,Integer> rescards = new TreeMap<ResCard, Integer>();
    protected ArrayList<DevCard> devcards = new ArrayList<DevCard>();

    public boolean giveCard(ResCard card){
        //decrement the count for that resource
        return true;
    }

    public boolean takeCard(ResCard card){
        //increment the count for that resource
        return true;
    }

    public boolean canTakeCard(ResCard card){
        //check if there is a card to take
        return true;
    }

}
