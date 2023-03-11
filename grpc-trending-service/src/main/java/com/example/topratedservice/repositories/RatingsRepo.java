package com.example.topratedservice.repositories;

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

    public List<String> getTopRatedMovies() {
            String query = "SELECT MOVIEID, AVG(RATING) AS overall_rating FROM rating GROUP BY MOVIEID ORDER BY overall_rating DESC";
        return jdbcTemplate.query(query, (rs, rowNum) -> rs.getString("MOVIEID"));
    }
}
