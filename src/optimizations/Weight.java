package optimizations;

import java.util.Objects;

abstract class Weight<T> implements Comparable{
    private T weight;

    private Weight(T weight){
        this.weight = weight;
    }

    public Weight of(T weight){
        Objects.requireNonNull(weight);

        return new Weight<T>(weight);
    }

    /**
     * @return the weight
     */
    public T getWeight() {
        return weight;
    }

    abstract public void addWeight(T weight);
}