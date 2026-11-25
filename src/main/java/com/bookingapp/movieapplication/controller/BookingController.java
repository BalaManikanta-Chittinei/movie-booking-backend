package com.bookingapp.movieapplication.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bookingapp.movieapplication.model.Booking;
import com.bookingapp.movieapplication.service.BookingService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    
    @PostMapping("/book/{userId}/{movieId}")
    public ResponseEntity<Booking> bookTicket(@PathVariable Long userId,@PathVariable Long movieId,
                                              @Valid @RequestBody Booking booking){

        Booking savedBooking =bookingService.bookTicket(userId,movieId,booking);
        return ResponseEntity.status(201).body(savedBooking);
    }

    
    @GetMapping("/getBookings")
    public ResponseEntity<Page<Booking>> getAllBookings(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "5") int size){
        Page<Booking> bookings = bookingService.getAllBookings(page,size);
        return ResponseEntity.status(200).body(bookings);
    }


    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long bookingId){
        Booking booking =bookingService.getBookingById(bookingId);
        return ResponseEntity.ok(booking);
    }


    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Booking>> getBookingHistory(@PathVariable Long userId){
        List<Booking> bookings = bookingService.getBookingHistory(userId);
        return ResponseEntity.status(200).body(bookings);
    }

    
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId){
        Booking booking =bookingService.cancelBooking(bookingId);
        return ResponseEntity.status(200).body(booking);
    }
}