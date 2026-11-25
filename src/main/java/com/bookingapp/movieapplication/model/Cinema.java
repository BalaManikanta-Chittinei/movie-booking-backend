package com.bookingapp.movieapplication.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor 
@Data
public  class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cinemaId;

    @NotBlank(message = "theatreName cannot be empty.")
    private String theatreName;

    @NotBlank(message = "location cannot be empty.")
    private String location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-cinema")
    private User user;


    @OneToMany(mappedBy = "cinema" , cascade = CascadeType.ALL)
    @JsonManagedReference("cinema-screen")
    private List<Screen> screen;

}
