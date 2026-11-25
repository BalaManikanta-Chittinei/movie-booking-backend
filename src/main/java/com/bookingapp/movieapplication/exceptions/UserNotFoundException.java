package com.bookingapp.movieapplication.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message){
        super(message);

    }

}
