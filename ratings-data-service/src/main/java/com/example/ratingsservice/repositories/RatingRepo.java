package com.example.ratingsservice.repositories;

import com.example.ratingsservice.models.Rating;
import com.example.ratingsservice.models.RatingEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepo extends JpaRepository<RatingEntity, String> {

    @Query("SELECT R.movieId, R.rating FROM RatingEntity R WHERE R.userId = :userId")
    List<Rating> findByUserId(@Param("userId") String userId);
}