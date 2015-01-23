package model.player;

/**
 * Created by brandt on 1/22/15.
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