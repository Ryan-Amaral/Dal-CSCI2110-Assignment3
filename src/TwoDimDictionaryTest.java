import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Test QuadTree and SimpleTwoDimDictionary together to ensure that they
 * both get the same results.
 * 
 * Assumptions/Restrictions: 
 * 
 * Noteworthy Features:
 * 
 * @author Ryan Amaral
 *
 */
public class TwoDimDictionaryTest {
    
    /**
     * Test a bunch of random rectangles and points.
     */
    @Test
    public void bigTest(){
        // for bounds size
        // exp from 5 to 10 so 2^exp is from 32 to 1024
        int exponent = (int) (Math.random() * 6) + 5;
        int upperBound = (int) Math.pow(2, exponent);
        QuadTree tree = new QuadTree(
                new Rectangle(1, 1, upperBound, upperBound));
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary(
                new Rectangle(1, 1, upperBound, upperBound));
        
        // insert 100 random points
        int points = 100;
        Point point;
        for(int i = 0; i < points; i++){
            // point in appropriate range
            point = new Point(
                    (int)(Math.random() * upperBound) + 1, 
                    (int)(Math.random() * upperBound) + 1);
            tree.insert(point);
            simple.insert(point);
        }
        
        // size should be equal
        assertEquals(tree.size(), simple.size());
        
        // try out 10 different test rectangle queries/counts
        int queries = 100;
        // hold counts
        int treeCount;
        int simpleCount;
        // hold query sets
        ArrayList<Point> treeSet = new ArrayList<Point>();
        ArrayList<Point> simpleSet = new ArrayList<Point>();
        for(int i = 0; i < queries; i++){
            
        }
    }

    @Test
    public void testInsert() {
        QuadTree tree = new QuadTree(new Rectangle(0,0,10,10));
        SimpleTwoDimDictionary simple = 
                new SimpleTwoDimDictionary(new Rectangle(0,0,10,10));
        
        tree.insert(new Point(5,2));
        simple.insert(new Point(5,2));
        assertEquals(tree.size(), simple.size());
        tree.insert(new Point(1,5));
        simple.insert(new Point(1,5));
        assertEquals(tree.size(), simple.size());
        assertEquals(tree.size(), simple.size());
    }

    @Test
    public void testCount() {
        QuadTree tree = new QuadTree(new Rectangle(0,0,10,10));
        tree.insert(new Point(5, 5));
        tree.insert(new Point(12, 5));
        tree.insert(new Point(5, 12));
        tree.insert(new Point(0, 0));
        tree.insert(new Point(0, 10));
        tree.insert(new Point(10, 0));
        tree.insert(new Point(10, 10));
        tree.insert(new Point(5, 10));
        tree.insert(new Point(10, 5));
        tree.insert(new Point(0, 5));
        tree.insert(new Point(5, 0));
        
        SimpleTwoDimDictionary simple = 
                new SimpleTwoDimDictionary(new Rectangle(0,0,10,10));
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
        // all points in both are the same

        assertEquals(tree.count(new Rectangle(0,0,10,10)), simple.count(new Rectangle(0,0,10,10)));
        assertEquals(tree.count(new Rectangle(3,-1,11,6)), simple.count(new Rectangle(3,-1,11,6)));
    }

    @Test
    public void testQuery() {
        // test if all points obtained from query are in the results of the
        // other implementation
        
        QuadTree tree = new QuadTree(new Rectangle(0,0,10,10));
        SimpleTwoDimDictionary simple = 
                new SimpleTwoDimDictionary(new Rectangle(0,0,10,10));
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
        
        tree.insert(point1);
        tree.insert(point2);
        tree.insert(point3);
        tree.insert(point4);
        tree.insert(point5);
        tree.insert(point6);
        tree.insert(point7);
        tree.insert(point8);
        tree.insert(point9);
        tree.insert(point10);
        tree.insert(point11);
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
        
        // all points and rectangle verified from rectangle test
        ArrayList<Point> listTree = new ArrayList<Point>();
        tree.query(listTree, new Rectangle(0,0,10,10));
        ArrayList<Point> listSimple = new ArrayList<Point>();
        simple.query(listSimple, new Rectangle(0,0,10,10));
        
        // see if have same amount of elements
        assertEquals(listTree.size(), listSimple.size());
        // make sure same elements are present
        for(int i = 0; i < listTree.size(); i++){
            assertTrue(listTree.contains(listSimple.get(i)));
        }
    }
}
