package algorithms.search;

public abstract class AState {


    protected AState comeFrom;
    protected int cost;

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

    abstract int[] getPosition();
}
