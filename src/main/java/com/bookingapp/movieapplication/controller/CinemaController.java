package com.bookingapp.movieapplication.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookingapp.movieapplication.model.Cinema;
import com.bookingapp.movieapplication.service.CinemaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Autowired 
    private CinemaService cinemaService;
    
    @PostMapping("/createCinema/{userId}")

    public ResponseEntity<Cinema> addingUser(@PathVariable Long userId,@Valid @RequestBody Cinema cinema){
        Cinema cinemas = cinemaService.addCinema(userId,cinema);
        return ResponseEntity.status(201).body(cinemas);
    }

    @GetMapping("/getCinemas")
    public ResponseEntity<Page<Cinema>> getCinema(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam (defaultValue = "5") int size){
        Page<Cinema> AllCinema= cinemaService.getAllCinemas(page,size);
        return ResponseEntity.status(200).body(AllCinema);
    }

    @GetMapping("/getCinema/{location}")
    public ResponseEntity<List<Cinema>> getScreenByType(@PathVariable String location){
        List<Cinema> getCinemaLocation = cinemaService.getCinemasByLocation(location);
        return ResponseEntity.status(200).body(getCinemaLocation);
    }

}
