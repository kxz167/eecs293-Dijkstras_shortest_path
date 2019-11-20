package interest;

import java.util.Objects;

import optimizations.*;

public class Drive extends AbstractLink {
    private Drive(Weight cost,Node sourceNode, Node targetNode){
        super(cost, sourceNode, targetNode);
    }

    public static Drive of (Weight cost, Node sourceNode, Node targetNode){
        Objects.requireNonNull(cost);
        Objects.requireNonNull(sourceNode);
        Objects.requireNonNull(targetNode);

        return new Drive(cost, sourceNode, targetNode);
    }
}