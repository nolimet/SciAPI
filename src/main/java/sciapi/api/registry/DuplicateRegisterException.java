package sciapi.api.registry;

public class DuplicateRegisterException extends RuntimeException {
    public DuplicateRegisterException() {
    }

    public DuplicateRegisterException(String message, Throwable cause) {
            super(message, cause);
    }

    public DuplicateRegisterException(String message) {
            super(message);
    }

    public DuplicateRegisterException(Throwable cause) {
            super(cause);
    }
}
