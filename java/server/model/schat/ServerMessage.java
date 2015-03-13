package model.schat;


/**
 * @author Hayden
 * A message written by a player to be sent through the chat client. Each Message will
 * have an owner, defined by the player's index, and the words of their message.
 */
public class ServerMessage {

    /**The playerIdx of the user who sent the message*/
    private String owner;
    /**The words of the message*/
    private String message;

    /**
     * Creates a message object
     * @param owner The integer index value (0-3) of the player who sent the message
     * @param message The words of the message
     */
    public ServerMessage(String owner, String message){
        this.owner = owner;
        this.message = message;
    }

    public String getOwner()
    {
        return owner;
    }

    public String getMessage()
    {
        return message;
    }
}
