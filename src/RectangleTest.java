import static org.junit.Assert.*;

import org.junit.Test;

public class RectangleTest {

    @Test
    public void testRectangle() {
        Rectangle rect = new Rectangle();
        
        assertEquals("top must be 0", 0, rect.top());
        assertEquals("left must be 0", 0, rect.left());
        assertEquals("right must be 1", 1, rect.right());
        assertEquals("bottom must be 1", 1, rect.bottom());
    }

    @Test
    public void testRectanglePointIntInt() {
        Rectangle rect = new Rectangle(new Point(1,1), 2, 2);
        
        assertEquals("top must be 1", 1, rect.top());
        assertEquals("left must be 1", 1, rect.left());
        assertEquals("right must be 3", 3, rect.right());
        assertEquals("bottom must be 3", 3, rect.bottom());
    }

    @Test
    public void testRectangleIntIntIntInt() {
        Rectangle rect = new Rectangle(5, 7, 11, 12);
        
        assertEquals("top must be 5", 5, rect.top());
        assertEquals("left must be 7", 7, rect.left());
        assertEquals("right must be 11", 11, rect.right());
        assertEquals("bottom must be 12", 12, rect.bottom());
    }

    @Test
    public void testLeft() {
        Rectangle rect = new Rectangle(5, 7, 11, 12);
        
        assertEquals("left must be 7", 7, rect.left());
    }

    @Test
    public void testRight() {
        Rectangle rect = new Rectangle(5, 7, 11, 12);
        
        assertEquals("right must be 11", 11, rect.right());
    }

    @Test
    public void testTop() {
        Rectangle rect = new Rectangle(5, 7, 11, 12);
        
        assertEquals("top must be 5", 5, rect.top());
    }

    @Test
    public void testBottom() {
        Rectangle rect = new Rectangle(5, 7, 11, 12);
        
        assertEquals("bottom must be 12", 12, rect.bottom());
    }

    @Test
    public void testQuadrantNorthWest() {
        
        // first test for even width and height
        Rectangle rect = new Rectangle(0, 0, 4, 2);
        Rectangle quadRect = rect.quadrantNorthWest();
        
        assertEquals("top must be 0", 0, quadRect.top());
        assertEquals("left must be 0", 0, quadRect.left());
        assertEquals("right must be 2", 2, quadRect.right());
        assertEquals("bottom must be 1", 1, quadRect.bottom());
        
        // test for odd width and height
        rect = new Rectangle(0, 0, 3, 1);
        quadRect = rect.quadrantNorthWest();
        
        assertEquals("top must be 0", 0, quadRect.top());
        assertEquals("left must be 0", 0, quadRect.left());
        assertEquals("right must be 1", 1, quadRect.right());
        assertEquals("bottom must be 0", 0, quadRect.bottom());
    }

    @Test
    public void testQuadrantNorthEast() {
        // first test for even width and height
        Rectangle rect = new Rectangle(0, 0, 4, 2);
        Rectangle quadRect = rect.quadrantNorthEast();
        
        assertEquals("top must be 0", 0, quadRect.top());
        assertEquals("left must be 2", 2, quadRect.left());
        assertEquals("right must be 4", 4, quadRect.right());
        assertEquals("bottom must be 1", 1, quadRect.bottom());
        
        // test for odd width and height
        rect = new Rectangle(0, 0, 3, 1);
        quadRect = rect.quadrantNorthEast();
        
        assertEquals("top must be 0", 0, quadRect.top());
        assertEquals("left must be 1", 1, quadRect.left());
        assertEquals("right must be 3", 3, quadRect.right());
        assertEquals("bottom must be 0", 0, quadRect.bottom());
    }

    @Test
    public void testQuadrantSouthWest() {
        // first test for even width and height
        Rectangle rect = new Rectangle(0, 0, 4, 2);
        Rectangle quadRect = rect.quadrantSouthWest();
        
        assertEquals("top must be 1", 1, quadRect.top());
        assertEquals("left must be 0", 0, quadRect.left());
        assertEquals("right must be 2", 2, quadRect.right());
        assertEquals("bottom must be 2", 2, quadRect.bottom());
        
        // test for odd width and height
        rect = new Rectangle(0, 0, 3, 1);
        quadRect = rect.quadrantSouthWest();
        
        assertEquals("top must be 0", 0, quadRect.top());
        assertEquals("left must be 0", 0, quadRect.left());
        assertEquals("right must be 1", 1, quadRect.right());
        assertEquals("bottom must be 1", 1, quadRect.bottom());
    }

    @Test
    public void testQuadrantSouthEast() {
     // first test for even width and height
        Rectangle rect = new Rectangle(0, 0, 4, 2);
        Rectangle quadRect = rect.quadrantSouthEast();
        
        assertEquals("top must be 1", 1, quadRect.top());
        assertEquals("left must be 2", 2, quadRect.left());
        assertEquals("right must be 4", 4, quadRect.right());
        assertEquals("bottom must be 2", 2, quadRect.bottom());
        
        // test for odd width and height
        rect = new Rectangle(0, 0, 3, 1);
        quadRect = rect.quadrantSouthEast();
        
        assertEquals("top must be 0", 0, quadRect.top());
        assertEquals("left must be 1", 1, quadRect.left());
        assertEquals("right must be 3", 3, quadRect.right());
        assertEquals("bottom must be 1", 1, quadRect.bottom());
    }

