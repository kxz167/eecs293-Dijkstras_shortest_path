package optimizations;
public static class Optimization{

    public List<Node> shortestPathFrom(Node startingNode, boolean weightDependent){
        //Unvisted list -> priority queue
        //Finalized list -> final list.

        
        //Take the starting node.
        //Add the starting node to the unvisited list with weight 0, shortest time possible;

        //While the queue is not empty.
            //Pop off the queue, look at all links.
            //For each link from the node
                //If not in finilized
                    //If weightRestricted is true, 
                        //And if link weight is less than weight of next node,
                            //set the weight of the next node to the link weight. //Note, will have to remove, then add to queue
                    //else if link weight is less than weight of next node
                        //set the next node weight to the weight of this node plus that of the link.
            //Set poped node as finished.
    }
}