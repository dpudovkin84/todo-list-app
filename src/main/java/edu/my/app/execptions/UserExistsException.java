package edu.my.app.execptions;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String message) {
        super(message);
    }
}
