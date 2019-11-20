package optimizations;

import java.util.Objects;

public abstract class AbstractLink implements Link{
    private final Weight cost;
    private final Node targetNode;
    private final Node sourceNode;

    protected AbstractLink(Weight cost, Node sourceNode, Node targetNode){
        this.cost = cost;
        this.targetNode = targetNode;
        this.sourceNode = sourceNode;
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

    public Node getSourceNode(){
        return sourceNode;
    }

    public boolean isTakeable(Weight cutoff){
        return this.cost.compareTo(cutoff) >= 0;
    }
}