package interest;

import java.util.Objects;

import optimizations.*;

public class Gas extends AbstractWeight<Integer> {

    private Gas(int weight) {
        this.weight = weight;
    }


    /**
     * Build method for creating a gas weight for driving.
     * 
     * @param weight The quanity of gas we want to represent. For simplicity, this is in integers
     * @return A new gas object with the quantity specified. If a negative gas quantity is entered, it will be set to 0
     */
    public static Gas of(int weight){
        return new Gas(weight < 0 ? 0 : weight);
    }

    /**
     * Override of the weightSumWith from Weight. Creates a new gas object representing the addition of the current weight, and an input weight
     * 
     * @param weight The weight that is to be taken in and added.
     * 
     * @return The new gas object representing the sum of the two weights.
     */
    @Override
    public Weight weightSumWith(Weight weight) {
        Objects.requireNonNull(weight);
        
        return Gas.of(this.getWeight() + (Integer)weight.getWeight());
    }

    /**
     * Override of the compareTo from weight. This will specify how the different gas objects will compare to another
     * 
     * @param o The object that the weight is being compared to
     * @return Negative if the gas integer is less than, 0 if equal, and positive otherwise.
     */
    @Override
    public int compareTo(Object o) {
        return o instanceof Weight ? this.getWeight() - ((Gas) o).getWeight() : -1;
    }
}