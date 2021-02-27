
/**
 * Write a description of DirectorsFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorsFilter implements Filter{
    private String myDirectors;
    
    public DirectorsFilter(String directors){
        myDirectors = directors;
    }
    
    @Override
    public boolean satisfies(String id) {
        String[] directorList = myDirectors.split(",");
        String currDirectors = MovieDatabase.getDirector(id);
        for (String director : directorList) {
            if (currDirectors.contains(director)){
                return true;
            }
        }
        return false;
    }
}
