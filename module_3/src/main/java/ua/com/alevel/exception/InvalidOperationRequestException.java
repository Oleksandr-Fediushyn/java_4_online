package ua.com.alevel.exception;

public class InvalidOperationRequestException extends RuntimeException {
    public InvalidOperationRequestException(String message) {
        super(message);
    }
}
