package com.bookingapp.movieapplication.model;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Booking {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY )
    private Long bookingId;

    @NotNull(message = "Booking date is required.")
    private LocalDate bookingDate;

    @NotBlank(message = "show time is required.")
    private String showTime;

    @NotNull (message = "Ticket count is required.")
    @Min(value = 1 , message = "Ticket count must be at least 1")
    private Integer ticketCount;
    
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference ("booking-user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @JsonBackReference("booking-movie")
    private Movie movie;

}
