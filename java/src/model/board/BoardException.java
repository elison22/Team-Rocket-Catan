package model.board;

/**
 * Created by brandt on 1/19/15.
 */
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