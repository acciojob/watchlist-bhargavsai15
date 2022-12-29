package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository //way to say that this is Repository layer
public class MovieRepository {



    HashMap<String,Movie> movieDb=new HashMap<>();

    HashMap<String,Director> directorDb=new HashMap<>();

    HashMap<String,String> movieDirectorHashMap=new HashMap<>();

    public String addMovieToDb(Movie movie){
        String key=movie.getName();
        movieDb.put(key,movie);
        return "Movie added successfully";
    }

    public String addDirectorToDb(Director director){
        String key=director.getName();
        directorDb.put(key,director);
        return "Director added successfully";
    }

    public Movie getMovieByName(String name){
        Movie movie=movieDb.get(name);
        return movie;
    }

    public Director getDirectorByName(String name){
        Director director=directorDb.get(name);
        return director;
    }

    public String addMovieDirectorPairToDb(String movieName,String directorName){
        movieDirectorHashMap.put(movieName,directorName);
        return "Movie and director are paired successfully";
    }

    public List<String> getMoviesByDirectorNameFromDb(String directorName){

        List<String> movieNames=new ArrayList<>();

        for(String movieName:movieDirectorHashMap.keySet()){
            if(movieDirectorHashMap.get(movieName).equals(directorName)){
                movieNames.add(movieName);
            }
        }
        return movieNames;
    }

    public List<String> getAllMovies(){
        List<String> allMovies=new ArrayList<>();
        for(String name:movieDb.keySet()){
            allMovies.add(name);
        }
        return allMovies;
    }

    public String deleteDirectorByName(String name){
        for(String movieName:movieDirectorHashMap.keySet()){
            String directorName=movieDirectorHashMap.get(movieName);
            if(directorName.equals(name)){
                 movieDirectorHashMap.remove(movieName);
                 directorDb.remove(directorName);
                 movieDb.remove(movieName);
            }
        }
        return "deleted successfully";
    }

    public String deleteAllDirectors(){
        for(String movieName:movieDirectorHashMap.keySet()){
            if(movieDirectorHashMap.get(movieName)!=null){
                movieDirectorHashMap.remove(movieName);
                movieDb.remove(movieName);
                directorDb.remove(movieDirectorHashMap.get(movieName));
            }
        }
        return "deleted successfully";
    }

}
