package com.guilherme.miguel;

import com.guilherme.miguel.domain.Movie;
import com.guilherme.miguel.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@EnableConfigurationProperties({ArangodbSampleProperties.class})
@SpringBootApplication
public class ArangodbSampleApplication implements CommandLineRunner {

    private MovieRepository movieRepository;

    public ArangodbSampleApplication(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ArangodbSampleApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Movie movie = new Movie(UUID.randomUUID().toString(), "The title", "Director", LocalDateTime.now());

        movieRepository.save(movie);

        log.info("Movie: {}", movieRepository.get(movie.getKey()));

        movie.setTitle("New title");
        movieRepository.update(movie.getKey(), movie);
        log.info("Updated Movie: {}", movieRepository.get(movie.getKey()));
    }
}
