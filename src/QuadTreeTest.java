import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

public class QuadTreeTest {

    @Test
    public void testInsert() {
        QuadTree tree = new QuadTree(new Rectangle(0,0,10,10));
        tree.insert(new Point(5,2));
        assertEquals(1, tree.size());
        tree.insert(new Point(1,5));
        assertEquals(2, tree.size());
        assertEquals(2, tree.size());
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
        // all points and rectangle verified from rectangle test
        assertEquals(4, tree.count(new Rectangle(0,0,10,10)));
    }

    @Test
    public void testQuery() {
        QuadTree tree = new QuadTree(new Rectangle(0,0,10,10));
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
        // all points and rectangle verified from rectangle test
        ArrayList<Point> list = new ArrayList<Point>();
        tree.query(list, new Rectangle(0,0,10,10));
        
        assertEquals(4, list.size());
        assertTrue(list.contains(point1));
        assertTrue(list.contains(point4));
        assertTrue(list.contains(point10));
        assertTrue(list.contains(point11));
        assertFalse(list.contains(point8));
    }

    @Test
    public void testSize() {
        QuadTree tree = new QuadTree(new Rectangle(0,0,10,10));
        assertEquals(0, tree.size());
        tree.insert(new Point(5,2));
        assertEquals(1, tree.size());
        tree.insert(new Point(1,5));
        assertEquals(2, tree.size());
        assertEquals(2, tree.size());
        tree.insert(new Point(15,2));
        assertEquals(2, tree.size());
    }

    @Test
    public void testDisplay(){
        QuadTree tree = new QuadTree(new Rectangle(0,0,10,10));
        tree.insert(new Point(5, 5));
        tree.insert(new Point(0, 0));
        tree.insert(new Point(0, 5));
        tree.insert(new Point(5, 0));
        tree.insert(new Point(9, 2));
        tree.insert(new Point(3, 6));
        tree.insert(new Point(8, 3));
        tree.insert(new Point(5, 4));
        tree.insert(new Point(5, 8));
        tree.insert(new Point(8, 9));
        tree.insert(new Point(2, 2));
        tree.insert(new Point(1, 1));
        tree.insert(new Point(0, 4));
        tree.insert(new Point(6, 6));
        tree.insert(new Point(8, 7));
        tree.insert(new Point(9, 4));
        tree.insert(new Point(4, 7));
        tree.insert(new Point(2, 3));
        
        tree.display();
    }
    
    @Test
    public void saveDotFile(){
        QuadTree tree = new QuadTree(new Rectangle(0,0,10,10));
        tree.insert(new Point(5, 5));
        tree.insert(new Point(0, 0));
        tree.insert(new Point(0, 5));
        tree.insert(new Point(5, 0));
        tree.insert(new Point(9, 2));
        tree.insert(new Point(3, 6));
        tree.insert(new Point(8, 3));
        tree.insert(new Point(5, 4));
        tree.insert(new Point(5, 8));
        tree.insert(new Point(8, 9));
        tree.insert(new Point(2, 2));
        tree.insert(new Point(1, 1));
        tree.insert(new Point(0, 4));
        tree.insert(new Point(6, 6));
        tree.insert(new Point(8, 7));
        tree.insert(new Point(9, 4));
        tree.insert(new Point(4, 7));
        tree.insert(new Point(2, 3));
        
        try {
            tree.saveDotFile("dotfile");
        } catch (IOException e) {
            fail("Could not save file");
        }
    }
}
