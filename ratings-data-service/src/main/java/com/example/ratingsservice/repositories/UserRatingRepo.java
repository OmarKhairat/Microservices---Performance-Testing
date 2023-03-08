package com.example.ratingsservice.repositories;

import com.example.ratingsservice.models.Rating;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRatingRepo extends JPARepository<Rating, String> {

    @Query("SELECT R.movieId, R.rating FROM Rating R WHERE R.userId = :userId")
    List<Rating> findByUserId(@Param("userId") String userId);
}