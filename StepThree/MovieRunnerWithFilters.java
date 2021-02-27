
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;

public class MovieRunnerWithFilters {
    // getAverageRatings()
    // - Reads IMDb files with data of Raters and Movies
    // - Uses MovieDatabase to load make a database of movies
    // - Uses ThirdRatings class to create ArrayLists with
    //      average rating for each movie with more than
    //      minimalRatings amount of ratings
    // - Sorts averageRatings in ascending order and 
    //      saves data in sortedAverageRatings
    //      uses private method getSortedAverageRatings
    public void printAverageRatings(){
        // DETERMINE FILES WITH DATA
        String moviefile = "data/ratedmoviesfull.csv";
        //String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        // Create ThirdRatings and initialize movie database
        ThirdRatings ThirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        // Get and print size of raters and movie database
        int raterSize = ThirdRatings.getRaterSize();
        int movieSize = MovieDatabase.size();
        System.out.println("read data for " + raterSize + " raters");
        System.out.println("read data for " + movieSize + " movies");

        // Get AverageRatings data    
        
        // DETERMIN MINIMALRATINGS GATE
        int minimalRatings = 35;
        // load an ArrayList of averageRatings 
        ArrayList<Rating> averageRatings = 
                    ThirdRatings.getAverageRatings(minimalRatings);
        // Get and print size of averageRatings
        int averageRatingsSize = averageRatings.size();
        System.out.println("found " + averageRatingsSize + " movies");
        /*
        ArrayList<Rating> sortedAverageRatings = 
                    getSortedAverageRatings(averageRatings);
        printSortedAverageRatings(sortedAverageRatings); 
        */
    }
    
    public void printAverageRatingsByYear(){
        // DETERMINE FILES WITH DATA
        String moviefile = "data/ratedmoviesfull.csv";
        //String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        // Create ThirdRatings and initialize movie database
        ThirdRatings ThirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        // Get and print size of raters and movie database
        int raterSize = ThirdRatings.getRaterSize();
        int movieSize = MovieDatabase.size();
        System.out.println("read data for " + raterSize + " raters");
        System.out.println("read data for " + movieSize + " movies");

        // Get AverageRatings data    
        
        // DETERMIN MINIMALRATINGS GATE AND FILTER TYPE
        int minimalRatings = 20;
        int yearFilterValue = 2000;
        Filter filter = new YearAfterFilter(yearFilterValue);
        
        // load an ArrayList of averageRatings 
        ArrayList<Rating> averageRatings = 
            ThirdRatings.getAverageRatingsByFilter(
                                    minimalRatings,filter);
        // Get and print size of averageRatings
        int averageRatingsSize = averageRatings.size();
        System.out.println("found " + averageRatingsSize + " movies");
        /* sort and print
        ArrayList<Rating> sortedAverageRatings = 
                    getSortedAverageRatings(averageRatings);
        printSortedAverageRatings(sortedAverageRatings);
        */
    }
    
    public void printAverageRatingsByGenre(){
        // DETERMINE FILES WITH DATA
        String moviefile = "data/ratedmoviesfull.csv";
        //String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        // Create ThirdRatings and initialize movie database
        ThirdRatings ThirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        // Get and print size of raters and movie database
        int raterSize = ThirdRatings.getRaterSize();
        int movieSize = MovieDatabase.size();
        System.out.println("read data for " + raterSize + " raters");
        System.out.println("read data for " + movieSize + " movies");

        // Get AverageRatings data    
        
        // DETERMIN MINIMALRATINGS GATE AND FILTER TYPE
        int minimalRatings = 20;
        String genreFilterValue = "Comedy";
        Filter filter = new GenreFilter(genreFilterValue);
        
        // load an ArrayList of averageRatings 
        ArrayList<Rating> averageRatings = 
            ThirdRatings.getAverageRatingsByFilter(
                                    minimalRatings,filter);
        // Get and print size of averageRatings
        int averageRatingsSize = averageRatings.size();
        System.out.println("found " + averageRatingsSize + " movies");
        
        /* sort and print
        ArrayList<Rating> sortedAverageRatings = 
                    getSortedAverageRatings(averageRatings);
        for(int i=0; i < sortedAverageRatings.size();i++){
            Rating currRating = sortedAverageRatings.get(i);
            double currValue = currRating.getValue();
            String currMovieID = currRating.getItem();
            String currTitle = MovieDatabase.getTitle(currMovieID);
            System.out.println(currValue + " " + currTitle);
            String currGenres = MovieDatabase.getGenres(currMovieID);
            System.out.println("\t" + currGenres);
        }
        */
    }
    
