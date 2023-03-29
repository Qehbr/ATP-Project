package algorithms.search;

/**
 * Abstract class State for states for searching algorithms
 */
public abstract class AState {

    //each state has its predecessor and cost
    protected AState comeFrom;
    protected int cost;

    //getters and setters
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public AState getComeFrom() {
        return comeFrom;
    }

    public void setComeFrom(AState comeFrom) {
        this.comeFrom = comeFrom;
    }

    public abstract int[] getPosition();


}
