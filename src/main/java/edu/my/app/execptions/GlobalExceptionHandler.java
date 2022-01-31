package edu.my.app.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectData> UserExists(
            UserExistsException exception){
        IncorrectData data=new IncorrectData();
        data.setErrorInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> UserNotFound(UserNotFoundException
                                                      exception){
        IncorrectData data= new IncorrectData();
        data.setErrorInfo(exception.getMessage());
        return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> TodoNotFound(TodoNotFoundException
                                                              exception){
        IncorrectData data= new IncorrectData();
        data.setErrorInfo(exception.getMessage());
        return new ResponseEntity<>(data,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> UnknownError(
            Exception exception){
        IncorrectData data=new IncorrectData();
        data.setErrorInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_GATEWAY);
    }



}
