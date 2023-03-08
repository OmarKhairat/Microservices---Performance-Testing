package com.example.ratingsservice.repositories;

import com.example.ratingsservice.models.Rating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRatingRepo extends CrudRepository<Rating, String> {

    @Query("SELECT R.movieId, R.rating FROM Rating R WHERE R.userId = :userId")
    List<Rating> findByUserId(@Param("userId") String userId);
}