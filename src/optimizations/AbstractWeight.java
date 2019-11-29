package optimizations;

import java.util.Objects;

public abstract class AbstractWeight<T> implements Weight<T>{

    /**
     * The weight representation of this weight.
     */
    protected T weight;

    /**
     * Returns a weight which is of the type for the generic.
     * 
     * @return The generic representation of the weight.
     */
    public T getWeight() {
        return weight;
    }
}