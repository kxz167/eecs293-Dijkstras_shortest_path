package interest;

import optimizations.*;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;



public class Destination extends AbstractNode{

    private Weight lowestCost = null;
    private Node previousNode = null;
    private Set<Link> links = new HashSet<Link>();

    private String name;

    private Destination(String name){
        this.name = name;
    }

    /**
     * Build method for creating destinations given the name of the destination
     * @param name The string representation for the name of the destination.
     * @return A new destination created by the constructor with the name
     */
    public static final Destination of( String name){
        return new Destination(name);
    }

    /**
     * Getter method to get the name of the destination
     * 
     * @return the string represntation of the name
     */
    public String getName(){
        return this.name;
    }

    /**
     * Determines whether the cost of this destination is known
     * 
     * @return True if the lowest cost is not null, false otherwise
     */
    public boolean isCostKnown(){
        return Objects.nonNull(this.lowestCost);
    }

    /**
     * Returns the lowest cost of the destination that has been stored
     * 
     * @return The lowest cost of the Destination.
     */
    public Weight getLowestCost() {
        return lowestCost;
    }

    /**
     * Setter method for the lowest cost of the destination determined by previous runs of dijkstra's algorithm
     * 
     * @param lowestCost The new lowest cost for the destination
     */
    public void setLowestCost(Weight lowestCost) {
        this.lowestCost = lowestCost;
    }

    /**
     * Getter method for the previous destination to reach the current one
     * 
     * @return The previousNode representing the last destination
     */
    public Node getPreviousNode() {
        return previousNode;
    }

    /**
     * Setter method to set the previousNode / destination of the current one
     * 
     * @param previousNode The new previousNode to be set for this destination
     */
    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    /**
     * Returns the links that were previously assosciated with the destination
     * 
     * @return A set of links for this destination
     */
    public Set<Link> getLinks() {
        return links;
    }
}