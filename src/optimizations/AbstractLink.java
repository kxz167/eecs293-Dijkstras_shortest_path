package optimizations;

import java.util.Objects;

public abstract class AbstractLink implements Link{

    /**
     * Determines whether this current link is takeable given a certain cutoff weight.
     * 
     * We will be utilizing the getCost() method which will be defined in sublcasses. The getCost must return the weight of the link.
     * 
     * @param cutoff The weight which represents a cutoff.
     * @return Whether the current weight is greater than the cutoff weight.
     */

    public boolean isTakeable(Weight cutoff){
        return this.getCost().compareTo(cutoff) >= 0;
    }
}