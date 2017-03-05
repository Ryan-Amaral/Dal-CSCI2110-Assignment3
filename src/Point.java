
/**
 * A basic 2D point class.
 * 
 * Assumptions/Restrictions: Only number as big as int will be needed.
 * 
 * Noteworthy Features:
 * 
 * @author Ryan Amaral
 *
 */
public class Point {
    public int X;
    public int Y;
    
    /**
     * Creates a new Point with coordinates (0,0).
     */
    public Point(){
        X = 0;
        Y = 0;
    }
    
    /**
     * Creates a new Point with specified coordinates.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Point(int x, int y){
        X = x;
        Y = y;
    }
}
