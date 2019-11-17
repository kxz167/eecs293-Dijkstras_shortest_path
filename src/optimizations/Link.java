package optimizations;

import java.util.Objects;

interface Link{
    public Link of(Weight cost, Node targetNode);

    /**
     * @return the cost
     */
    public Weight getCost();

    /**
     * @return the targetNode
     */
    public Node getTargetNode();
    
    public boolean isTakeable(Weight cutoff);
}