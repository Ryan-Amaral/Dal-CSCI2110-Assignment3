
public class QuadTreePlayground {

    /**
     * This is a simple program that creates a quad tree with many points
     * as well as a rectangle, and tries to find the most points that can
     * be inside that rectangle by varying position.
     * @param args
     */
    public static void main(String[] args) {
        maxPointsFinder();
    }

    /**
     * This is a simple program that creates a quad tree with many points
     * as well as a rectangle, and tries to find the most points that can
     * be inside that rectangle by varying position.
     */
    public static void maxPointsFinder(){
        final int boundsWidthHeight = 20; // the width and height of qtree
        QuadTree tree = 
                new QuadTree(new Rectangle(
                        new Point(0,0), boundsWidthHeight, boundsWidthHeight));
        final int finderWidthHeight = 10; // the width/height of the finder square
        Rectangle finderRect = new Rectangle(
                new Point(0,0), finderWidthHeight, finderWidthHeight);
        
        // put random points in tree
        final int points = 1000; 
        Point point;
        for(int i = 0; i < points; i++){
            // point in appropriate range
            point = new Point(
                    (int)(Math.random() * boundsWidthHeight), 
                    (int)(Math.random() * boundsWidthHeight));
            tree.insert(point);
        }
        
        int highest = 0; // highest amount found in finder rectangle
        int least = points; // lowest amount found in finder rectangle
        double average = 0; // the average amount of points found
        int moves = 0;
        // sweep finder from top to bottom left to right
        while(finderRect.bottom() < boundsWidthHeight){
            finderRect.moveTo(0, finderRect.top()); // electric slide to left
            while(finderRect.right() < boundsWidthHeight){
                int curPoints = tree.count(finderRect);
                if(curPoints > highest)
                    highest = curPoints;
                if(curPoints < least)
                    least = curPoints;
                average += curPoints; // running total to be divided after
                finderRect.move(1, 0); // move right 1
                moves++;
            }
            finderRect.move(0, 1); // move down 1
        }
        average /= moves;
        
        System.out.println("The most points the found by the finder at once "
                + "is: " + highest + ".");
        System.out.println("The least points the found by the finder at once "
                + "is: " + least + ".");
        System.out.println("The average points the found by the finder at once "
                + "is: " + average + ".");
    }
}
