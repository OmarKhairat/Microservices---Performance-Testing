package com.example.topratedservice.grpc;

import com.example.topratedservice.models.Movie;
import com.example.topratedservice.repositories.RatingsRepo;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TopRatedServiceImpl extends TopRatedServiceGrpc.TopRatedServiceImplBase {
    RatingsRepo ratingsRepo;
    public TopRatedServiceImpl(RatingsRepo ratingsRepo){
        this.ratingsRepo=ratingsRepo;
    }

    @Override
    public void getTopRatedMovies(GRPC.TopRatedRequest request, StreamObserver<GRPC.TopRatedResponse> responseObserver) {
        System.out.println("HAAAAHHHHHHHHHAAAAAAAAAAAAAAA");
        List<Movie> dbMovies = ratingsRepo.getTopRatedMovies();
        List<GRPC.Movie> movies = new ArrayList<>();
        for(Movie movie:dbMovies){
            movies.add(GRPC.Movie.newBuilder()
                    .setMovieId(movie.getMovieId())
                    .setDescription(movie.getDescription())
                    .setName(movie.getName())
                    .build());
        }
//        GRPC.Movie m = GRPC.Movie.newBuilder()
//                .setMovieId("1")
//                .setDescription("Movie 1")
//                .setName("Overview of Movie 1")
//                .build();
        // Create a response message with the list of movies
        GRPC.TopRatedResponse response = GRPC.TopRatedResponse.newBuilder()
                .addAllMovie(movies)
                .build();

        // Send the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
