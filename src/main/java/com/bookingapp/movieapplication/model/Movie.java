package com.bookingapp.movieapplication.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long movieId;

    @NotBlank(message = "title cannot be empty.")
    private String title;

    @NotBlank(message = "releaseDate cannot be empty.")
    private String releaseDate;

    @NotBlank(message = "showCycle cannot be empty.")
    private String showCycle;

    @OneToOne(mappedBy = "movie")
    @JsonBackReference("screen-movie")
    private Screen screen;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    @JsonManagedReference("movie-booking")
    private List<Booking> bookings;

}
