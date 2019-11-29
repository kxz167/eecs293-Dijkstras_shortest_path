package optimizations;

import java.util.Objects;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Optimization {

    public Optimization() {

    }


    /**
     * Computes the shortest path as a list of returned nodes which has successful computations given a starting node
     * Functionality will vary based on weightDependent which says whether we will be adding weights or purely adopting them
     * 
     * @param startingNode The starting node for Dijsktra's algorithm computations. We assume that this has previous weights set (such as time or cost to reach).
     * @param weightDependent True if we don't want to add the weights, false otherwise.
     * 
     * @return A list of all the nodes which has shortest costs to reach from the startingNode
     */
    public static List<Node> shortestPathFrom(Node startingNode, boolean weightDependent)
            throws UninitializedStartError {

        verifyInputs(startingNode);
        clearCosts(startingNode);

        Queue<Node> unvisited = new PriorityQueue<>();
        List<Node> finalized = new ArrayList<>();

        unvisited.add(startingNode);

        while (!unvisited.isEmpty()) {

            Node consideredNode = unvisited.poll();

            consideredNode.getLinks().stream()
                    .filter(link -> (weightDependent ? link.isTakeable(consideredNode.getLowestCost()) : true)
                            && !finalized.contains(link.getTargetNode()))
                    .forEach(link -> {
                        Node targetNode = link.getTargetNode();

                        Weight linkWeight = link.getCost();
                        Weight targetNodeWeight = targetNode.getLowestCost();

                        if (weightDependent) {
                            configureNodeIfShorter(targetNode, consideredNode, linkWeight, targetNodeWeight);

                        } else {
                            Weight combinedWeight = linkWeight.weightSumWith(consideredNode.getLowestCost());

                            configureNodeIfShorter(targetNode, consideredNode, combinedWeight, targetNodeWeight);
                        }

                        unvisited.remove(targetNode);
                        unvisited.add(targetNode);
                    });

            finalized.add(consideredNode);
        }

        return Collections.unmodifiableList(finalized);
    }

    /**
     * Configures a node with the corresponding cost and previous node if the passed in cost is lower
     * 
     * @param targetNode The node we are computing the shortest path to currently
     * @param previousNode The node we are reaching the targetNode from
     * @param linkCost The cost of the path including the previousNode to the targetNode
     */
    private static void configureNodeIfShorter(Node targetNode, Node previousNode, Weight linkCost,
            Weight previousCost) {
        assert Objects.nonNull(targetNode) : "Target node should not be null";
        assert Objects.nonNull(linkCost) : "LinkCost should not be null";

        if (linkCost.compareTo(previousCost) < 0) {

            targetNode.setLowestCost(linkCost);

            targetNode.setPreviousNode(previousNode);
        }
    }

    /**
     * Verifies whether the inputs has been configured correctly
     * 
     * @param startingNode The starting node that was passed into the shortestPathFrom method
     * @throws UninitializedStartError Thrown if the starting node does not have a valid weight set.
     * @throws NullPointerExcpetion Thrown if the startingNode is null.
     */
    private static void verifyInputs(Node startingNode) throws UninitializedStartError, NullPointerException {
        Objects.requireNonNull(startingNode);

        if (!startingNode.isCostKnown())
            throw new UninitializedStartError(
                    "The start node should have it's cost set to the lowest weight possible.");
    }

    /**
     * Runs through all the previous calculations and clears them. This verifies that we don't have residual weights clouding new computations
     * 
     * @param startingNode The starting node we are starting calcuations of the shortest path from
     */
    private static void clearCosts(Node startingNode) {
        assert Objects.nonNull(startingNode) : "The starting Node should not be null";

        Set<Node> visited = new HashSet<>();
        Queue<Node> nextNodes = new PriorityQueue<>();

        startingNode.getLinks().stream().forEach(link -> nextNodes.add(link.getTargetNode()));

        visited.add(startingNode);

        while (!nextNodes.isEmpty()) {

            Node nextNode = nextNodes.poll();

            nextNode.getLinks().stream().filter(link -> !visited.contains(link.getTargetNode()))
                    .forEach(unvisitedLink -> nextNodes.add(unvisitedLink.getTargetNode()));

            nextNode.setLowestCost(Weight.UNKNOWN);

            visited.add(nextNode);
        }
    }

    public class TestHook {
        public final void configureNodeIfShorter(Node targetNode, Node previousNode, Weight linkCost,
                Weight previousCost) {
            Optimization.configureNodeIfShorter(targetNode, previousNode, linkCost, previousCost);
        }

        public final void verifyInputs(Node startingNode) throws UninitializedStartError {
            Optimization.verifyInputs(startingNode);
        }
    }
}