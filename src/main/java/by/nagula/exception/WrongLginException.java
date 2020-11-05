package by.nagula.exception;

public class WrongLginException extends RuntimeException {
    public WrongLginException() {
    }

    public WrongLginException(String message) {
        super(message);
    }

    public WrongLginException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongLginException(Throwable cause) {
        super(cause);
    }

    public WrongLginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
