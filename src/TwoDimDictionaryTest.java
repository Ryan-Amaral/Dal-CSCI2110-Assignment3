import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import org.junit.Test;

// my analysis
/*
 * Insert: Quad tree take about double the time to insert elements
 * as the simple implementation does for 2000 calls.
 * Count: Quad tree consistently  performs this operation about
 * 2.5 times faster than the simple implementation for 10000 calls.
 * Overall: Quad tree is better for large numbers to have useful
 * time for searches compared the simple implementation.
 * */

/**
 * Test QuadTree and SimpleTwoDimDictionary together to ensure that they
 * both get the same results.
 * 
 * Assumptions/Restrictions: Quadtree does not accept duplicates, though
 * simple implementation does.
 * 
 * Noteworthy Features: Printing to a dot file
 * 
 * @author Ryan Amaral
 *
 */
public class TwoDimDictionaryTest {
    
    /**
     * Test a bunch of random rectangles and points and make sure both 
     * implementations give the same results.
     */
    @Test
    public void bigTest(){
        // for bounds size
        // exp from 9 to 12 so 2^exp is from 512 to 4096
        int exponent = (int) (Math.random() * 4) + 9;
        int upperBound = (int) Math.pow(2, exponent);
        QuadTree tree = new QuadTree(
                new Rectangle(1, 1, upperBound, upperBound));
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary(
                new Rectangle(1, 1, upperBound, upperBound));
        
        // insert 10000 random points
        int points = 10000;
        Point point;
        for(int i = 0; i < points; i++){
            // point in appropriate range
            point = new Point(
                    (int)(Math.random() * upperBound) + 1, 
                    (int)(Math.random() * upperBound) + 1);
            tree.insert(point);
            simple.insert(point);
        }
        
        // sizes no longer equal because tree does not accept duplicates
        //assertEquals(tree.size(), simple.size());
        
        // try out 10000 different test rectangle queries/counts
        int queries = 10000;
        // query sets
        ArrayList<Point> treeSet;
        ArrayList<Point> simpleSet;
        Rectangle rect;
        for(int i = 0; i < queries; i++){
            // reset sets
            treeSet = new ArrayList<Point>();
            simpleSet = new ArrayList<Point>();
            
            // create random query rectangle
            rect = new Rectangle(1,1,
                    (int)(Math.random() * upperBound) + 1,
                    (int)(Math.random() * upperBound) + 1);
            
            // may not be equal as tree does not take duplicates
            // assertEquals(tree.count(rect), simple.count(rect));
            
            // do queries
            tree.query(treeSet, rect);
            simple.query(simpleSet, rect);
            
            // make sure same elements are present
            for(int j = 0; j < treeSet.size(); j++){
                // this one can still be equal as they contain the same,
                // even if not same amount
                assertTrue(simpleSet.contains(treeSet.get(j)));
            }
        }
        
        // display the structures
        System.out.print("Simple implementation (Array List): ");
        simple.display();
        System.out.print("\nQuad Tree implementation: \n");
        tree.display();
        // also get file for tree
        try {
            tree.saveDotFile("BigTestResults");
        } catch (IOException e) {
            System.out.println("Fail");
        }
    }
    
    /**
     * Compare the time used between the two implementation for insert
     * and count.
     */
    @Test
    public void timeCompare(){
        int upperBound = 4096;
        QuadTree tree = new QuadTree(
                new Rectangle(1, 1, upperBound, upperBound));
        SimpleTwoDimDictionary simple = new SimpleTwoDimDictionary(
                new Rectangle(1, 1, upperBound, upperBound));
        
        // for timing
        long timeStart;
        long timeEnd;
        
        // insert 10000 random points
        // any larger frequently causes stack overflow
        // on my laptop on quadtree
        int points = 10000; 
        Point point;
        
        // first time quadtree
        timeStart = System.nanoTime();
        for(int i = 0; i < points; i++){
            // point in appropriate range
            point = new Point(
                    (int)(Math.random() * upperBound) + 1, 
                    (int)(Math.random() * upperBound) + 1);
            tree.insert(point);
        }
        timeEnd = System.nanoTime();
        
        System.out.println("Adding " + points + " points to QuadTree took " + (timeEnd - timeStart)/1000000 + " nanoseconds.");
        
        // time simple
        timeStart = System.nanoTime();
        for(int i = 0; i < points; i++){
            // point in appropriate range
            point = new Point(
                    (int)(Math.random() * upperBound) + 1, 
                    (int)(Math.random() * upperBound) + 1);
            simple.insert(point);
        }
        timeEnd = System.nanoTime();
        
        System.out.println("Adding " + points + " points to Simple took " + (timeEnd - timeStart)/1000000 + " nanoseconds.");
        
        
        // now test time to count
        
        // try out 1000000 different test rectangle queries/counts
        int counts = 10000;
        Rectangle rect;
        
        // count tree
        timeStart = System.nanoTime();
        for(int i = 0; i < counts; i++){
            // create random query rectangle
            rect = new Rectangle(
                    ((int)(Math.random() * upperBound) + 1) / 2,
                    ((int)(Math.random() * upperBound) + 1) / 2,
                    (int)(Math.random() * upperBound/2) + upperBound/2,
                    (int)(Math.random() * upperBound/2) + upperBound/2);
            
            tree.count(rect);
        }
        timeEnd = System.nanoTime();
        
        System.out.println("Calling count " + counts + " times on QuadTree takes " 
                + (timeEnd - timeStart)/1000000 + " nanoseconds.");
        
        // now count simple 
        timeStart = System.nanoTime();
        for(int i = 0; i < counts; i++){
            // create random query rectangle
            rect = new Rectangle(
                    ((int)(Math.random() * upperBound) + 1) / 2,
                    ((int)(Math.random() * upperBound) + 1) / 2,
                    (int)(Math.random() * upperBound/2) + upperBound/2,
                    (int)(Math.random() * upperBound/2) + upperBound/2);
            
            simple.count(rect);
        }
        timeEnd = System.nanoTime();
        
        System.out.println("Calling count " + counts + " times on Simple takes " 
                + (timeEnd - timeStart)/1000000 + " nanoseconds.");
        
        /*
         * Insert: Quad tree take about double the time to insert elements
         * as the simple implementation does.
         * Count: Quad tree consistently  performs this operation about
         * 2.5 times faster than the simple implementation.
         * Overall: Quad tree is better for large numbers to have useful
         * time for searches compared the simple implementation.
         * */
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
        // double test
        assertEquals(tree.size(), simple.size());
        assertEquals(tree.size(), simple.size());
        
        // insert a duplicate point
        tree.insert(new Point(5,2));
        simple.insert(new Point(5,2));
        // tree size should be 1 less as the tree does not accept duplicates
        assertEquals(tree.size()+1, simple.size());
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
