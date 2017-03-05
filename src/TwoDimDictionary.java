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
     * Insert a point into the data structure.
     * @param p The point to insert.
     */
    public void insert(Point p);
    
    /**
     * @param b The rectangle to look for points in.
     * @return The amount of points bounded by b.
     */
    public int count(Rectangle b);
    
    /**
     * Add all points in the data structure that are bounded by rectangle
     * b to the array list s.
     * @param s The list to add points to.
     * @param b The rectangle to look for points in.
     */
    public void query(ArrayList<Point> s, Rectangle b);
    
    /**
     * @return The amount of points stored in the data structure.
     */
    public int size();
    
    /**
     * Prints out the data structure using System.out.
     */
    public void display();
}
