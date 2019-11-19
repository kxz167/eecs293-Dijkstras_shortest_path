package interest;

import optimizations.*;

public class Drive extends AbstractLink {
    private Drive(Weight cost, Node targetNode){
        super(cost, targetNode);
    }

    public static Drive of (Weight cost, Node targetNode){
        return new Drive(cost, targetNode);
    }
}