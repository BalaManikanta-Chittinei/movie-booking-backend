package com.bookingapp.movieapplication.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public  UsernameNotFoundException(String message){
        super(message);
    }

}
