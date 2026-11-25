package com.bookingapp.movieapplication.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookingapp.movieapplication.exceptions.MovieNotFoundException;
import com.bookingapp.movieapplication.exceptions.ScreenNotFoundException;
import com.bookingapp.movieapplication.model.Movie;
import com.bookingapp.movieapplication.model.Screen;
import com.bookingapp.movieapplication.reporsitory.MovieRepository;
import com.bookingapp.movieapplication.reporsitory.ScreenRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j 
@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired 
    private  ScreenRepository screenRepository;

@Transactional
    public Movie addMovie(Long screenId, Movie movie){

        log.info("Received request for creating movie for screen id : {}",screenId);

        Screen screen = screenRepository.findById(screenId)
                .orElseThrow(() -> {
                    log.warn("Screen not found with id : {}",screenId);
                    return new ScreenNotFoundException("Screen not found with id : " + screenId);});

        screen.setMovie(movie);
        movie.setScreen(screen);

        Screen savedScreen = screenRepository.save(screen);
        log.info("Movie created successfully for screen id : {}",screenId);

        return savedScreen.getMovie();
    }


    @Transactional(readOnly = true)
   public Page<Movie> getAllMovies(int page,int size){
    log.info(" fetching movie with page:{} and size:{}",page,size);
    Pageable pageable = PageRequest.of(page, size);
    return movieRepository.findAll(pageable);
    
}

@Transactional(readOnly = true)
public List<Movie> getMovieTitle(String title){

    log.info("Fetching movie with title : {}", title);
    List<Movie> movies = movieRepository.findByTitle(title);
    log.info("Successfully fetched {} movie record(s) for title : {}",movies.size(),title);
    return movies;
}
    @Transactional 
    public Movie updateMovieDetails(Long movieId ,Movie movie){
        log.info("Received request for updating movies with Id:{}",movieId);
        
            Movie movies = movieRepository.findById(movieId).orElseThrow(()-> {
                                log.warn("movie not Found with the Id:{}",movieId);
                                return  new MovieNotFoundException
                                ("Movie Not Found with ID: "+ movieId);});
            movies.setTitle(movie.getTitle());
            movies.setReleaseDate(movie.getReleaseDate());
            movies.setShowCycle(movie.getShowCycle());
            log.info("Movie Details updated Successfuly with id:{}",movieId);
            return movieRepository.save(movies);

    }

    @Transactional 
    public void deleteMovie(Long movieId){
        log.info("Received Request for deleting the movie With id:{}",movieId);
        Movie movie = movieRepository.findById(movieId).orElseThrow(()->{
                                      log.warn("Movie Not found with the Id:{}",movieId);
                                      return new MovieNotFoundException
                                      ("Movie Not Found with ID: "+ movieId);});
                log.info("Movie Deleted Successfully with the Id:{}",movieId);
                movieRepository.delete(movie);                                                   
        
    }

}
