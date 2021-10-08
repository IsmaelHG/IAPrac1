package AuxClass;

import AuxClass.Point;

import java.util.ArrayList;

/**
 * Created by victor on 12/10/17.
 */
public class AStar extends Common {
    public AStar(String[][] map, int size) {
        super.map = map;
        super.size = size;
    }

    public ArrayList<Point> ASSearch(Heuristic H) {
        Point finalPos = new Point(9,9);
        ArrayList<State> remainings = new ArrayList<>();
        remainings.add(new State(new Point(0,0), new State(), 0));
        ArrayList<State> processeds = new ArrayList<>();
        boolean found = false;
        while (!found && !remainings.isEmpty()){
            State s = remainings.get(0);
            remainings.remove(0);
            if(s.samePosition(finalPos))
                return s.getPath();
            for(State n : lookNeighbours(s, H)){
                if(!contains(processeds, n)){
                    remainings.add(n);
                    remainings.sort((o1, o2) -> {
                        if(o1.getAccumulate() + o1.getHeuristic() > o2.getAccumulate() + o2.getHeuristic())
                            return 1;
                        if(o1.getAccumulate() + o1.getHeuristic() < o2.getAccumulate() + o2.getHeuristic())
                            return -1;
                        return 0;
                    });
                }
                processeds.add(n);
            }
        }
        return null;
    }
}
