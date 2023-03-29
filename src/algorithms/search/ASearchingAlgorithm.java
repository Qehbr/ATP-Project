package algorithms.search;

/**
 * Abstract class searching algorithm implementing Searching algorithm interface
 */
public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    //algorithms name
    String name;

    public ASearchingAlgorithm(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
