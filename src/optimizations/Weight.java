package optimizations;

import java.util.Objects;

public interface Weight<T> extends Comparable {

    public static final Weight UNKNOWN = null;
    public T getWeight();
    public Weight weightSumWith(Weight weight);
}