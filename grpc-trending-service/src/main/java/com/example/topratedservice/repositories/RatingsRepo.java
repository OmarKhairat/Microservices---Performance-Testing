package com.example.topratedservice.repositories;

import com.example.topratedservice.models.Movie;
import com.example.topratedservice.models.Rating;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class RatingsRepo {

    private final JdbcTemplate jdbcTemplate;

    public RatingsRepo(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Movie> getTopRatedMovies() {
        String query = "SELECT MOVIEID,NAME,DESCRIPTION FROM rating,movie GROUP BY MOVIEID ORDER BY AVG(RATING) DESC";
        return jdbcTemplate.query(query, (rs, rowNum) -> new Movie(rs.getString("MOVIEID"), rs.getString("NAME"), rs.getString("DESCRIPTION")));
    }
}
