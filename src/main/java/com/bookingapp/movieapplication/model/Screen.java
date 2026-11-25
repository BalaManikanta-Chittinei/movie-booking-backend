package com.bookingapp.movieapplication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screenId;

    @NotBlank(message = "screenType cannot be empty.")
    private String screenType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id",unique = true)
    @JsonManagedReference("screen-movie")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    @JsonBackReference("cinema-screen")
    private Cinema cinema;

}
