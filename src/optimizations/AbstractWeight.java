package optimizations;

import java.util.Objects;

abstract class AbstractWeight<T> implements Weight{

    private T weight;

    /**
     * @return the weight
     */
    public T getWeight() {
        return weight;
    }
}