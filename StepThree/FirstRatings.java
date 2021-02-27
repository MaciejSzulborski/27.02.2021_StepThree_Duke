
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    private ArrayList<String> directorNames;
    private ArrayList<Integer> directorWorks;
        
    public ArrayList<Movie> loadMovies (String filename){
        ArrayList<Movie> movies= new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for(CSVRecord rec : parser){
            int minutes = Integer.parseInt(rec.get(6));
            Movie movie = new Movie(rec.get(0),rec.get(1),rec.get(2),
                    rec.get(4),rec.get(5), rec.get(3), 
                    rec.get(7), minutes);
            movies.add(movie);
        }
        
        return movies;
    }
    
    public ArrayList<Rater> loadRaters (String filename){
        ArrayList<Rater> ratersList= new ArrayList<Rater>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        
        for(CSVRecord rec : parser){
            double value = Double.parseDouble(rec.get(2));
            Rater currRater = new EfficientRater(rec.get(0));
            // rater id check
            boolean hasRater = false;
            for (int i = 0; i < ratersList.size(); i++){ 
                hasRater = 
                        rec.get(0).equals(ratersList.get(i).getID());
            }
            int index = ratersList.indexOf(currRater);
            //System.out.println(ratersList.indexOf(currRater));
            if (!hasRater){
                currRater.addRating(rec.get(1), value);
                ratersList.add(currRater);
                
            }
            else{
                int lastindex = ratersList.size()-1;
                Rater prevRater = ratersList.get(lastindex);
                prevRater.addRating(rec.get(1), value);
                ratersList.set(lastindex, prevRater);
            }
        }
        return ratersList;
    }
    
    // helper methods for testLoadMovies 
    
    public int getGenreCount (ArrayList<Movie> movies, String genreType){
        int genreCount = 0;
        for (int i = 0; i < movies.size(); i++) {
            if(movies.get(i).getGenres().contains(genreType)){
                genreCount++;
            }
        }
        return genreCount;
    }
    
    public int getLengthCount (ArrayList<Movie> movies, int minuteGate){
        int lengthCount = 0;
        for (int i = 0; i < movies.size(); i++) {
            if(movies.get(i).getMinutes() > minuteGate){
                lengthCount++;
            }
        }
        return lengthCount;
    }
    
    public void getDirectorsCount(ArrayList<Movie> movies){
        directorNames= new ArrayList<String>();
        directorWorks = new ArrayList<Integer>();
        
        for (int i = 0; i < movies.size(); i++) {
            String[] directors = movies.get(i).getDirector().split(", ");
            for (int j=0; j<directors.length; j++){
                String director = directors[j];
                int index = directorNames.indexOf(director);
                if (index == -1){
                    directorNames.add(director);
                    directorWorks.add(1);
                }
                else {
                    int works = directorWorks.get(index);
                    directorWorks.set(index,works+1);
                }
            }
        }
    }
    
    public int getMaxWorks(){
        int max = directorWorks.get(0);
        int maxIndex = 0;
        for(int k=0; k < directorWorks.size(); k++){
            if (directorWorks.get(k) > max){
                max = directorWorks.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    
    // helper methods for testLoadRaters 
    
    public int getRaterCount(ArrayList<Rater> raters, String raterID){
        int raterCount = 0;
        for (int i = 0; i < raters.size(); i++) {
            Rater currRater = raters.get(i);
            String currID = currRater.getID();
            if(currID.equals(raterID)){
                raterCount = currRater.numRatings();
            }
        }
        return raterCount;
    }
    
    public ArrayList<String> getGreatestIDs(
                                            ArrayList<Rater> raters){
        int greatestNumberOfRatings = 0;
        ArrayList<String> graterIDs = new ArrayList<String>();
        for (int i = 0; i < raters.size(); i++) {
            Rater currRater = raters.get(i);
            String currID = currRater.getID();
            int numRatings = currRater.numRatings();
            if(greatestNumberOfRatings<numRatings){
                greatestNumberOfRatings = numRatings;
                graterIDs.clear();
                graterIDs.add(currID);
            } 
            else if(greatestNumberOfRatings == numRatings){
                graterIDs.add(currID);
            }
        }
        
        System.out.println("\nMaximum number of raitings is: " 
                            + greatestNumberOfRatings);
        return graterIDs;
    }
    
    public int getMovieRatingCount(ArrayList<Rater> raters,
                                                    String movieID){
        int ratingCount = 0;
        for (int i = 0; i < raters.size(); i++) {
            Rater currRater = raters.get(i);
            if(currRater.hasRating(movieID)){
                ratingCount++;
            }
        }
        return ratingCount;
    }
    
    public int getRatedMoviesCount(ArrayList<Rater> raters){
        ArrayList<String> ratedMovies = new ArrayList<String>();
        for (int i = 0; i < raters.size(); i++) {
            Rater currRater = raters.get(i);
            String currID = currRater.getID();
            ArrayList<String> itemsRated = currRater.getItemsRated();
            for (int j = 0; j < itemsRated.size(); j++) {
                String currMovieID = itemsRated.get(j).toString();
                boolean isMovieNew = !ratedMovies.contains(currMovieID);
                if(isMovieNew){
                    ratedMovies.add(currMovieID);
                }
            }
        }
        int ratedMoviesCount = ratedMovies.size();
        return ratedMoviesCount;
    }
    
    // Tester methods
    
    public void testLoadMovies(){
        //String filename = "ratedmovies_short.csv";
        String filename = "ratedmoviesfull.csv";
        //String filename = "ratedmovies_directors.csv";
        ArrayList<Movie> movies = loadMovies("data/" + filename);
        
        System.out.println("The number of movies is: " 
                                                    + movies.size());
        /* Print out all movies
        for (int i = 0; i < movies.size(); i++) {
            System.out.println(movies.get(i));
        }
        */
        // Determine how many movies include the Comedy genre.
        String genreType = "Comedy";
        int genreCount = getGenreCount(movies, genreType);
        System.out.println("The number of movies of genre: " +
                                genreType + " is: " + genreCount );
        // Determine how many movies are greater 
        // than X minutes in length
        int minuteGate = 150;
        int lengthCount = getLengthCount(movies, minuteGate);
        System.out.println("The number of movies of " +
                                "length greater than " + minuteGate 
                                + " minutes is: " + lengthCount );
                                
        // Determin a director with biggest amount of works 
        // and what is the count
        getDirectorsCount(movies);
        int index = getMaxWorks();
        System.out.println("The director with greatests amount of works is: "
                            + directorNames.get(index) 
                            + "\nand the number of his works is: "
                            + directorWorks.get(index));
    }
    
    public void testLoadRaters(){
        //String filename = "ratings_short.csv";
        String filename = "ratings.csv";
        //String filename = "ratings_mix.csv";
        ArrayList<Rater> raters = loadRaters("data/" + filename);
        
        System.out.println("The number of raters is: " 
                                                    + raters.size());
                                                    
        // Print out all raters
        /*
        for (int i = 0; i < raters.size(); i++) {
            Rater currRater = raters.get(i);
            String currID = currRater.getID();
            int numRatings = currRater.numRatings();
            
            System.out.println("Rater: " + currID + " rated "
                                + numRatings + " movies");
            ArrayList<String> itemsRated = currRater.getItemsRated();
            for (int j = 0; j < itemsRated.size(); j++) {
                String currMovieID = itemsRated.get(j).toString();
                double currMovieValue = currRater.getRating(currMovieID);
                System.out.println("Movie ID: " + currMovieID 
                                    + " \tRating: "+ currMovieValue);
            }
        }
        */
        
        // Determine how many movies were rated by a specific rater
        String raterCountID = "193";
        int raterCount = getRaterCount(raters, raterCountID);
        System.out.println("\nRater with ID: " + raterCountID 
                            + " rated " + raterCount
                            + " movies");
        
                            
        // Determine raters with greatest amount of ratings and their IDs                    
        
        ArrayList<String> greatestIDs = getGreatestIDs(raters);
        System.out.println("Greatest raters are:");
        for (int i = 0; i < greatestIDs.size(); i++) {
            System.out.println("Rater ID: "+ greatestIDs.get(i));
        }
        
        // Determine count of ratings a movie has
        String movieID = "1798709";
        int movieRatingCount = getMovieRatingCount(raters, movieID);
        System.out.println("\nMovie with ID: " + movieID 
                            + " was rated by " + movieRatingCount 
                            + " raters");
                            
        // Determine how many movies were rated
        int ratedMoviesCount = getRatedMoviesCount(raters);
        System.out.println("\nThere are: " + ratedMoviesCount 
                            + " rated movies in the database");
    }
    
    public void testSecondRatings(){
        String ratingsfile = "ratings.csv";
        ArrayList<Rater> raters = loadRaters("data/" + ratingsfile);
        System.out.println("The number of raters is: " 
                                                    + raters.size());
        
        String moviefile = "ratedmoviesfull.csv";
        ArrayList<Movie> movies = loadMovies("data/" + moviefile);
        System.out.println("The number of movies is: " 
                                                    + movies.size());
    }
}
