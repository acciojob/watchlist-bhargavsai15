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

    HashMap<String,List<String>> movieDirectorHashMap=new HashMap<>();

    List<String> allMovies=new ArrayList<>();

    public String addMovieToDb(Movie movie){
        String key=movie.getName();
        movieDb.put(key,movie);
        allMovies.add(movie.getName());
        return "success";
    }

    public String addDirectorToDb(Director director){
        String key=director.getName();
        directorDb.put(key,director);
        return "success";
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
        if(!movieDirectorHashMap.containsKey(directorName)){
            movieDirectorHashMap.put(directorName,new ArrayList<>());
        }
        movieDirectorHashMap.get(directorName).add(movieName);

        return "success";
    }



    public String getDirectorNameByMovieName(String movieName){
        //fetching the director names
        for(String director:movieDirectorHashMap.keySet()){
            for(String movies:movieDirectorHashMap.get(director)){      //fetch all movies for that director
                if(movies.equals(movieName)){     //if movieName match
                    return director;
                }
            }
        }
        return null;    //movie not found
    }

    public List<String> getMoviesByDirectorNameFromDb(String directorName){

        for(String director:movieDirectorHashMap.keySet()){
            if(director.equals(directorName)){
                return movieDirectorHashMap.get(director);
            }
        }
        return null;
    }

    public List<String> getAllMovies(){
        return allMovies;
    }

    public String deleteDirectorByName(String name){
        outer:
        for(String director:movieDirectorHashMap.keySet()){
            if(director.equals(name)){
                for (String movie:movieDirectorHashMap.get(director)){
                    movieDb.remove(movie);
                    allMovies.remove(movie);
                }
                break outer;
            }
        }
        movieDirectorHashMap.remove(name);
        directorDb.remove(name);
        return "success";
    }

    public String deleteAllDirectors(){
        for(List<String> movies:movieDirectorHashMap.values()){
            for(String name:movies){
                movieDb.remove(name);
                allMovies.remove(name);
            }
        }
        movieDirectorHashMap.clear();
        directorDb.clear();
        return "success";
    }

}
