package optimizations;

import java.util.Set;
import java.util.HashSet;
import java.util.Objects;

public abstract class AbstractNode implements Node{
    
    /**
     * Associates a link to a node as long as the node is the source of the link
     * 
     * We will be utilizing the getLinks() method which will be defined by the subclass. getLinks() must return the set of links.
     * 
     * @param newLink The new link that we want to associate with the current node.
     */
    public void addLink(Link newLink){
        if(Objects.nonNull(newLink)){ 
            if(this.equals(newLink.getSourceNode())){
                this.getLinks().add(newLink);
            }
        }
    }

    /**
     * Compares the node against another through delegation to the comparator of weights.
     * 
     * We will be utilizing the getLowestCost() function of the node. The user' implementation must return the lowest cost.
     * 
     * @param o The other node we want to compare to
     * @return Negative if node weight is smaller, 0 if equal, and positive otherwise.
     */
    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Node) || Objects.isNull(this.getLowestCost()))
            return -1;

        return this.getLowestCost().compareTo(((Node)o).getLowestCost());
    }

}