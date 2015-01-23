package model.player;

/**
 * Created by brandt on 1/22/15.
 * Used for all exceptions in the player package. Exception specifics are in the exception message.
 */
public class PlayerException extends Exception {

    public PlayerException() {
        return;
    }

    public PlayerException(String message) {
        super(message);
    }

    public PlayerException(Throwable cause) {
        super(cause);
    }

    public PlayerException(String message, Throwable cause) {
        super(message, cause);
    }

}