    public void printAverageRatingsByMinutes(){
        // DETERMINE FILES WITH DATA
        String moviefile = "data/ratedmoviesfull.csv";
        //String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        // Create ThirdRatings and initialize movie database
        ThirdRatings ThirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        // Get and print size of raters and movie database
        int raterSize = ThirdRatings.getRaterSize();
        int movieSize = MovieDatabase.size();
        System.out.println("read data for " + raterSize + " raters");
        System.out.println("read data for " + movieSize + " movies");

        // Get AverageRatings data    
        
        // DETERMIN MINIMALRATINGS GATE AND FILTER TYPE
        int minimalRatings = 5;
        int minMinutes = 105;
        int maxMinutes = 135;
        Filter filter = new MinutesFilter(minMinutes, maxMinutes);
        
        // load an ArrayList of averageRatings 
        ArrayList<Rating> averageRatings = 
            ThirdRatings.getAverageRatingsByFilter(
                                    minimalRatings,filter);
        // Get and print size of averageRatings
        int averageRatingsSize = averageRatings.size();
        System.out.println("found " + averageRatingsSize + " movies");
        
        /* sort and print
        ArrayList<Rating> sortedAverageRatings = 
                    getSortedAverageRatings(averageRatings);
        for(int i=0; i < sortedAverageRatings.size();i++){
            Rating currRating = sortedAverageRatings.get(i);
            double currValue = currRating.getValue();
            String currMovieID = currRating.getItem();
            String currTitle = MovieDatabase.getTitle(currMovieID);
            int currMinutes = MovieDatabase.getMinutes(currMovieID);
            
            System.out.println(currValue + " Time: " 
                                    + currMinutes + " " +currTitle);
        }
        */
    }
    
