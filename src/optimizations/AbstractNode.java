package optimizations;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public abstract class AbstractNode implements Node{
    private Weight lowestCost = null;
    private Node previousNode = null;
    private Set<Link> links = new HashSet<Link>();

    public boolean costKnown(){
        return Objects.nonNull(this.lowestCost);
    }

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
        if(Objects.nonNull(newLink))   
            this.links.add(newLink);
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Node) || Objects.isNull(lowestCost))
            return -1;

        return lowestCost.compareTo(((Node)o).getLowestCost());
    }

}