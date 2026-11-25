package com.bookingapp.movieapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bookingapp.movieapplication.exceptions.UserNotFoundException;
import com.bookingapp.movieapplication.model.User;
import com.bookingapp.movieapplication.reporsitory.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j 
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired 
    private PasswordEncoder passwordEncoder;

 @Transactional
    public User addUser(User user){
        log.info("Received request to create user : {}",user.getUsername());
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser =userRepository.save(user);
        log.info("User created successfully with id : {}",savedUser.getUserId());
        return savedUser;
    }    


@Transactional(readOnly = true)
public  Page<User> getAllUsers(int page ,int size) {
    log.info("Fetching users with page: {} and size: {}", page,size);
    Pageable pageable = PageRequest.of(page, size);
    return userRepository.findAll(pageable);
     
}

@Transactional(readOnly = true)
public User getUserById(Long userId){

    log.info("Fetching user with id : {}", userId);

    User user = userRepository.findById(userId)
            .orElseThrow(() -> {
                log.warn("User not found with id : {}",userId);
                return new UserNotFoundException("User not found with id : " + userId);});
    log.info("User fetched successfully with id : {}",userId);
    return user;
}

@Transactional 
public User updateUser(Long userId, User user){
    log.info("Received request for updating the user:{}",userId);
    User existingUser = userRepository.findById(userId)
            .orElseThrow(() -> {
                log.warn("User not found with Id: {}",userId);
                return new UserNotFoundException(
                    "User not found with id : " + userId);
                });

    existingUser.setUsername(user.getUsername());
    existingUser.setPassword(user.getPassword());
    log.info("User Updated Successfully with Id;{}",userId);
    return userRepository.save(existingUser);
}

@Transactional 
public void removeUser(Long userId) {

    log.info("Fetching user with Id:{}",userId);
    User user = userRepository.findById(userId)
            .orElseThrow(() -> {
                log.warn("User not found with id:{}",userId);
                   return new UserNotFoundException(
                            "User not found with id : " + userId);});
          log.info("User Deleted Successfully with Id:{}",userId);       
    userRepository.delete(user);
}

}
