package error;

import utils.ErrorMessages;

public class InvalidSessionException extends Exception {
    public InvalidSessionException(ErrorMessages message) {
        super(message.getMessage());
    }
}