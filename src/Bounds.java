
/**
 * A struct that contains bounds for a rectangle.
 * 
 * Assumptions/Restrictions: 
 * 
 * Noteworthy Features:
 * 
 * @author Ryan Amaral
 *
 */
public class Bounds {
    public int Upper;
    public int Left;
    public int Right;
    public int Lower;
    
    /**
     * Crates a bounds object with the specified bounds.
     * @param upper The upper bound.
     * @param left The left bound.
     * @param right The right bound.
     * @param lower The lower bound.
     */
    public Bounds(int upper, int left, int right, int lower){
        Upper = upper;
        Left = left;
        Right = right;
        Lower = lower;
    }
}
