package test;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.Objects;

import interest.*;
import optimizations.*;

public class OptimizationTest {

    private Optimization testOptimization = new Optimization();

    private Optimization.TestHook testhook = testOptimization.new TestHook();

    @Test
    public void testInputVerification() throws UninitializedStartError {
        Destination sea = Destination.of("Seattle");

        Gas emptyTank = Gas.of(0);
        sea.setLowestCost(emptyTank);

        testhook.verifyInputs(sea);
    }

    @Test(expected = NullPointerException.class)
    public void testInputNullity() throws UninitializedStartError {
        Destination sea = null;
        testhook.verifyInputs(sea);
    }

    @Test(expected = UninitializedStartError.class)
    public void testInputUninitialized() throws UninitializedStartError {
        Destination sea = Destination.of("Seattle");
        testhook.verifyInputs(sea);
    }

    @Test
    public void testNodeConfiguration() {
        Destination sea = Destination.of("Seattle");
        Destination port = Destination.of("Portland");
        Destination los = Destination.of("Los Angeles");

        Gas emptyTank = Gas.of(0);
        Gas halfTank = Gas.of(10);
        Gas fullTank = Gas.of(20);

        // We have shorter link cost than the node's
        testhook.configureNodeIfShorter(port, sea, halfTank, fullTank);
        assertEquals(sea, port.getPreviousNode());
        assertEquals(halfTank, port.getLowestCost());

        // We have equal link cost to the node's
        testhook.configureNodeIfShorter(port, los, halfTank, halfTank);
        assertEquals(sea, port.getPreviousNode());
        assertEquals(halfTank, port.getLowestCost());

        // We have longer link cost than the node's
        testhook.configureNodeIfShorter(port, los, fullTank, halfTank);
        assertEquals(sea, port.getPreviousNode());
        assertEquals(halfTank, port.getLowestCost());

    }

    @Test(expected = NullPointerException.class)
    public void testOptimizationNullity() throws UninitializedStartError {
        Optimization.shortestPathFrom(null, true);
    }

    @Test(expected = UninitializedStartError.class)
    public void testOptimizationBadData() throws UninitializedStartError {
        Destination sea = Destination.of("Seattle");

        Optimization.shortestPathFrom(sea, true);
    }

    // Enter first while loop
    @Test
    public void testOptimizationBranch1() {
        Destination seattle = Destination.of("seattle");
        Destination tacoma = Destination.of("tacoma");
        Destination olympia = Destination.of("olympia");
        Destination yakima = Destination.of("yakima");
        Destination portland = Destination.of("portland");
        Destination salem = Destination.of("salem");
        Destination eugene = Destination.of("eugene");

        seattle.addLink(Drive.of(Gas.of(44), seattle, tacoma));
        seattle.addLink(Drive.of(Gas.of(142), seattle, yakima));
        tacoma.addLink(Drive.of(Gas.of(30), tacoma, olympia));
        olympia.addLink(Drive.of(Gas.of(115), olympia, portland));
        yakima.addLink(Drive.of(Gas.of(185), yakima, portland));
        portland.addLink(Drive.of(Gas.of(45), portland, salem));
        salem.addLink(Drive.of(Gas.of(66), salem, eugene));

        seattle.setLowestCost(Gas.of(0));

        List<Node> nodes = null;

        try {
            nodes = Optimization.shortestPathFrom(seattle, false);
        } catch (UninitializedStartError e) {
            System.out.println(e.getMessage());
        }

        printRoute(nodes);

        assertEquals(300, nodes.get(nodes.size() - 1).getLowestCost().getWeight());
    }

    // Is takeable true
    @Test
    public void testOptimizationBranch2() {

        // Consider a wacky game with a friend. You are trying to go on a route where
        // whoever travels the farthest between destinations is the best at endurance.
        // Given a map, we want to find the route we should take to reach a destination
        // with the longest straight drive that never travels a shorter distance than already traveled.

        Destination seattle = Destination.of("seattle");
        Destination tacoma = Destination.of("tacoma");
        Destination olympia = Destination.of("olympia");
        Destination yakima = Destination.of("yakima");
        Destination portland = Destination.of("portland");
        Destination salem = Destination.of("salem");
        Destination eugene = Destination.of("eugene");

        seattle.addLink(Drive.of(Gas.of(44), seattle, tacoma));
        seattle.addLink(Drive.of(Gas.of(142), seattle, yakima));
        tacoma.addLink(Drive.of(Gas.of(30), tacoma, olympia));
        olympia.addLink(Drive.of(Gas.of(115), olympia, portland));
        yakima.addLink(Drive.of(Gas.of(185), yakima, portland));
        portland.addLink(Drive.of(Gas.of(45), portland, salem));
        salem.addLink(Drive.of(Gas.of(66), salem, eugene));

        seattle.setLowestCost(Gas.of(0));

        List<Node> nodes = null;

        try {
            nodes = Optimization.shortestPathFrom(seattle, true);
        } catch (UninitializedStartError e) {
            System.out.println(e.getMessage());
        }

        printRoute(nodes);

        assertEquals(185, nodes.get(nodes.size() - 1).getLowestCost().getWeight());
    }

    @Test
    public void testOptimizationLoops(){
        Destination seattle = Destination.of("seattle");
        Destination tacoma = Destination.of("tacoma");
        Destination olympia = Destination.of("olympia");
        Destination yakima = Destination.of("yakima");
        Destination portland = Destination.of("portland");
        Destination salem = Destination.of("salem");
        Destination eugene = Destination.of("eugene");

        seattle.addLink(Drive.of(Gas.of(44), seattle, tacoma));
        seattle.addLink(Drive.of(Gas.of(142), seattle, yakima));
        tacoma.addLink(Drive.of(Gas.of(30), tacoma, olympia));
        olympia.addLink(Drive.of(Gas.of(115), olympia, portland));
        yakima.addLink(Drive.of(Gas.of(44), yakima, seattle));
        portland.addLink(Drive.of(Gas.of(45), portland, salem));
        salem.addLink(Drive.of(Gas.of(66), salem, eugene));

        seattle.setLowestCost(Gas.of(0));

        List<Node> nodes = null;

        try {
            nodes = Optimization.shortestPathFrom(seattle, false);
        } catch (UninitializedStartError e) {
            System.out.println(e.getMessage());
        }

        printRoute(nodes);

        assertEquals(300, nodes.get(nodes.size() - 1).getLowestCost().getWeight());
    }

    public void printRoute(List<Node> nodes) {
        for (Node node : nodes) {
            System.out
                    .println(((Destination) node).getName() + ": " + node.getLowestCost().getWeight() + " mile drive");
            Node lastNode = node;
            while (Objects.nonNull(lastNode)) {
                System.out.print(((Destination) lastNode).getName() + " : ");
                lastNode = lastNode.getPreviousNode();
            }

            System.out.println();
        }
    }
}