    @Test
    public void testContains() {
        Rectangle container = new Rectangle(0,0,10,10);
        Point point1 = new Point(5, 5); // in
        Point point2 = new Point(12, 5); // out x
        Point point3 = new Point(5, 12); // out y
        Point point4 = new Point(0, 0); // left top corner
        Point point5 = new Point(0, 10); // left bottom corner
        Point point6 = new Point(10, 0); // right top corner
        Point point7 = new Point(10, 10); // right bottom corner
        Point point8 = new Point(5, 10); // on bottom
        Point point9 = new Point(10, 5); // on right
        Point point10 = new Point(0, 5); // on left
        Point point11 = new Point(5, 0); // on top
        
        assertTrue(container.contains(point1));
        assertFalse(container.contains(point2));
        assertFalse(container.contains(point3));
        assertTrue(container.contains(point4));
        assertFalse(container.contains(point5));
        assertFalse(container.contains(point6));
        assertFalse(container.contains(point7));
        assertFalse(container.contains(point8));
        assertFalse(container.contains(point9));
        assertTrue(container.contains(point10));
        assertTrue(container.contains(point11));
    }

    @Test
    public void testIntersects() {
        Rectangle intersecter = new Rectangle(0, 0, 10, 10);
        
        // inside, should true
        Rectangle intersectee1 = new Rectangle(1, 1, 5, 5);
        // outside, should false
        Rectangle intersectee2 = new Rectangle(11, 3, 5, 13);
        // covers it, should true
        Rectangle intersectee3 = new Rectangle(-2, -2, 15, 15);
        // matches it, should true
        Rectangle intersectee4 = new Rectangle(0, 0, 10, 10);
        // inside it, should true
        Rectangle intersectee5 = new Rectangle(1, 1, 9, 9);
        
        assertTrue(intersecter.intersects(intersectee1));
        assertFalse(intersecter.intersects(intersectee2));
        assertTrue(intersecter.intersects(intersectee3));
        assertTrue(intersecter.intersects(intersectee4));
        assertTrue(intersecter.intersects(intersectee5));
    }

    @Test
    public void testCoveredBy() {
        Rectangle coverer = new Rectangle(0, 0, 10, 10);
        
        // should be covered
        Rectangle coveree1 = new Rectangle(1, 1, 5, 5);
        // should not be covered
        Rectangle coveree2 = new Rectangle(11, 3, 5, 13);
        // should not be covered
        Rectangle coveree3 = new Rectangle(-2, -2, 15, 15);
        // should be covered
        Rectangle coveree4 = new Rectangle(0, 0, 10, 10);
        // should be covered
        Rectangle coveree5 = new Rectangle(1, 1, 9, 9);
        
        assertTrue(coveree1.coveredBy(coverer));
        assertFalse(coveree2.coveredBy(coverer));
        assertFalse(coveree3.coveredBy(coverer));
        assertTrue(coveree4.coveredBy(coverer));
        assertTrue(coveree5.coveredBy(coverer));
    }

    @Test
    public void testQuadrant() {
        Rectangle rect = new Rectangle(0, 0, 10, 10);
        
        Point point1 = new Point(7,2); // NE
        Point point2 = new Point(2,2); // NW
        Point point3 = new Point(2,7); // SW
        Point point4 = new Point(7,7); // SE
        Point point5 = new Point(5,5); // middle, SE
        Point point6 = new Point(11,11); // out, none
        Point point7 = new Point(5,2); // NE/NW border, NE
        Point point8 = new Point(2,5); // NW/SW border, SW
        Point point9 = new Point(5,7); // SE/SW border, SE
        Point point10 = new Point(7,5); // NE/SE border, SE
        
        assertEquals(Rectangle.Quadrant.NorthEast, rect.quadrant(point1));
        assertEquals(Rectangle.Quadrant.NorthWest, rect.quadrant(point2));
        assertEquals(Rectangle.Quadrant.SouthWest, rect.quadrant(point3));
        assertEquals(Rectangle.Quadrant.SouthEast, rect.quadrant(point4));
        assertEquals(Rectangle.Quadrant.SouthEast, rect.quadrant(point5));
        assertEquals(Rectangle.Quadrant.None, rect.quadrant(point6));
        assertEquals(Rectangle.Quadrant.NorthEast, rect.quadrant(point7));
        assertEquals(Rectangle.Quadrant.SouthWest, rect.quadrant(point8));
        assertEquals(Rectangle.Quadrant.SouthEast, rect.quadrant(point9));
        assertEquals(Rectangle.Quadrant.SouthEast, rect.quadrant(point10));
    }

}
