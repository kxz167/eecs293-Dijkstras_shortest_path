package interest;

import optimizations.*;

public class Gas extends AbstractWeight<Integer> {

    private Gas(int weight) {
        this.weight = weight;
    }

    public static Gas of(int weight) {
        return new Gas(weight);
    }

    @Override
    public Weight weightSumWith(Integer weight) {
        return Gas.of(this.getWeight() + weight);
    }

    @Override
    public int compareTo(Object o) {
        return o instanceof Weight ? this.getWeight() - ((Gas) o).getWeight() : -1;
    }
}