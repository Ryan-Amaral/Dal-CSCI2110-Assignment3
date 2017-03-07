import java.util.ArrayList;

/**
 * A data structure that stores 2 dimensional points, and has
 * functionality to check for points within certain bounds.
 * 
 * Assumptions/Restrictions: I assume that it is OK to do System.out.print
 * in this file because that is what the instructions seem to say.
 * 
 * Noteworthy Features: 
 * 
 * @author Ryan Amaral
 *
 */
public interface TwoDimDictionary {
    
    /**
     * Insert a point into the data structure. Only inserts if it is 
     * within bounds.
     * @param point The point to insert.
     */
    public void insert(Point point);
    
    /**
     * @param bounds The rectangle to look for points in.
     * @return The amount of points bounded by b.
     */
    public int count(Rectangle bounds);
    
    /**
     * Add all points in the data structure that are bounded by rectangle
     * b to the array list s.
     * @param set The list to add points to.
     * @param bounds The rectangle to look for points in.
     */
    public void query(ArrayList<Point> set, Rectangle bounds);
    
    /**
     * @return The amount of points stored in the data structure.
     */
    public int size();
    
    /**
     * Prints out the data structure using System.out.
     */
    public void display();
}
