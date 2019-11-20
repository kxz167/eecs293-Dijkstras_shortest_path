package test;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;


import interest.*;
import optimizations.*;

public class StressTest{
    @Test
    public void testRepeatedCalls(){
        Destination seattle = Destination.of("seattle");
        seattle.setLowestCost(Gas.of(0));

        Node previousNode = seattle;

        for(int i = 0; i < 5000; i++){
            
            Destination ith = Destination.of("Destination: " + i);
            
            previousNode.addLink(Drive.of(Gas.of(1), previousNode, ith));

            List<Node> nodes = null;

            try {
                nodes = Optimization.shortestPathFrom(seattle, false);
            } catch (UninitializedStartError e) {
                System.out.println(e.getMessage());
            }

            assertEquals(i + 1, nodes.get(nodes.size() - 1).getLowestCost().getWeight());

            previousNode = ith;
        }
    }

    @Test
    public void testLongPath(){
        Destination seattle = Destination.of("seattle");
        seattle.setLowestCost(Gas.of(0));

        Node previousNode = seattle;

        for(int i = 0; i < 10000; i++){
            
            Destination ith = Destination.of("Destination: " + i);
            
            previousNode.addLink(Drive.of(Gas.of(1), previousNode, ith));

            previousNode = ith;
        }

        List<Node> nodes = null;

        try {
            nodes = Optimization.shortestPathFrom(seattle, false);
        } catch (UninitializedStartError e) {
            System.out.println(e.getMessage());
        }

        int size = nodes.size();
        assertEquals(size - 1, nodes.get(size - 1).getLowestCost().getWeight());
    }

}