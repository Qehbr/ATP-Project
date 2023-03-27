package algorithms.search;

import java.util.List;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    String name;

    public ASearchingAlgorithm(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
