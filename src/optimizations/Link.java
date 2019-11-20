package optimizations;

import java.util.Objects;

public interface Link{
    public Weight getCost();
    public Node getTargetNode();
    public Node getSourceNode();
    public boolean isTakeable(Weight cutoff);
}