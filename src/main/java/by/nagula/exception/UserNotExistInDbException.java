package by.nagula.exception;

public class UserNotExistInDbException extends RuntimeException {

    public UserNotExistInDbException() {
    }

    public UserNotExistInDbException(String message) {
        super(message);
    }

    public UserNotExistInDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotExistInDbException(Throwable cause) {
        super(cause);
    }

    public UserNotExistInDbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
