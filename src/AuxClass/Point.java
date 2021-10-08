package AuxClass;

/**
 * Clase para indicar un punto en concreto
 */
public class Point {
    private int row, col;

    public Point(int fil, int col) {
        this.row = fil;
        this.col = col;
    }

    public int getFil() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean compare(Point p){
        return row==p.row && col==p.col;
    }
}
