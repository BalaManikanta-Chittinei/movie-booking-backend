package com.bookingapp.movieapplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.bookingapp.movieapplication.exceptions.UsernameNotFoundException;
import com.bookingapp.movieapplication.model.User;
import com.bookingapp.movieapplication.reporsitory.UserRepository;

@Service 
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override 
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
         User user = userRepository.findByUsername(username)
                                    .orElseThrow(() ->  new UsernameNotFoundException("User not found"));

        return org.springframework.security
                   .core.userdetails.User.builder()
                   .username(user.getUsername())
                   .password(user.getPassword())
                   .authorities(user.getRole())
                   .build();

    }

}
