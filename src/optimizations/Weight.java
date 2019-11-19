package optimizations;

import java.util.Objects;

interface Weight<T> extends Comparable {

    public T getWeight();
    public Weight addWeight(T weight);
}