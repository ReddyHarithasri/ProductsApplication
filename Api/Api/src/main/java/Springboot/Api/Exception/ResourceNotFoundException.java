package Springboot.Api.Exception;

public class ResourceNotFoundException extends  Exception {
    public ResourceNotFoundException(final String message) {

        super(message);
    }
}
