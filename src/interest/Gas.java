package interest;

import java.util.Objects;

import optimizations.*;

public class Gas extends AbstractWeight<Integer> {

    private Gas(int weight) {
        this.weight = weight;
    }

    public static Gas of(int weight){
        return new Gas(weight < 0 ? 0 : weight);
    }

    @Override
    public Weight weightSumWith(Weight weight) {
        Objects.requireNonNull(weight);
        
        return Gas.of(this.getWeight() + (Integer)weight.getWeight());
    }

    @Override
    public int compareTo(Object o) {
        return o instanceof Weight ? this.getWeight() - ((Gas) o).getWeight() : -1;
    }
}