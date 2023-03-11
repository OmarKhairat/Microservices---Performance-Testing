package com.example.topratedservice.grpc;

import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.List;

public class TopRatedServiceImpl extends TopRatedServiceGrpc.TopRatedServiceImplBase {
    @Override
    public void getTopRatedMovies(GRPC.TopRatedRequest request, StreamObserver<GRPC.TopRatedResponse> responseObserver) {
        // Create a list of dummy movies
        List<Movie> movies = Arrays.asList(
                Movie.newBuilder()
                        .setMovieId("1")
                        .setTitle("Movie 1")
                        .setOverview("Overview of Movie 1")
                        .build(),
                Movie.newBuilder()
                        .setMovieId("2")
                        .setTitle("Movie 2")
                        .setOverview("Overview of Movie 2")
                        .build(),
                Movie.newBuilder()
                        .setMovieId("3")
                        .setTitle("Movie 3")
                        .setOverview("Overview of Movie 3")
                        .build()
        );

        // Create a response message with the list of movies
        GRPC.TopRatedResponse response = GRPC.TopRatedResponse.newBuilder()
                .addAllMovie(movies)
                .build();

        // Send the response back to the client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
