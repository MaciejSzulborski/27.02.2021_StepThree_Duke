
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    
    // getAverageRatings()
    // - Reads IMDb files with data of Raters and Movies
    // - Uses SecondRatings class to create ArrayLists with
    //      Movies and Raters data
    // - Uses SecondRatings class to create ArrayLists with
    //      average rating for each movie with more than
    //      minimalRatings amount of ratings
    // - Sorts averageRatings in ascending order and 
    //      saves saves data in sortedAverageRatings
    //      uses private method getSortedAverageRatings
    public void printAverageRatings(){
        // DETERMINE FILES WITH DATA
        //String moviefile = "data/ratedmoviesfull.csv";
        //String moviefile = "data/ratedmovies_short.csv";
        String moviefile = "data/ratedmovies_quiz1.csv";
        //String ratingsfile = "data/ratings.csv";
        //String ratingsfile = "data/ratings_short.csv";
        String ratingsfile = "data/ratings_quiz1.csv";
        
        SecondRatings SecondRatings = new SecondRatings(
                                                moviefile,ratingsfile);
        int movieSize = SecondRatings.getMovieSize();
        int raterSize = SecondRatings.getRaterSize();
        
        System.out.println("movieSize = " + movieSize);
        System.out.println("raterSize = " + raterSize);
        
        // Sort movies with a specifed amount of ratings
        // by rating value - ascending
        
        // DETERMIN MINIMALRATINGS GATE
        int minimalRatings = 2;
        ArrayList<Rating> averageRatings = 
                    SecondRatings.getAverageRatings(minimalRatings);
        ///*
        System.out.println("\nRating list in ascending order:\n");
        ArrayList<Rating> sortedAverageRatings = 
                    getSortedAverageRatings(averageRatings);
        for(int i=0; i < sortedAverageRatings.size();i++){
            Rating currRating = sortedAverageRatings.get(i);
            double currValue = currRating.getValue();
            String currMovieID = currRating.getItem();
            String currTitle = SecondRatings.getTitle(currMovieID);
            System.out.println(currValue + " " + currTitle);
        }
        //*/
        
    }
    
    // getAverageRatingOneMovie()
    // - Reads IMDb files with data of Raters and Movies
    // - Uses SecondRatings class to create ArrayLists with
    //      average rating for each movie with more than
    //      minimalRatings amount of ratings
    // - Processes input title to fing its rating
    // - If no such title is found user gets informed about it
    // 
    // minimalRatings set to zero - complete averageRatingsList
    public void getAverageRatingOneMovie(){
        // DETERMINE FILES WITH DATA
        //String moviefile = "data/ratedmoviesfull.csv";
        String moviefile = "data/ratedmovies_short.csv";
        //String ratingsfile = "data/ratings.csv";
        String ratingsfile = "data/ratings_short.csv";
        
        // PUT MOVIE TITLE HERE 
        String movieTitle = "The Godfather";
        
        SecondRatings SecondRatings = new SecondRatings(
                                                moviefile,ratingsfile);
        int movieSize = SecondRatings.getMovieSize();
        int raterSize = SecondRatings.getRaterSize();
        
        System.out.println("movieSize = " + movieSize);
        System.out.println("raterSize = " + raterSize);
        
        int minimalRatings = 0;
        ArrayList<Rating> averageRatings = 
                    SecondRatings.getAverageRatings(minimalRatings);
        
        String movieID = SecondRatings.getID(movieTitle);
        boolean movieFound = !movieID.contains(" ");
        double movieValue = -1.0;
        
        if(movieFound){
            for(int i=0; i < averageRatings.size();i++){
                Rating currRating = averageRatings.get(i);
                String currMovieID = currRating.getItem();
                if(currMovieID.equals(movieID)){
                    movieValue = currRating.getValue();
                }
            }
            System.out.println(movieValue + "\t" + movieTitle);
        }else{
            System.out.println(movieID);
        }
        
    }
    
    // Helper method for sorting ArrayList<Rating>
    // Uses Insert sort
    
    private ArrayList<Rating> getSortedAverageRatings(
                                ArrayList<Rating> averageRatings){
        ArrayList<Rating> sortedAverageRatings = averageRatings;
        for(int i=1; i<sortedAverageRatings.size();i++){
            Rating currRating = sortedAverageRatings.get(i);
            double currValue = currRating.getValue();
            int j = i-1;
            while(j>=0 && 
                currValue < sortedAverageRatings.get(j).getValue()){
                    Rating swapRating = sortedAverageRatings.get(j);
                    sortedAverageRatings.set(j+1,swapRating);
                    j--;
            }
            sortedAverageRatings.set(j+1,currRating);
        }
        return sortedAverageRatings;
    }
}
