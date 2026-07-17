package app.user.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Object id) {
        super("User not found with id: " + id);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}