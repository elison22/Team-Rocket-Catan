package model.cards;

import shared.definitions.ResourceType;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by brandt on 1/17/15.
 */
public class CardBank {

    protected TreeMap<ResourceType,Integer> rescards = new TreeMap<ResourceType, Integer>();
    protected ArrayList<DevCard> devcards = new ArrayList<DevCard>();

    public boolean giveCard(ResourceType card){
        //decrement the count for that resource
        return true;
    }

    public boolean takeCard(ResourceType card){
        //increment the count for that resource
        return true;
    }

    public boolean canTakeCard(ResourceType card){
        //check if there is a card to take
        return true;
    }

}
