package com.stackroute.rediscacheservice.repository;

import com.stackroute.rediscacheservice.model.Movie;

import java.util.Map;

public interface MovieRepository {

    void save(Movie movie);

    Map<String, Movie> findAll();

    Movie findById(String movieId);

    void update(Movie movie);

    void delete(String movieId);

}
