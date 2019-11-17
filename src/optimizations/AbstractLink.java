package optimizations;

import java.util.Objects;

abstract class AbstractLink implements Link{
    private final Weight cost;
    private final Node targetNode;

    private AbstractLink(Weight cost, Node targetNode, CutOff cutoff){
        this.cost = cost;
        this.targetNode = targetNode;
        this.cutoff = cutoff;
    }

    public Link of(Weight cost, Node targetNode){
        Objects.requireNonNull(cost);
        Objects.requireNonNull(targetNode);
        
        return new Link(cost, targetNode);
    }

    /**
     * @return the cost
     */
    public Weight getCost() {
        return cost;
    }

    /**
     * @return the targetNode
     */
    public Node getTargetNode() {
        return targetNode;
    }

    public boolean isTakeable(Weight cutoff){
        return this.cost.compareTo(cutoff) <= 0;
    }
}