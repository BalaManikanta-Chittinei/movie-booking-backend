package com.bookingapp.movieapplication.exceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bookingapp.movieapplication.exceptions.BookingNotFoundException;
import com.bookingapp.movieapplication.exceptions.CinemaNotFoundException;
import com.bookingapp.movieapplication.exceptions.MovieNotFoundException;
import com.bookingapp.movieapplication.exceptions.ScreenNotFoundException;
import com.bookingapp.movieapplication.exceptions.UserNotFoundException;
import com.bookingapp.movieapplication.exceptions.UsernameNotFoundException;

@RestControllerAdvice 
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFondException(UserNotFoundException userEX){
        ErrorResponse response = new ErrorResponse(
                                    LocalDateTime.now().toString(),
                                    HttpStatus.NOT_FOUND.value(),
                                    "Not Found",
                                    userEX.getMessage());
    
      return  ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler (MovieNotFoundException.class)
    public ResponseEntity<ErrorResponse> HandleMovieNOtFoundException(MovieNotFoundException movieEx){
        ErrorResponse response= new ErrorResponse(
                                                LocalDateTime.now().toString(),
                                                HttpStatus.NOT_FOUND.value(),
                                                "Not Found",
                                                movieEx.getMessage());

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler (ScreenNotFoundException.class)
    public ResponseEntity<ErrorResponse> HandleScreenNOtFoundException(ScreenNotFoundException screenEx){
        ErrorResponse response= new ErrorResponse(
                                                LocalDateTime.now().toString(),
                                                HttpStatus.NOT_FOUND.value(),
                                                "Not Found",
                                                screenEx.getMessage());

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler (CinemaNotFoundException.class)
    public ResponseEntity<ErrorResponse> HandleCinemaNOtFoundException(CinemaNotFoundException cinemaEx){
        ErrorResponse response= new ErrorResponse(
                                                LocalDateTime.now().toString(),
                                                HttpStatus.NOT_FOUND.value(),
                                                "Not Found",
                                                cinemaEx.getMessage());

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler (BookingNotFoundException.class)
    public ResponseEntity<ErrorResponse> HandleBookingNOtFoundException(BookingNotFoundException bookingEx){
        ErrorResponse response= new ErrorResponse(
                                                LocalDateTime.now().toString(),
                                                HttpStatus.NOT_FOUND.value(),
                                                "Not Found",
                                                bookingEx.getMessage());

        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String , String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                             .forEach(error->
                                errors.put(error.getField(),error.getDefaultMessage()));
        
        return ResponseEntity.status(400).body(errors);                     
    }

     @ExceptionHandler (UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> HandleUsernameNOtFoundException(UsernameNotFoundException usernameEx){
        ErrorResponse response= new ErrorResponse(
                                                LocalDateTime.now().toString(),
                                                HttpStatus.NOT_FOUND.value(),
                                                "Not Found",
                                                usernameEx.getMessage());

        return ResponseEntity.status(400).body(response);
    }
}

