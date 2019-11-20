package interest;

import optimizations.*;

public class Drive extends AbstractLink {
    private Drive(Weight cost,Node sourceNode, Node targetNode){
        super(cost, sourceNode, targetNode);
    }

    public static Drive of (Weight cost, Node sourceNode, Node targetNode){
        return new Drive(cost, sourceNode, targetNode);
    }
}