package com.bookingapp.movieapplication.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bookingapp.movieapplication.model.User;
import com.bookingapp.movieapplication.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/createUser")

    public ResponseEntity<User> addingUser(@Valid @RequestBody User user){
        User users = userService.addUser(user);
        if(users!=null){
            return ResponseEntity.status(201).body(users);
        }
         return ResponseEntity.status(400).build();
    }

    @GetMapping("/getUsers")
    public ResponseEntity<Page<User>> getallUsers(@RequestParam(defaultValue = "0") int page ,
                                                  @RequestParam(defaultValue = "5") int size){
        Page<User> users = userService.getAllUsers(page,size);
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/getBy/{id}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long id){
        User user = userService.getUserById(id);
        return ResponseEntity.status(200).body(user);
    }

    @PutMapping("/update/{userId}")
     public ResponseEntity<User> updateUserDetails(@PathVariable Long userId,@RequestBody User user){
        User updateUser = userService.updateUser(userId,user);

        if(updateUser!=null){
            return ResponseEntity.status(200).body(updateUser);
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("/remove/{userId}")
     public ResponseEntity<String> removeUserDetails(@PathVariable Long userId){
       userService.removeUser(userId);
        return ResponseEntity.status(200).body("User deleted successfully with Id:"+ userId);

    }
}
