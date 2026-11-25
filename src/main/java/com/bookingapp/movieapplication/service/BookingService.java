package com.bookingapp.movieapplication.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookingapp.movieapplication.exceptions.BookingNotFoundException;
import com.bookingapp.movieapplication.exceptions.MovieNotFoundException;
import com.bookingapp.movieapplication.exceptions.UserNotFoundException;
import com.bookingapp.movieapplication.model.Booking;
import com.bookingapp.movieapplication.model.BookingStatus;
import com.bookingapp.movieapplication.model.Movie;
import com.bookingapp.movieapplication.model.User;
import com.bookingapp.movieapplication.reporsitory.BookingRepository;
import com.bookingapp.movieapplication.reporsitory.MovieRepository;
import com.bookingapp.movieapplication.reporsitory.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public Booking bookTicket(Long userId,Long movieId,Booking booking){

        log.info("Received booking request for userId : {} and movieId : {}",userId,movieId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                log.warn("User not found with id : {}",userId);
                return new UserNotFoundException("User not found with id : " + userId);});

        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> {
                log.warn("Movie not found with id : {}",movieId);
                return new MovieNotFoundException("Movie not found with id : " + movieId);});

        booking.setUser(user);
        booking.setMovie(movie);
        booking.setBookingStatus(BookingStatus.BOOKED);

        Booking savedBooking =bookingRepository.save(booking);
        log.info("Booking created successfully with id : {}",savedBooking.getBookingId());
        return savedBooking;
    }

    @Transactional(readOnly = true)
    public Page<Booking> getAllBookings(int page,int size){

        log.info("Fetching all bookings with page:{} and size:{}",page,size);
        Pageable pageable = PageRequest.of(page, size);
        return bookingRepository.findAll(pageable);
        
    }

    @Transactional(readOnly = true)
    public Booking getBookingById(Long bookingId){

        log.info("Fetching booking with id : {}",bookingId);

        Booking booking =bookingRepository.findById(bookingId)
                        .orElseThrow(() -> {
                        log.warn("Booking not found with id : {}",bookingId);
                        return new BookingNotFoundException("Booking not found with id : "+ bookingId);});

        log.info("Booking fetched successfully : {}",bookingId);
        return booking;
    }

   @Transactional(readOnly = true)
    public List<Booking> getBookingHistory(Long userId){
        log.info("Fetching booking history for user id : {}",userId);
        return bookingRepository.findByUserUserId(userId);
    }


    @Transactional
    public Booking cancelBooking(Long bookingId){

        log.info("Received request to cancel booking : {}",bookingId);

        Booking booking =bookingRepository.findById(bookingId)
                        .orElseThrow(() -> {
                        log.warn("Booking not found with id : {}",bookingId);
                        return new BookingNotFoundException("Booking not found with id : "+ bookingId);});

        booking.setBookingStatus(BookingStatus.CANCELLED);
        Booking cancelledBooking =bookingRepository.save(booking);
        log.info("Booking cancelled successfully : {}",bookingId);
        return cancelledBooking;
    }
}