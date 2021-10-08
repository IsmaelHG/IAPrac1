package AuxClass;

/**
 * Created by victor on 11/10/17.
 */
public class TimeCost {
    private int time;
    private int cost;
    private int other;

    public int getTime() {
        return time;
    }

    public int getCost() {
        return cost;
    }

    public int getOther() { return other; }

    public TimeCost(int time, int cost) {
        this.time = time;
        this.cost = cost;
        other = (time*2 + cost)/2; // Asumo que 1u tiempo = 2u coste
    }
}
