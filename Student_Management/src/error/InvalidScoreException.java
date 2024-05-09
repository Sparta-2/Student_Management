package error;

import utils.ErrorMessages;

public class InvalidScoreException extends Exception {
    public InvalidScoreException(ErrorMessages message) {
        super(message.getMessage());
    }
}
