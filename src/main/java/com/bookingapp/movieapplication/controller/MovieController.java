package com.bookingapp.movieapplication.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookingapp.movieapplication.model.Movie;
import com.bookingapp.movieapplication.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/createMovie/{screenId}")
    public ResponseEntity<Movie> addMovie(@PathVariable Long screenId,@Valid @RequestBody Movie movie){
        Movie savedMovie =movieService.addMovie(screenId, movie);
        return ResponseEntity.status(201).body(savedMovie);
    }


    @GetMapping("/getMovies")
    public ResponseEntity<Page<Movie>> getAllMovies(@RequestParam(defaultValue = "0")int page,
                                                    @RequestParam(defaultValue = "5")int size){
        Page<Movie> movies = movieService.getAllMovies(page,size);
        return ResponseEntity.status(200).body(movies);
    }

    @GetMapping("/movieBy/{title}")
    public ResponseEntity<List<Movie>> getMovieDetails(@PathVariable String title){
        List<Movie> movieTitle = movieService.getMovieTitle(title) ;
        return ResponseEntity.status(200).body(movieTitle);
    }

    @PutMapping("/update/{movieId}")
     public ResponseEntity<Movie> updateMovieDetails(@PathVariable Long movieId,@RequestBody Movie movie){
        Movie updateMovies = movieService.updateMovieDetails(movieId,movie);
        if(updateMovies!=null){
            return ResponseEntity.status(200).body(updateMovies);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/remove/{movieId}")
     public ResponseEntity<String> deleteMovieDetails(@PathVariable Long movieId){
        movieService.deleteMovie(movieId);
        return ResponseEntity.status(200).body("Movie deleted successfully with Id:"+ movieId);
   
    }


    
    

}
