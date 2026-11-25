package com.bookingapp.movieapplication.reporsitory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.bookingapp.movieapplication.model.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen,Long>{
    List<Screen> findByScreenType(String screenType);

}
