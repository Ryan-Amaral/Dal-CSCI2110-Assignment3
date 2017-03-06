import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class SimpleTwoDimDictionaryTest {

    @Test
    public void testInsert() {
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary();
        simple.insert(new Point(5,2));
        assertEquals(1, simple.size());
        simple.insert(new Point(1,5));
        assertEquals(2, simple.size());
        assertEquals(2, simple.size());
    }

    @Test
    public void testCount() {
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary();
        simple.insert(new Point(5, 5));
        simple.insert(new Point(12, 5));
        simple.insert(new Point(5, 12));
        simple.insert(new Point(0, 0));
        simple.insert(new Point(0, 10));
        simple.insert(new Point(10, 0));
        simple.insert(new Point(10, 10));
        simple.insert(new Point(5, 10));
        simple.insert(new Point(10, 5));
        simple.insert(new Point(0, 5));
        simple.insert(new Point(5, 0));
        // all points and rectangle from rectangle test
        assertEquals(4, simple.count(new Rectangle(0,0,10,10)));
    }

    @Test
    public void testQuery() {
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary();
        Point point1 = new Point(5, 5); 
        Point point2 = new Point(12, 5); 
        Point point3 = new Point(5, 12); 
        Point point4 = new Point(0, 0); 
        Point point5 = new Point(0, 10);
        Point point6 = new Point(10, 0); 
        Point point7 = new Point(10, 10); 
        Point point8 = new Point(5, 10); 
        Point point9 = new Point(10, 5); 
        Point point10 = new Point(0, 5); 
        Point point11 = new Point(5, 0); 
        
        simple.insert(point1);
        simple.insert(point2);
        simple.insert(point3);
        simple.insert(point4);
        simple.insert(point5);
        simple.insert(point6);
        simple.insert(point7);
        simple.insert(point8);
        simple.insert(point9);
        simple.insert(point10);
        simple.insert(point11);
        // all points and rectangle from rectangle test
        ArrayList<Point> list = new ArrayList<Point>();
        simple.query(list, new Rectangle(0,0,10,10));
        
        assertEquals(4, list.size());
        assertTrue(list.contains(point1));
        assertTrue(list.contains(point4));
        assertTrue(list.contains(point10));
        assertTrue(list.contains(point11));
        assertFalse(list.contains(point8));
    }

    @Test
    public void testSize() {
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary();
        assertEquals(0, simple.size());
        simple.insert(new Point(5,2));
        assertEquals(1, simple.size());
        simple.insert(new Point(1,5));
        assertEquals(2, simple.size());
        assertEquals(2, simple.size());
    }
}
