package com.stackroute.rediscacheservice.controller;

import com.stackroute.rediscacheservice.model.Movie;
import com.stackroute.rediscacheservice.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//import com.stackroute.rediscacheservice.service.MovieService;

@RestController
@RequestMapping("/rest/movie")
@Slf4j
public class HomeController {

    private MovieRepository movieRepository;

    public HomeController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

//    @GetMapping("/add/{movieId}/{movieTitle}")
//    public Movie add(@PathVariable("movieId") final String movieId,
//                    @PathVariable("movieTitle") final String movieTitle) {
//        movieRepository.save(new Movie(movieId, movieTitle));
//        return movieRepository.findById(movieId);
//    }

    @PostMapping("/add")
    public void add(@RequestBody Movie movie) {
        movieRepository.save(movie);
    }

//    @GetMapping("/update/{id}/{name}")
//    public User update(@PathVariable("id") final String id,
//                       @PathVariable("name") final String name) {
//        userRepository.update(new User(id, name, 1000L));
//        return userRepository.findById(id);
//    }
//
//    @GetMapping("/delete/{id}")
//    public Map<String, User> delete(@PathVariable("id") final String id) {
//        userRepository.delete(id);
//        return all();
//    }

    @GetMapping("/all")
    public Map<String, Movie> all() {
        return movieRepository.findAll();
    }

    @GetMapping("/find/{movieId}")
    @Cacheable(value = "moviecache")
    @ResponseBody
    public Movie getById(@PathVariable String movieId) {
        System.out.println("inside intellij");
        Movie movie;

        try {
            movie = movieRepository.findById(movieId);

        } catch (Exception e) {
            e.printStackTrace();
            movie = null;
        }
        return movie;
    }


    @Scheduled(cron = "0 */2 * ? * *")
    @CacheEvict(value = "moviecache", allEntries = true)
    public void clearRedisCache() {
        log.info("Clearing Redis Cache after 2 minute");
    }
}








