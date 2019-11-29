package optimizations;

import java.util.Set;

public interface Node extends Comparable{

    /**
     * Returns if this Node has a non-null cost
     * 
     * @return True if the node has a cost, false otherwise
     */
    public boolean isCostKnown();

    /**
     * Getter for the curret lowest weight
     * 
     * @return The weight object which represents the lowest cost of the node
     */
    public Weight getLowestCost();
    
    /**
     * Setter method to set the lowest cost of the node
     * 
     * @param lowestCost The new lowest cost for this Node
     */
    public void setLowestCost(Weight lowestCost);

    /**
     * Getter for the previous Node on the path to the current node
     * 
     * @return The Node object represnting the previous node on this path.
     */
    public Node getPreviousNode();

    /**
     * Setter to set the previous Node for the computed path
     * 
     * @param previousNode The Node object which will be the previous node.
     */
    public void setPreviousNode(Node previousNode);

    /**
     * Getter method to get all of the links associated with the Node
     * 
     * @return The set of links which the current node houses.
     */
    public Set<Link> getLinks();

    /**
     * Adds a link to the current set of associated links as long as the node and link source agree.
     * 
     * @param newLink The new link to be associated with the Node.
     */
    public void addLink(Link newLink);
}