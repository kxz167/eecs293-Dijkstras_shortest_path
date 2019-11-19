package optimizations;

import java.util.Objects;

public interface Link{
    public Weight getCost();
    public Node getTargetNode();
    public boolean isTakeable(Weight cutoff);
}