package com.example.ratingsservice.repositories;

//import com.example.ratingsservice.models.RatingEntity;
import com.example.ratingsservice.models.MovieRating;
import com.example.ratingsservice.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<MovieRating, String> {
    List<Rating> findByUserId(@Param("userId") String userId);
}
