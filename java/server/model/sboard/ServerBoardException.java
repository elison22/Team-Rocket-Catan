package model.sboard;

/**
 * Created by Hayden on 3/12/15.
 */
@SuppressWarnings("serial")
public class ServerBoardException extends Exception{

    public ServerBoardException() {
        return;
    }

    public ServerBoardException(String message) {
        super(message);
    }

    public ServerBoardException(Throwable cause) {
        super(cause);
    }

    public ServerBoardException(String message, Throwable cause) {
        super(message, cause);
    }

}
