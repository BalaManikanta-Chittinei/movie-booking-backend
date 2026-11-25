package com.bookingapp.movieapplication.exceptions;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(String message){
        super(message);
    }

}
