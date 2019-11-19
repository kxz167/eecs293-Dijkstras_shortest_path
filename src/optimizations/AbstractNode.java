package optimizations;

import java.util.Set;
import java.util.HashSet;

abstract class AbstractNode implements Node{
    private Weight lowestCost = null;
    private Node previousNode = null;
    private Set<Link> links = new HashSet<Link>();

    /**
     * @return the lowestCost
     */
    public Weight getLowestCost() {
        return lowestCost;
    }

    /**
     * @param lowestCost the lowestCost to set
     */
    public void setLowestCost(Weight lowestCost) {
        this.lowestCost = lowestCost;
    }
    /**
     * @return the previousNode
     */
    public Node getPreviousNode() {
        return previousNode;
    }

    /**
     * @param previousNode the previousNode to set
     */
    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
    }

    /**
     * @return the links
     */
    public Set<Link> getLinks() {
        return links;
    }
    
    public void addLink(Link newLink){
        this.links.add(newLink);
    }

    public void removeLink(Link removedLink){
        this.links.remove(removedLink);
    }

}