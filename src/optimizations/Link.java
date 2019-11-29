package optimizations;

import java.util.Objects;

public interface Link{

    /**
     * Getter for the cost of the link
     * 
     * @return The cost for the weight as a object of type Weight
     */
    public Weight getCost();

    /**
     * Getter for the target node of the link.
     * 
     * @return The Node which is the destination for this link
     */
    public Node getTargetNode();

    /**
     * Getter for the source node of the link
     * 
     * @return The Node which represents the source of the link
     */
    public Node getSourceNode();

    /**
     * Computes whether the current link is takeable / available for traversal
     * 
     * @param cutoff The weigth cutoff that the current link is compared to
     * 
     * @return True if we are allowed to utilize the link, false otherwise.
     */
    public boolean isTakeable(Weight cutoff);
}