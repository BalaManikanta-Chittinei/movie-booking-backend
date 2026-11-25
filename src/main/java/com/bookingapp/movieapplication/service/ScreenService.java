package com.bookingapp.movieapplication.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookingapp.movieapplication.exceptions.CinemaNotFoundException;
import com.bookingapp.movieapplication.model.Cinema;
import com.bookingapp.movieapplication.model.Screen;
import com.bookingapp.movieapplication.reporsitory.CinemaRepository;
import com.bookingapp.movieapplication.reporsitory.ScreenRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class ScreenService {

    @Autowired
    private ScreenRepository screenRepository;
    @Autowired 
    private CinemaRepository cinemaRepository;

    @Transactional
public Screen addScreen(Long cinemaId, Screen screen){

    log.info("Received request for creating screen for cinema id : {}",cinemaId);

    Cinema cinema = cinemaRepository.findById(cinemaId)
            .orElseThrow(() -> {
                log.warn("Cinema not found with id : {}",cinemaId);
                return new CinemaNotFoundException("Cinema not found with id : " + cinemaId);});
    screen.setCinema(cinema);
    Screen savedScreen = screenRepository.save(screen);

    log.info("Screen created successfully with id : {}",savedScreen.getScreenId());

    return savedScreen;
}
    
    @Transactional(readOnly = true)
    public Page<Screen> getAllScreens(int page,int size){
        log.info("Fetching screen with page: {} and size:{}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        return screenRepository.findAll( pageable);
        
    }
    
    @Transactional(readOnly = true)
    public List<Screen> getByScreenType(String screenType){
        log.info("Received Request for Fetching screentype{}",screenType);
        List<Screen> screens=screenRepository.findByScreenType(screenType);
        log.info("Successfully fetched {} screen records for screenType:",screens.size(),screenType);
        return screens;
    }
    

}
