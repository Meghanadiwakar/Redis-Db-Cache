//package com.stackroute.rediscacheservice.service;
//
//import com.stackroute.rediscacheservice.model.Movie;
//import com.stackroute.rediscacheservice.repository.MovieRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@Service
//public class MovieService implements MovieRepository{
//    private static final String REDIS_KEY = "movie";
//
//    private RedisTemplate<String,Movie> redisTemplate;
//
//    private HashOperations<String, Integer,Movie> hashOperations;
//
//    @Autowired
//    public MovieService(RedisTemplate<String,Movie> redisTemplate)
//    {
//        this.redisTemplate = redisTemplate;
//    }
//
//    @PostConstruct
//    private void initializehashoperations(){
//        hashOperations=redisTemplate.opsForHash();
//    }
//
//
//
//
//    @Override
//    public void save(Movie movie) {
//       hashOperations.put(REDIS_KEY,movie.getMovieId(),movie);
//    }
//
//    @Override
//    public Movie find(int movieId) {
//        return hashOperations.get(REDIS_KEY,movieId);
//    }
//}
