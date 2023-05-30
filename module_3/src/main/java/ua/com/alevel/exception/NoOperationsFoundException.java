package ua.com.alevel.exception;

public class NoOperationsFoundException extends RuntimeException {
    public NoOperationsFoundException(String message) {
        super(message);
    }
}
