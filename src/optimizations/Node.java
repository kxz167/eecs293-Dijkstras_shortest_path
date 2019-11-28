package optimizations;

import java.util.Set;

public interface Node extends Comparable{

    public boolean isCostKnown();
    public Weight getLowestCost();
    public void setLowestCost(Weight lowestCost);
    public Node getPreviousNode();
    public void setPreviousNode(Node previousNode);
    public Set<Link> getLinks();
    public void addLink(Link newLink);
}