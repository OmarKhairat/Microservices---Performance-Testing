package com.moviecatalogservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping("/grpcClient")
public class GrpcClient {
    @RequestMapping("/run")
    void callGrpc()
    {
        System.out.println("heeeereeeee");
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8086).usePlaintext().build();
        TopRatedServiceGrpc.TopRatedServiceBlockingStub stub =  TopRatedServiceGrpc.newBlockingStub(managedChannel);
        try {
            GRPC.TopRatedRequest topRatedRequest = GRPC.TopRatedRequest.newBuilder().build();
            GRPC.TopRatedResponse topRatedMovies = stub.getTopRatedMovies(topRatedRequest);
            List<GRPC.Movie> movies = topRatedMovies.getMovieList();
            for(GRPC.Movie movie : movies)
            {
                System.out.println(movie.getMovieId() + " | " + movie.getName() + " | " + movie.getDescription());
            }
        } catch (StatusRuntimeException e) {
            System.out.println(e.getStatus());
            e.printStackTrace();
            return;
        }
    }
}