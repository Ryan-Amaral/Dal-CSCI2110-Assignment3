import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * An implementation of TwoDimDictionary that uses a quadree to be able
 * to perform query and count operation in O(log_4(n)).
 * 
 * Assumptions/Restrictions: 
 * 
 * Noteworthy Features:
 * 
 * @author Ryan Amaral
 *
 */
public class QuadTree implements TwoDimDictionary {
    Rectangle bounds = null; // the bounds of this tree
    int numPoints = 0; // amount of points within bounds
    Point point = null; // stores a point if this is external node
    QuadTree[] subTrees = null; // the children of this node if it is internal

    /**
     * Creates a QuadTree with the specified bounds.
     * @param bounds The bounds.
     */
    public QuadTree(Rectangle bounds){
        this.bounds = bounds;
    }
    
    // this method adapted from the assignment description
    @Override
    public void insert(Point newPoint) {
        // OOB
        if(!bounds.contains(newPoint)){
            return;
        }
        // an empty leaf
        else if(numPoints == 0){
            this.point = newPoint; // make not empty leaf
        }
        // a filled leaf
        else if(numPoints == 1){
            // make it an internal node
            split(); // creates the children, assigns their bounds
            // insert this point into child in right quadrant
            subTrees[bounds.quadrant(this.point).val()]
                    .insert(this.point);
            this.point = null; // no need for this point, free space
            subTrees[bounds.quadrant(newPoint).val()]
                    .insert(newPoint);
        }
        // this node already has children
        else{
            // insert into right quadrant
            subTrees[bounds.quadrant(newPoint).val()]
                    .insert(newPoint);
        }
        numPoints++; // one more point in this node (works recursively)
    }
    
    /**
     * Creates the subtrees of this node.
     */
    private void split(){
        // 4 subtrees (thats why it is called a quadtree)
        subTrees = new QuadTree[4];
        subTrees[Rectangle.Quadrant.NorthEast.val()] 
                = new QuadTree(bounds.quadrantNorthEast()); // NE
        subTrees[Rectangle.Quadrant.NorthWest.val()] 
                = new QuadTree(bounds.quadrantNorthWest()); // NW
        subTrees[Rectangle.Quadrant.SouthWest.val()] 
                = new QuadTree(bounds.quadrantSouthWest()); // SW
        subTrees[Rectangle.Quadrant.SouthEast.val()] 
                = new QuadTree(bounds.quadrantSouthEast()); // SE
    }

    @Override
    public int count(Rectangle testBounds) {
        int amount = 0; // the amount to return
        
        // no points so return 0
        if(numPoints == 0)
            amount = 0;
        // this bounds within testBounds so all points are in
        else if(bounds.coveredBy(testBounds))
            amount = numPoints;
        // this is point inside testBounds, return this point
        else if(isPoint() && testBounds.contains(point))
            amount = 1;
        // not a point so look at children
        else if(!isPoint()){
            // sum up 4 children
            for(int i = 0; i < 4; i++){
                // only look into touched quadrants
                if(subTrees[i].bounds.intersects(testBounds))
                    amount += subTrees[i].count(testBounds);
            }
        }
        
        return amount;
    }
    
    /**
     * @return Whether this node is a point.
     */
    private boolean isPoint(){
        return point != null;
    }

    // this method adapted from the assignment description
    @Override
    public void query(ArrayList<Point> set, Rectangle testBounds) {
        // some of testBounds is inside bounds
        if(bounds.intersects(testBounds)){
            // if there is only one point in the structure, this node is
            // leaf, so if the point is in testBounds, add to set
            if(numPoints == 1
                    && testBounds.contains(point)){
                set.add(point);
            }
            // more points, so must check sub trees
            else if(numPoints > 1){
                // always 4 children
                for(int i = 0; i < 4; i++){
                    subTrees[i].query(set, testBounds);
                }
            }
        }
    }

    @Override
    public int size() {
        return numPoints;
    }

    @Override
    public void display() {
        display(0); // use internal method
    }

