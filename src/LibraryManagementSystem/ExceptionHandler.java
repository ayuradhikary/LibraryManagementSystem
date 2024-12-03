package LibraryManagementSystem;

public class ExceptionHandler extends Exception {

    public ExceptionHandler(String message) {
        super(message);
    }

    public static ExceptionHandler UnauthorizedAccessException(String message) {
        return new ExceptionHandler("Unauthorized Access: " + message);
    }

    public static ExceptionHandler resourceNotFoundException(String message) {
        return new ExceptionHandler("Resource Not Found: " + message);
    }

    public static ExceptionHandler invalidInputException(String message) {
        return new ExceptionHandler("Invalid Input: " + message);
    }

    public static ExceptionHandler invalidCredentialsException(String message) {
        return new ExceptionHandler("Invalid Credentials: " + message);
    }

    public static ExceptionHandler fileWriteException(String message) {
        return new ExceptionHandler("Invalid Input: " + message);
    }
}
 