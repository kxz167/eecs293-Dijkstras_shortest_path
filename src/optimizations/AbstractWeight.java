package optimizations;

import java.util.Objects;

public abstract class AbstractWeight<T> implements Weight<T>{

    protected T weight;

    /**
     * @return the weight
     */
    public T getWeight() {
        return weight;
    }
}