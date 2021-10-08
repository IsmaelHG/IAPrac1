package AuxClass;

import java.util.ArrayList;

/**
 * Created by victor on 12/10/17.
 */
public class State {
    private Point pos;
    private State parent;
    private ArrayList<Point> path;
    private float heuristic;
    private float accumulate;

    public State getParent() {
        return parent;
    }

    public ArrayList<Point> getPath() {
        return path;
    }

    public Point getPos(){
        return pos;
    }

    public float getHeuristic(){
        return heuristic;
    }

    public float getAccumulate() { return accumulate; }

    public boolean samePosition(Point p){
        return pos.compare(p);
    }

    public void penalizar(){
        heuristic *= 2;
    }

    public void accumulate(float g){
        accumulate += g;
    }

    public State() {
        /* Only for first state */
        path = new ArrayList<>();
        accumulate = 0;
    }

    public State(Point pos, State p, float h) {
        this.pos = pos;
        parent = p;
        path = (ArrayList<Point>) p.getPath().clone();
        path.add(pos);
        heuristic = h;
        accumulate = 0;
    }
}
