package optimizations;

import java.util.ArrayList;

interface Node extends Comparable{
    /**
     * @return the lowestCost
     */
    public Weight getLowestCost();

    /**
     * @param lowestCost the lowestCost to set
     */
    public void setLowestCost(Weight lowestCost);
    /**
     * @return the previousNode
     */
    public Node getPreviousNode();

    /**
     * @param previousNode the previousNode to set
     */
    public void setPreviousNode(Node previousNode);

    /**
     * @return the links
     */
    public List<Link> getLinks();
    
    public void addLink(Link newLink);

    public void removeLink(Link removedLink);

}