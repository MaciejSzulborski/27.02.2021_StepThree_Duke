
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        //this("data/ratings.csv");
        this("data/ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings FirstRatings = new FirstRatings();
        myRaters = FirstRatings.loadRaters(ratingsfile);
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
   
    private double getAverageByID(String id, int minimalRaters) {
        // Calculate how many raters rated movie with this id 
        // and get a total value of all ratings
        int ratingCount = 0;
        double totalRating = 0.0;
        double average = 0.0;
        for (Rater currRater : myRaters) {
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
        // Load a list of movie IDs
        ArrayList<String> myMovies = 
                            MovieDatabase.filterBy(new TrueFilter());
        // itterate over all movie IDs
        for (String movieID : myMovies) {
            double currValue = getAverageByID(movieID,
                                                minimalRaters);
            Rating currRating = new Rating(movieID, currValue);
            if(currValue != 0.0){
                averageRatings.add(currRating);
            }
        }
        return averageRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(
                            int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> averageRatings = new ArrayList<Rating>();
        ArrayList<String> myMovies = 
                            MovieDatabase.filterBy(filterCriteria);
        for (String movieID : myMovies) {
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
    }
}