    /**
     * Internal method to help traverse the tree, keeping track of the 
     * level.
     * @param level The depth currently being looked at.
     */
    private void display(int level){
        for(int i = 0; i < level * 3; i++)
            System.out.print(" "); // print spaces
        
        // not a point
        if(!isPoint()){
            // no children
            if(subTrees == null){
                System.out.println("0"); // represents Empty child
                return;
            }
            // is parent
            else{
                System.out.println("P"); // represents parent
            }
            
            // print all children
            subTrees[Rectangle.Quadrant.NorthEast.val()].display(level + 1);
            subTrees[Rectangle.Quadrant.NorthWest.val()].display(level + 1);
            subTrees[Rectangle.Quadrant.SouthWest.val()].display(level + 1);
            subTrees[Rectangle.Quadrant.SouthEast.val()].display(level + 1);
        }
        else{
            System.out.println(point.toString());
        }
    }
    
    /**
     * Saves a dot file for good visualization of this structure
     * using Graphviz.
     * @param fileName The name of the file to save to.
     */
    public void saveDotFile(String fileName) throws IOException{
        FileOutputStream ostream = null;
        
        try{
            File file = new File("output/" + fileName + ".dot");
            ostream = new FileOutputStream(file);
            ostream.write(toDotString().getBytes());
        }finally{
            ostream.close();
        }
    }
    
    /**
     * @return A string representation of this structure in the dot file
     * format.
     */
    private String toDotString(){
        StringBuilder output = new StringBuilder(); // the string to write to
        output.append("digraph quadtree {\n");
        dotStringRecursor(output, 0);
        output.append("}");
        return output.toString();
    }
    
    /**
     * Helps construct a dot file string.
     * @param input The string to append to.
     * @param id The id for differentiating nodes.
     */
    private int dotStringRecursor(StringBuilder input, int id){
        
        // increment this everytime
        String thisThing = "thing" + ((Integer)(++id)).toString();
        
        // not a point
        if(!isPoint()){
            // no children, so is empty
            if(subTrees == null){
                input.append("    " + thisThing + " [shape=circle,color=red,label=\"Empty\"];\n");
            }
            // has children
            else{
                // create the parent thing
                input.append("    " + thisThing + "[shape=box,color=black,label=\"Parent\"];\n");
                
                // do this function to all children, because this has children
                String thisThingChild1 = "thing" + ((Integer)(id + 1)).toString();
                id = subTrees[Rectangle.Quadrant.NorthEast.val()].dotStringRecursor(input, id);
                String thisThingChild2 = "thing" + ((Integer)(id + 1)).toString();
                id = subTrees[Rectangle.Quadrant.NorthWest.val()].dotStringRecursor(input, id);
                String thisThingChild3 = "thing" + ((Integer)(id + 1)).toString();
                id = subTrees[Rectangle.Quadrant.SouthWest.val()].dotStringRecursor(input, id);
                String thisThingChild4 = "thing" + ((Integer)(id + 1)).toString();
                id = subTrees[Rectangle.Quadrant.SouthEast.val()].dotStringRecursor(input, id);
                
                // link this node to children
                input.append("    " + thisThing + " -> " + thisThingChild1 + ";\n");
                input.append("    " + thisThing + " -> " + thisThingChild2 + ";\n");
                input.append("    " + thisThing + " -> " + thisThingChild3 + ";\n");
                input.append("    " + thisThing + " -> " + thisThingChild4 + ";\n");
            }
        }
        // is a point
        else{
            input.append("    " + thisThing +"[shape=circle,color=green,label=\"" + 
                    point.toString() + "\"];\n");
        }
        
        return id;
    }
    
    /**
     * @return Teh string representation of this node. "Empty" if the node
     * has no children and no point assigned, "Parent" if the node has 
     * children, otherwise returns the point's toString() method.
     */
    private String nodeString(){
        if(point == null)
            if(subTrees == null)
                return "Empty";
            else
                return "Parent";
        else
            return point.toString();
    }
}
