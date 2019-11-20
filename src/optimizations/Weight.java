package optimizations;

import java.util.Objects;

public interface Weight<T> extends Comparable {
    public T getWeight();
    public Weight weightSumWith(Weight weight);
}