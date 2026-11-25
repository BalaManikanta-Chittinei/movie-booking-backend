package com.bookingapp.movieapplication.reporsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookingapp.movieapplication.model.Movie;
import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
     List<Movie> findByTitle(String title);

}
