package interest;

import optimizations.*;

public class Gas extends AbstractWeight<Integer> {

    private Gas(int weight) {
        super(weight);
    }

    public static Gas of(int weight) {
        return new Gas(weight);
    }

    @Override
    public Weight addWeight(Integer weight) {
        return Gas.of(this.getWeight() + weight);
    }

    @Override
    public int compareTo(Object o) {
        return o instanceof Weight ? this.getWeight() - ((Gas) o).getWeight() : -1;
    }
}