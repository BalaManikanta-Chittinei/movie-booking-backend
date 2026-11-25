package com.bookingapp.movieapplication.exceptions;


public class CinemaNotFoundException extends  RuntimeException{
    public CinemaNotFoundException(String message){
        super(message);
    }

}
