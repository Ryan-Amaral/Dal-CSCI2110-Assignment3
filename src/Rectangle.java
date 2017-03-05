
/**
 * A rectangle class containing functions having to do with relationships
 * to other rectangles and points.
 * 
 * Assumptions/Restrictions: 
 * - The top and the left of a rectangle will be inclusive with respect to 
 * containing a point, and the bottom and right will be exclusive.
 * - height will increase downwards.
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
    
    public static enum Quadrant{NorthEast, NorthWest, SouthWest, SouthEast, None};

    /**
     * Creates a default 1x1 rectangle with top left point at (0,0).
     */
    public Rectangle(){
        topLeft = new Point();
        width = 1;
        height = 1;
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
    }
    
    /**
     * Creates a new rectangle with the specified bounds.
     * @param top The top bound.
     * @param left The left bound.
     * @param right The right bound.
     * @param bottom The bottom bound.
     */
    public Rectangle(int top, int left, int right, int bottom){
        topLeft = new Point(left, top);
        this.width = right - left;
        this.height = bottom - top;
    }
    
    /**
     * @return The left bound of the rectangle.
     */
    public int left(){
        return topLeft.X;
    }
    
    /**
     * @return The right bound of the rectangle.
     */
    public int right(){
        return topLeft.X + width;
    }

    /**
     * @return The top bound of the rectangle.
     */
    public int top(){
        return topLeft.Y;
    }

    /**
     * @return The bottom bound of the rectangle.
     */
    public int bottom(){
        return topLeft.Y + height;
    }
    
    /**
     * @param point The point to check for containment in this rectangle.
     * @return Whether the rectangle contains the point.
     */
    public boolean contains(Point point){
        // remember only left and upper are inclusive
        if(point.X >= left() && point.X < right() &&
                point.Y >= top() && point.Y < bottom())
            return true; // contained
        return false; // not contained
    }
    
    /**
     * @param rect The rectangle to compare intersection with.
     * @return Whether this rectangle intersects with rect.
     */
    public boolean intersects(Rectangle other){
        // no bound on this is beyond the opposite bound of other
        if(!(this.left() > other.right() ||
                this.right() < other.left() || 
                this.top() > other.bottom() ||
                this.bottom() < other.top()))
            return true; // intersect
        return false; // no intersect
    }
    
    /**
     * @param other The other rectangle to check for containment within.
     * @return Whether this rectangle is covered by / fully contained 
     * within other.
     */
    public boolean coveredBy(Rectangle other){
        // all bounds of this within bounds of other
        if(this.top() > other.top() &&
                this.left() > other.left() &&
                this.right() < other.right() &&
                this.bottom() < other.bottom())
            return true; // contained
        return false; // not contained
    }
    
    /**
     * @param point The point to check for quadrant position on.
     * @return What quadrant in the rectangle point lies within.
     */
    public Quadrant quadrant(Point point){
        // quadrants are inclusive on top and left
        // quadrants are exclusive on bottom and right
        
        // in north
        if(point.Y >= top() && 
                point.Y < (top() + height/2)){
            // in west
            if(point.X >= left() &&
                    point.X < (left() + width/2)){
                return Quadrant.NorthWest;
            }
            // in east
            else if(point.X >= (left() + width/2) &&
                    point.X < right()){
                return Quadrant.NorthEast;
            }
            // none
            else{
                return Quadrant.None;
            }
        }
        // in south
        else if(point.Y >= (top() + height/2) && 
                point.Y < bottom()){
            // in west
            if(point.X >= left() &&
                    point.X < (left() + width/2)){
                return Quadrant.SouthWest;
            }
            // in east
            else if(point.X >= (left() + width/2) &&
                    point.X < right()){
                return Quadrant.SouthEast;
            }
            // none
            else{
                return Quadrant.None;
            }
        }
        // none
        else{
            return Quadrant.None;
        }
    }
}
