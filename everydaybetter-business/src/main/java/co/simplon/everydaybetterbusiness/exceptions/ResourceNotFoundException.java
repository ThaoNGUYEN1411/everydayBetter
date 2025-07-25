package co.simplon.everydaybetterbusiness.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
