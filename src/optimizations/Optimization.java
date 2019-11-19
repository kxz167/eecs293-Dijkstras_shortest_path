package optimizations;

import java.util.Queue;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class Optimization {

    public static List<Node> shortestPathFrom(Node startingNode, boolean weightDependent) {
        // Unvisted list -> priority queue
        Queue<Node> unvisited = new PriorityQueue<>();
        // Finalized list -> final list.
        List<Node> finalized = new ArrayList<>();

        // Take the starting node.
        // Add the starting node to the unvisited list with weight 0, shortest time
        // possible;
        unvisited.add(startingNode);

        // While the queue is not empty.
        while (!unvisited.isEmpty()) {
            // Pop off the queue, look at all links.
            Node consideredNode = unvisited.poll();
            // For each link from the node
            consideredNode.getLinks().stream().filter(link -> link.isTakeable(consideredNode.getLowestCost())
                    && !finalized.contains(link.getTargetNode())).forEach(link -> {
                        Node targetNode = link.getTargetNode();

                        Weight linkWeight = link.getCost();
                        Weight targetNodeWeight = targetNode.getLowestCost();

                        if (weightDependent) {
                            configureNodeIfShorter(targetNode, consideredNode, linkWeight, targetNodeWeight);

                        } else {
                            Weight combinedWeight = linkWeight.addWeight(consideredNode);

                            configureNodeIfShorter(targetNode, consideredNode, combinedWeight, targetNodeWeight);
                        }

                        unvisited.add(targetNode);
                    });

            finalized.add(consideredNode);
        }

        return finalized;
    }

    // Method which takes in target node, previous node, cost,
    public static void configureNodeIfShorter(Node targetNode, Node previousNode, Weight linkCost,
            Weight previousCost) {
        if (linkCost.compareTo(previousCost) < 0) {
            targetNode.setLowestCost(linkCost);

            targetNode.setPreviousNode(previousNode);
        }
    }
}