    public void printAverageRatingsByDirectors(){
        // DETERMINE FILES WITH DATA
        String moviefile = "data/ratedmoviesfull.csv";
        //String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        // Create ThirdRatings and initialize movie database
        ThirdRatings ThirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        // Get and print size of raters and movie database
        int raterSize = ThirdRatings.getRaterSize();
        int movieSize = MovieDatabase.size();
        System.out.println("read data for " + raterSize + " raters");
        System.out.println("read data for " + movieSize + " movies");

        // Get AverageRatings data    
        
        // DETERMIN MINIMALRATINGS GATE AND FILTER TYPE
        int minimalRatings = 4;
        String directors = "Clint Eastwood,Joel Coen"
            +",Martin Scorsese,Roman Polanski,Nora Ephron,"
            +"Ridley Scott,Sydney Pollack";
        Filter filter = new DirectorsFilter(directors);
        
        // load an ArrayList of averageRatings 
        ArrayList<Rating> averageRatings = 
            ThirdRatings.getAverageRatingsByFilter(
                                    minimalRatings,filter);
        // Get and print size of averageRatings
        int averageRatingsSize = averageRatings.size();
        System.out.println("found " + averageRatingsSize + " movies");
        /* sort and print
        ArrayList<Rating> sortedAverageRatings = 
                    getSortedAverageRatings(averageRatings);
        for(int i=0; i < sortedAverageRatings.size();i++){
            Rating currRating = sortedAverageRatings.get(i);
            double currValue = currRating.getValue();
            String currMovieID = currRating.getItem();
            String currTitle = MovieDatabase.getTitle(currMovieID);
            System.out.println(currValue + " " + currTitle);
            String currDirector = 
                            MovieDatabase.getDirector(currMovieID);
            System.out.println("\t" + currDirector);
        } 
        */
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        // DETERMINE FILES WITH DATA
        String moviefile = "data/ratedmoviesfull.csv";
        //String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        // Create ThirdRatings and initialize movie database
        ThirdRatings ThirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        // Get and print size of raters and movie database
        int raterSize = ThirdRatings.getRaterSize();
        int movieSize = MovieDatabase.size();
        System.out.println("read data for " + raterSize + " raters");
        System.out.println("read data for " + movieSize + " movies");

        // Get AverageRatings data    
        
        // DETERMIN MINIMALRATINGS GATE AND FILTER TYPE
        int minimalRatings = 8;
        int yearFilterValue = 1990;
        String genreFilterValue = "Drama";
        
        
        Filter yearAfterFilter = new YearAfterFilter(yearFilterValue);
        Filter genreFilter = new GenreFilter(genreFilterValue);
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(yearAfterFilter);
        allFilters.addFilter(genreFilter);
        
        // load an ArrayList of averageRatings 
        ArrayList<Rating> averageRatings = 
            ThirdRatings.getAverageRatingsByFilter(
                                    minimalRatings,allFilters);
        // Get and print size of averageRatings
        int averageRatingsSize = averageRatings.size();
        System.out.println("found " + averageRatingsSize + " movies");
        /* sort and print
        ArrayList<Rating> sortedAverageRatings = 
                    getSortedAverageRatings(averageRatings);
        for(int i=0; i < sortedAverageRatings.size();i++){
            Rating currRating = sortedAverageRatings.get(i);
            double currValue = currRating.getValue();
            
            String currMovieID = currRating.getItem();
            int currYear = MovieDatabase.getYear(currMovieID);
            String currTitle = MovieDatabase.getTitle(currMovieID);
            String currGenres = MovieDatabase.getGenres(currMovieID);
            System.out.println(currValue + " " + currYear + " " 
                            + currTitle + "\n\t" + currGenres);
        } 
        */
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        // DETERMINE FILES WITH DATA
        String moviefile = "data/ratedmoviesfull.csv";
        //String moviefile = "data/ratedmovies_short.csv";
        String ratingsfile = "data/ratings.csv";
        //String ratingsfile = "data/ratings_short.csv";
        
        // Create ThirdRatings and initialize movie database
        ThirdRatings ThirdRatings = new ThirdRatings(ratingsfile);
        MovieDatabase.initialize(moviefile);
        
        // Get and print size of raters and movie database
        int raterSize = ThirdRatings.getRaterSize();
        int movieSize = MovieDatabase.size();
        System.out.println("read data for " + raterSize + " raters");
        System.out.println("read data for " + movieSize + " movies");

        // Get AverageRatings data    
        
        // DETERMIN MINIMALRATINGS GATE AND FILTER TYPE
        int minimalRatings = 3;
        String directors ="Clint Eastwood,Joel Coen,Tim Burton,"
            +"Ron Howard,Nora Ephron,Sydney Pollack";
        int minMinutes = 90;
        int maxMinutes = 180;
        
        Filter minutesFilter = new MinutesFilter(minMinutes, maxMinutes);
        Filter directorsFilter = new DirectorsFilter(directors);
        
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(minutesFilter);
        allFilters.addFilter(directorsFilter);
        
        // load an ArrayList of averageRatings 
        ArrayList<Rating> averageRatings = 
            ThirdRatings.getAverageRatingsByFilter(
                                    minimalRatings,allFilters);
        // Get and print size of averageRatings
        int averageRatingsSize = averageRatings.size();
        System.out.println("found " + averageRatingsSize + " movies");
        /* sort and print
        ArrayList<Rating> sortedAverageRatings = 
                    getSortedAverageRatings(averageRatings);
        for(int i=0; i < sortedAverageRatings.size();i++){
            Rating currRating = sortedAverageRatings.get(i);
            double currValue = currRating.getValue();
            
            String currMovieID = currRating.getItem();
            int currMinutes = MovieDatabase.getMinutes(currMovieID);
            int currYear = MovieDatabase.getYear(currMovieID);
            String currTitle = MovieDatabase.getTitle(currMovieID);
            String currDirector = 
                            MovieDatabase.getDirector(currMovieID);
            System.out.println(currValue + " Time: " + currMinutes 
                        + " " + currTitle + "\n\t" + currDirector);
        } 
        */
    }
    
    // Helper method for sorting ArrayList<Rating>
    // Uses Insert sort
    // sorts in ascending order
    // returns ArrayList<Rating> sortedAverageRatings
    
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
    
    // Helper method for printing an ArrayList<Rating>
    // Prints Ratings (value + movie title)
    
    private void printSortedAverageRatings(
                            ArrayList<Rating> sortedAverageRatings){
        for(int i=0; i < sortedAverageRatings.size();i++){
            Rating currRating = sortedAverageRatings.get(i);
            double currValue = currRating.getValue();
            String currMovieID = currRating.getItem();
            String currTitle = MovieDatabase.getTitle(currMovieID);
            System.out.println(currValue + " " + currTitle);
        }  
    }
}
