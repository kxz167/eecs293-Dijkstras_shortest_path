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

    private static void configureNodeIfShorter(Node targetNode, Node previousNode, Weight linkCost,
            Weight previousCost) {
        assert Objects.nonNull(targetNode) : "Target node should not be null";
        assert Objects.nonNull(linkCost) : "LinkCost should not be null";

        if (linkCost.compareTo(previousCost) < 0) {

            targetNode.setLowestCost(linkCost);

            targetNode.setPreviousNode(previousNode);
        }
    }

    private static void verifyInputs(Node startingNode) throws UninitializedStartError {
        Objects.requireNonNull(startingNode);

        if (!startingNode.costKnown())
            throw new UninitializedStartError(
                    "The start node should have it's cost set to the lowest weight possible.");
    }

    private static void clearCosts(Node startingNode) {
        assert Objects.nonNull(startingNode) : "The starting Node should not be null";

        Set<Node> visited = new HashSet<>();
        Queue<Node> nextNodes = new PriorityQueue<>();

        for (Link link : startingNode.getLinks()) {
            nextNodes.add(link.getTargetNode());
        }

        visited.add(startingNode);

        while (!nextNodes.isEmpty()) {

            Node nextNode = nextNodes.poll();

            nextNode.getLinks().stream().filter(link -> !visited.contains(link.getTargetNode()))
                    .forEach(unvisitedLink -> nextNodes.add(unvisitedLink.getTargetNode()));

            nextNode.setLowestCost(null);

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