package algorithms.search;

/**
 * Interface for searching algorithm
 */
public interface ISearchingAlgorithm {
    /**
     * Given searchable dimension returns solution containing solution using searching algorithm
     *
     * @param searchable Searchable dimension
     * @return Solution object
     */
    Solution solve(ISearchable searchable);

    //getters

    /**
     * Returns number of nodes evaluated after solving searchable dimensions
     *
     * @return number of nodes
     */
    int getNumberOfNodesEvaluated();

    String getName();
}
