package algorithms.search;

import java.util.PriorityQueue;

/**
 * BestFirstSearch extends BFS algorithm
 */
public class BestFirstSearch extends BreadthFirstSearch {

    //use super class constructor, but priority queue for BestFirstSearch
    public BestFirstSearch() {
        super("BestFirstSearch", new PriorityQueue<>());
    }
}
