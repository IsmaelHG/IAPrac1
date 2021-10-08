package AuxClass;

import AuxClass.Heuristic;
import AuxClass.Point;
import AuxClass.State;
import AuxClass.TimeCost;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by victor on 12/10/17.
 */
public abstract class Common {
    protected String[][] map;
    protected int size;
    public static HashMap<String, TimeCost> hashTypes = new HashMap<>();

    public static void setHash() {
        hashTypes.put("A", new TimeCost(1, 0));
        hashTypes.put("N", new TimeCost(2, 0));
        hashTypes.put("C", new TimeCost(3, 0));
    }

    protected boolean contains(ArrayList<State> list, State s){
        for (State e: list){
            if(e.samePosition(s.getPos()))
                return true;
        }
        return false;
    }

    private State getNeighbour(int fil, int col, State p, Heuristic H){
        State aux;
        switch (H){
            case COST: aux = new State(new Point(fil, col), p, hashTypes.get(map[fil][col]).getCost()); aux.accumulate(p.getAccumulate() + aux.getHeuristic()); return aux;
            case TIME: aux = new State(new Point(fil, col), p, hashTypes.get(map[fil][col]).getTime()); aux.accumulate(p.getAccumulate() + aux.getHeuristic()); return aux;
            case OTHER: aux = new State(new Point(fil, col), p, hashTypes.get(map[fil][col]).getOther()); aux.accumulate(p.getAccumulate() + aux.getHeuristic()); return aux;
            // Should add acc + real_cost, but I consider an ideal heuristic as there's no real data given.
        }
        return null; // CAN'T BE
    }

    protected ArrayList<State> lookNeighbours(State s, Heuristic H){
        int col = s.getPos().getCol();
        int fil = s.getPos().getFil();
        ArrayList<State> nb = new ArrayList<>();
        if (col + 1 < size) {
            nb.add(getNeighbour(fil, col+1, s, H));
        }
        if (fil + 1 < size) {
            nb.add(getNeighbour(fil+1, col, s, H));
        }
        if (col - 1 >= 0) {
            State aux = getNeighbour(fil, col-1, s, H);
            aux.penalizar();
            nb.add(aux);
        }
        if (fil - 1 >= 0) {
            State aux = getNeighbour(fil-1, col, s, H);
            aux.penalizar();
            nb.add(aux);
        }
        return nb;
    }
}
