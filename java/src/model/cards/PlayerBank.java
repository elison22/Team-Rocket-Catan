package model.cards;

/**
 * @author Hayden
 * Extension of the CardBank class that holds all of the cards
 * each player has in their hand. Upon creation, the PlayerBank will
 * be empty.
 */
public class PlayerBank extends CardBank{

    /**
     * Creates a PlayerBank object and initializes the player's
     * held resources to zero
     */
    public PlayerBank(){
        //initialize everything to 0
    }

    /**
     * Used to take a development card from the GameBank and add it
     * to the player's list of development cards
     * @return True if the player successfully took a DevCard
     */
    public boolean takeDevCard(){
        //take from the bank
        //add to card list
        return true;
    }

    /**
     * Called by the Facade to see if the player can legally play
     * a development card.
     * @param card The type of development card to be played
     */
    public void canPlayDevCard(DevCard card){
        //maybe some inheritance or an interface?
//        card.effect();    only commented out to make it build
//        return true;      commented it out because it isn't in the documentation above and not sure if we need it
    }

}
