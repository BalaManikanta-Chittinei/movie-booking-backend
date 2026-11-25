package com.bookingapp.movieapplication.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookingapp.movieapplication.model.Screen;
import com.bookingapp.movieapplication.service.ScreenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/screen")
public class ScreenController {

    @Autowired
    private ScreenService screenService;

    @PostMapping("/createScreen/{cinemaId}")
    public ResponseEntity<Screen> addScreen(@PathVariable Long cinemaId,@Valid @RequestBody Screen screen){
        Screen savedScreen =screenService.addScreen(cinemaId, screen);
        return ResponseEntity.status(201).body(savedScreen);
    }

    @GetMapping("/getScreens")
    public ResponseEntity<Page<Screen>> getScreen(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam (defaultValue = "5") int size){
        Page<Screen> AllScreen= screenService.getAllScreens(page,size);
        return ResponseEntity.status(200).body(AllScreen);
    }

    @GetMapping("/getScreen/{type}")
    public ResponseEntity<List<Screen>> getScreenByType(@PathVariable String type){
        List<Screen> getScreenType = screenService.getByScreenType(type);
        return ResponseEntity.status(200).body(getScreenType);
    }
    
    
    
    

}
