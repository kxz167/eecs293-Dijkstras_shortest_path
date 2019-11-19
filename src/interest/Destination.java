package interest;

import optimizations.*;

public class Destination extends AbstractNode{
    String name;

    private Destination(String name){
        this.name = name;
    }

    public static final Destination of( String name){
        return new Destination(name);
    }
}