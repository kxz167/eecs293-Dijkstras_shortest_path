package interest;

import java.util.Objects;

import optimizations.*;

public class Drive extends AbstractLink {

    private final Weight cost;
    private final Node targetNode;
    private final Node sourceNode;

    private Drive(Weight cost,Node sourceNode, Node targetNode){
        this.cost = cost;
        this.targetNode = targetNode;
        this.sourceNode = sourceNode;
    }

    /**
     * Takes in a cost, sourceNode, and targetNode for the drive and returns the drive with the corresponding information
     * 
     * @param cost The weight for the drive
     * @param sourceNode The source of this drive
     * @param targetNode The destination of this drive
     * 
     * @throws NullPointerException There will be a null pointer exception thrown whenever the cost, source, or target is null
     * 
     * @return A new Drive with the specified information
     */
    public static Drive of (Weight cost, Node sourceNode, Node targetNode) throws NullPointerException{
        Objects.requireNonNull(cost);
        Objects.requireNonNull(sourceNode);
        Objects.requireNonNull(targetNode);

        return new Drive(cost, sourceNode, targetNode);
    }

    /**
     * Getter method for the cost of the Drive
     * 
     * @return The weight in gas which represents the cost of the drive
     */
    public Weight getCost() {
        return cost;
    }

    /**
     * Getter method for the destination this drive will end at
     * 
     * @return The targetNode for the Drive
     */
    public Node getTargetNode() {
        return targetNode;
    }

    /**
     * Getter method for the start destination of the drive
     * 
     * @return The sourceNode of the drive.
     */
    public Node getSourceNode(){
        return sourceNode;
    }

}