package com.stackroute.rediscacheservice.repository;


import com.stackroute.rediscacheservice.model.Movie;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    private RedisTemplate<String, Movie> redisTemplate;

    private HashOperations hashOperations;

    public MovieRepositoryImpl(RedisTemplate<String, Movie> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Movie movie) {

        hashOperations.put("MOVIE", movie.getMovieId(), movie);

    }

    @Override
    public Map<String, Movie> findAll() {
        return hashOperations.entries("MOVIE");
    }

    @Override
    public Movie findById(String movieId) {
        return (Movie) hashOperations.get("MOVIE", movieId);
    }

    @Override
    public void update(Movie movie) {
        save(movie);

    }

    @Override
    public void delete(String movieId) {

        hashOperations.delete("MOVIE", movieId);

    }
}
