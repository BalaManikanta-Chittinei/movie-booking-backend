package com.bookingapp.movieapplication.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookingapp.movieapplication.exceptions.UserNotFoundException;
import com.bookingapp.movieapplication.model.Cinema;
import com.bookingapp.movieapplication.model.User;
import com.bookingapp.movieapplication.reporsitory.CinemaRepository;
import com.bookingapp.movieapplication.reporsitory.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class CinemaService {

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired 
    private UserRepository userRepository;

    @Transactional
    public Cinema addCinema(Long userId, Cinema cinema){

        log.info("Received request for creating cinema for user id : {}",userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.warn("User not found with id : {}",userId);
                    return new UserNotFoundException("User not found with id : " + userId);});

        cinema.setUser(user);

        Cinema savedCinema =cinemaRepository.save(cinema);
        log.info("Cinema created successfully with id : {}",savedCinema.getCinemaId());
        return savedCinema;
}

    @Transactional(readOnly = true)
    public Page<Cinema> getAllCinemas(int page,int size){
        log.info("Fetching cinemas with page:{} and size: {}",page,size);
        Pageable pageable = PageRequest.of(page,size);
        return cinemaRepository.findAll(pageable);
        
    }

    @Transactional(readOnly = true)
    public List<Cinema> getCinemasByLocation(String location){
        log.info("Received Request for Fetching Cinema location :{}",location);
        List<Cinema> cinemas=cinemaRepository.findByLocation(location);
        log.info("Successfully fetched {} cinema record(s) for location : {}",cinemas.size(),location);
        return cinemas;
    }



}
