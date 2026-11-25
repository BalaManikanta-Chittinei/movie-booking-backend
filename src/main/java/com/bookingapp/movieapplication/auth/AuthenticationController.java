package com.bookingapp.movieapplication.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookingapp.movieapplication.dto.LoginRequestDTO;
import com.bookingapp.movieapplication.dto.LoginResponseDTO;
import com.bookingapp.movieapplication.security.JwtService;

@RestController 
@RequestMapping ("/auth")
public class AuthenticationController {

    @Autowired 
    private AuthenticationManager authenticationManager;

    @Autowired 
    private  JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request){
        Authentication authentication = authenticationManager.authenticate(
                                            new UsernamePasswordAuthenticationToken(request.getUsername(),
                                             request.getPassword()));
        if(authentication.isAuthenticated()){

            String token = jwtService.generateToken(request.getUsername());
            return  ResponseEntity.ok(new LoginResponseDTO(token));
        }
        throw new RuntimeException("Invalid Credentials");

    }
    



}
