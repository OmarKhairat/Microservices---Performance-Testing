package com.example.topratedservice.grpc;

import com.example.topratedservice.repositories.RatingsRepo;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    RatingsRepo repo ;
    public MyCommandLineRunner(RatingsRepo ratingsRepo){
        repo=ratingsRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // your code to run on startup goes here
        System.out.println("My command line runner executed");
        Server server = ServerBuilder.forPort(8086)
                .addService(new TopRatedServiceImpl(repo)).build();
        server.start();
        server.awaitTermination();

    }
}