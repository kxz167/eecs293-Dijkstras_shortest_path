package optimizations;

import java.util.Objects;

public interface Weight<T> extends Comparable {

    /**
     * The reperesntation of an unkown weight is null
     */
    public static final Weight UNKNOWN = null;

    /**
     * Getter for the stored value of the weight. This is NOT the weight object itself and can be integers or custom defined classes.
     * 
     * @return The object that the weight represents
     */
    public T getWeight();

    /**
     * Combines two weight objects and returns a new weight with the sum of the two weights.
     * 
     * @param weight The weight to be summed with
     * @return A new Weight object which will represent the sum of the weight.
     */
    public Weight weightSumWith(Weight weight);
}