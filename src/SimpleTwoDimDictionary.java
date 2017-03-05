import java.util.ArrayList;
import java.util.Iterator;

/**
 * An array list based implementation of my TwoDimDictionary interface.
 * 
 * Assumptions/Restrictions: 
 * 
 * Noteworthy Features: 
 * 
 * @author Ryan Amaral
 *
 */
public class SimpleTwoDimDictionary implements TwoDimDictionary {

    ArrayList<Point> pointList;
    
    public SimpleTwoDimDictionary(){
        pointList = new ArrayList<Point>();
    }
    
    @Override
    public void insert(Point p) {
        pointList.add(p);
    }

    @Override
    public int count(Rectangle b) {
        Iterator<Point> iterator = pointList.iterator();
        int count = 0; // the amount of bounded points
        
        while(iterator.hasNext()){
            if(b.contains(iterator.next()))
                count++;
        }
        
        return count;
    }

    @Override
    public void query(ArrayList<Point> s, Rectangle b) {
        Iterator<Point> iterator = pointList.iterator();
        while(iterator.hasNext()){
            Point point = iterator.next();
            if(b.contains(point))
                s.add(point);
        }
    }

    @Override
    public int size() {
        return pointList.size();
    }

    @Override
    public void display() {
        Iterator<Point> iterator = pointList.iterator();
        System.out.print("{");
        while(iterator.hasNext()){
            System.out.print(iterator.next().toString());
            if(iterator.hasNext())
                System.out.print(", ");
        }
        System.out.print("}");
    }

}
