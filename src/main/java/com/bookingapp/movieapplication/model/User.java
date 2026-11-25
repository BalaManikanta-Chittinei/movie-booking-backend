package com.bookingapp.movieapplication.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private Long userId;

    @NotBlank(message = "username cannot be empty.") 
    private String username;

    @NotBlank(message = "password cannot be empty.")
    @Size(min = 8, message = "password must contain at least 8 characters.")
    private String password;

    private String role;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    @JsonManagedReference ("user-cinema")
    private List<Cinema> cinema;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference ("user-booking")
    private List<Booking> bookings;

}
