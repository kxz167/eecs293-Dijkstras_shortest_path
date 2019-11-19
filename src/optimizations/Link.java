package optimizations;

import java.util.Objects;

interface Link{
    public Link of(Weight cost, Node targetNode);
    public Weight getCost();
    public Node getTargetNode();
    public boolean isTakeable(Weight cutoff);
}