
/**
 * Write a description of SecondRatings here.
 * 
 * @author Maciej Szulborski 
 * @version 02.02.2021
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        //this("data/ratedmoviesfull.csv", "data/ratings.csv");
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }
    
    public SecondRatings(String moviefile,String ratingsfile) {
        FirstRatings FirstRatings = new FirstRatings();
        myMovies = FirstRatings.loadMovies(moviefile);
        myRaters = FirstRatings.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        int movieSize = myMovies.size();
        return movieSize;
    }
    
    public int getRaterSize(){
        int raterSize = myRaters.size();
        return raterSize;
    }
    
    public String getTitle(String movieID){
        String title = "Database doesn't hold a title of a movie " 
                + "with provided ID";
        for(int i = 0; i < myMovies.size(); i++){
            Movie currMovie = myMovies.get(i);
            String currMovieID = currMovie.getID();
            if(currMovieID.equals(movieID)){
                title = currMovie.getTitle();
            }
        }
        return title;
    }
    
    public String getID(String title){
        String movieID = "Database doesn't hold an ID of a movie " 
                + "with provided title";
        for(int i = 0; i < myMovies.size(); i++){
            Movie currMovie = myMovies.get(i);
            String currMovieTitle = currMovie.getTitle();
            if(currMovieTitle.equals(title)){
                movieID = currMovie.getID();
            }
        }
        return movieID;
    }
   
    private double getAverageByID(String id, int minimalRaters) {
        // Calculate how many raters rated movie with this id 
        // and get a total value of all ratings
        int ratingCount = 0;
        double totalRating = 0.0;
        double average = 0.0;
        for (int i = 0; i < myRaters.size(); i++) {
            Rater currRater = myRaters.get(i);
            if(currRater.hasRating(id)){
                ratingCount++;
                totalRating += currRater.getRating(id);
            }
        }
        // If movie doesnt have enought ratings then return 0.0
        if(minimalRaters > ratingCount){
            return 0.0;        
        }
        average = totalRating/ratingCount;
        return average;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        // itterate over all movies
        for (Movie m : myMovies) {
            String movieID = m.getID();
            double currValue = getAverageByID(movieID,
                                                minimalRaters);
            Rating currRating = new Rating(movieID, currValue);
            if(currValue != 0.0){
                averageRatings.add(currRating);
            }
        }
        return averageRatings;
    }
    
    public void tester(){
        /* Test getAverageByID
        String movieID = "1798709";
        double average = getAverageByID(movieID,3);
        System.out.println("Average for movie is: " + average);
        */
        
        // Test getAverageRatings()
        int minimalRaters = 2;
        ArrayList<Rating> averageRatings = 
                                getAverageRatings(minimalRaters);
        
        for (int i = 0; i < averageRatings.size(); i++) {
            String currMovieID = averageRatings.get(i).getItem();
            double currMovieAverage = 
                                averageRatings.get(i).getValue();
            System.out.println("Movie ID " + currMovieID 
                                + " was rated " + currMovieAverage);
        }
       
        // Test getTitle()
        String movieID = "1798709";
        String title = getTitle(movieID);
        
        System.out.println("MovieID: " + movieID +
                            "\tTitle: " + title);
                            
        // Test getID()
        
        String title2 = "Her";
        String movieID2 = getID(title2);
        
        System.out.println("Title: " + title2 + 
                            "\tMovieID: " + movieID2 );
    }
}
