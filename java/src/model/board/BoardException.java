package model.board;

/**
 * Created by brandt on 1/19/15.
 * Used for all exceptions in the board package. Exception specifics are in the exception message.
 */
@SuppressWarnings("serial")
public class BoardException extends Exception {

    public BoardException() {
        return;
    }

    public BoardException(String message) {
        super(message);
    }

    public BoardException(Throwable cause) {
        super(cause);
    }

    public BoardException(String message, Throwable cause) {
        super(message, cause);
    }

}