package com.example.ratingsservice.repositories;

import com.example.ratingsservice.models.Rating;
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

    public List<Rating> getRatingsByUserId(String userId) {
        String sql = "SELECT MOVIEID, RATING FROM rating WHERE USERID = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Rating(rs.getString("MOVIEID"), rs.getInt("RATING")), userId);
    }
}
