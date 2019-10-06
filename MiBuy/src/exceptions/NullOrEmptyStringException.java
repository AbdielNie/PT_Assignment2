package exceptions;

public class NullOrEmptyStringException extends Exception {
    public NullOrEmptyStringException(String msg) {
        super(msg);
    }
}
