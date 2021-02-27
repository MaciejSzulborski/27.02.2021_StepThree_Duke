
/**
 * Write a description of MinuteFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter{
    private int myMinMinutes;
    private int myMaxMinutes;
    
    public MinutesFilter(int minMinutes, int maxMinutes) {
        myMinMinutes = minMinutes;
        myMaxMinutes = maxMinutes;
    }
	
    @Override
    public boolean satisfies(String id) {
        int minutes = MovieDatabase.getMinutes(id);
	return minutes >= myMinMinutes && minutes <= myMaxMinutes;
    }
}
