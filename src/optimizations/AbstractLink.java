package optimizations;

import java.util.Objects;

public abstract class AbstractLink implements Link{
    private final Weight cost;
    private final Node targetNode;

    protected AbstractLink(Weight cost, Node targetNode){
        this.cost = cost;
        this.targetNode = targetNode;
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
        return this.cost.compareTo(cutoff) >= 0;
    }
}