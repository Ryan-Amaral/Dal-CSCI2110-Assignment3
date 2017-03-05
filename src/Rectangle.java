
/**
 * A rectangle class containing functions having to do with relationships
 * to other rectangles and points.
 * 
 * Assumptions/Restrictions: 
 * - The top and the left of a rectangle will be inclusive with respect to 
 * containing a point, and the bottom and right will be exclusive.
 * 
 * Noteworthy Features:
 * 
 * @author Ryan Amaral
 *
 */
public class Rectangle {
    private Point topLeft; // the top left point of the rectangle
    private int width;
    private int height;
    
    // the bounds on each side of the rectangle
    private Bounds bounds;
    
    
    /**
     * @return the bounds
     */
    public Bounds getBounds() {
        return bounds;
    }

    /**
     * Creates a default 1x1 rectangle with top left point at (0,0).
     */
    public Rectangle(){
        topLeft = new Point();
        width = 1;
        height = 1;
        
        bounds.Upper = 0;
        bounds.Left = 0;
        bounds.Right = 1;
        bounds.Lower = 1;
    }
    
    /**
     * Creates a new rectangle with specified top left corner position, 
     * width, and height.
     * @param topLeftPoint The point that the top left corner of the 
     * rectangle lies on.
     * @param width The width of the rectangle.
     * @param height The height of the rectangle.
     */
    public Rectangle(Point topLeftPoint, int width, int height){
        topLeft = topLeftPoint;
        this.width = width;
        this.height = height;
        
        bounds.Upper = topLeft.Y;
        bounds.Left = topLeft.X;
        bounds.Right = bounds.Left + width;
        bounds.Lower = bounds.Upper - height;
    }
    
    /**
     * Creates a new rectangle with the specified bounds.
     * @param upper The upper bound.
     * @param left The left bound.
     * @param right The right bound.
     * @param lower The lower bound.
     */
    public Rectangle(int upper, int left, int right, int lower){
        topLeft = new Point(left, upper);
        this.width = right - left;
        this.height = upper - lower;
        
        bounds.Upper = upper;
        bounds.Left = lower;
        bounds.Right = right;
        bounds.Lower = lower;
    }
    
    /**
     * @param point The point to check for containment in this rectangle.
     * @return Whether the rectangle contains the point.
     */
    public boolean contains(Point point){
        // remember only left and upper are inclusive
        if(point.X >= bounds.Left && point.X < bounds.Right &&
                point.Y <= bounds.Upper && point.Y > bounds.Lower)
            return true; // contained
        return false; // not contained
    }
    
    /**
     * @param rect The rectangle to compare intersection with.
     * @return Whether this rectangle intersects with rect.
     */
    public boolean intersects(Rectangle other){
        // This is incorrect better fix this junk
        if(((this.bounds.Left < other.bounds.Left && 
                this.bounds.Right > other.bounds.Left) || 
                (this.bounds.Left < other.bounds.Right && 
                this.bounds.Right > other.bounds.Right)) 
                && 
                (this.bounds.Lower < other.bounds.Upper && 
                this.bounds.Upper > other.bounds.Upper) ||
                (this.bounds.Lower < other.bounds.Lower && 
                this.bounds.Upper > other.bounds.Lower))
            return true; // intersect
        return false; // no intersect
    }
}
