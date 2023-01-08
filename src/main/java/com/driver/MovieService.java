package com.driver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //Way to tell this is service layer
public class MovieService {

    @Autowired
    MovieRepository movieRepository;


    String addMovie(Movie movie){
        String message=movieRepository.addMovieToDb(movie);
        return  message;
    }

    String addDirector(Director director){
        String message=movieRepository.addDirectorToDb(director);
        return message;
    }
    Movie getMovieByName(String name){
        return movieRepository.getMovieByName(name);
    }

    Director getDirectorByName(String name){
        return movieRepository.getDirectorByName(name);
    }

    String addMovieDirectorPair(String movieName,String directorName){
        String message=movieRepository.addMovieDirectorPairToDb(movieName,directorName);
        return message;
    }

    List<String>  getMoviesByDirectorName(String directorName){
        List<String> movies=movieRepository.getMoviesByDirectorNameFromDb(directorName);
        return movies;
    }

    String getDirectorNameByMovieName(String movieName){
        return movieRepository.getDirectorNameByMovieName(movieName);
    }

    List<String> findAllMovies(){
        List<String> movieNames=movieRepository.getAllMovies();
        return movieNames;
    }

    String deleteDirectorByName(String name){
        String message=movieRepository.deleteDirectorByName(name);
        return message;
    }

    String deleteAllDirectors(){
        String message=movieRepository.deleteAllDirectors();
        return message;
    }

}
