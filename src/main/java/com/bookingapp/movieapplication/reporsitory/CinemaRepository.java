package com.bookingapp.movieapplication.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookingapp.movieapplication.model.Cinema;
import java.util.List;


@Repository

public interface CinemaRepository extends JpaRepository<Cinema,Long> {
    List<Cinema> findByLocation(String location);
}
