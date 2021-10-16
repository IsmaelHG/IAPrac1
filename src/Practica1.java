import AuxClass.Cost;
import AuxClass.PrintMap;
import AuxClass.ReadFile;

import java.io.IOException;
import java.util.ArrayList;

public class Practica1 {
    private static final String filepath = "mapa1.txt";
    private static final int maxsize = 10;

    public static void main(String[] args) throws IOException {
        String[][] map = ReadFile.ReadMap(filepath, maxsize, maxsize);
        System.out.println("MAPA ESCOGIDO:");
        PrintMap.print(map);

        Operator[] ops = new Operator[4];
        ops[0] = new Operator(0,1,"abajo");
        ops[1] = new Operator(0,-1,"arriba");
        ops[2] = new Operator(-1,0,"izquierda");
        ops[3] = new Operator(1,0,"derecha");

        BestFirst bf = new BestFirst(map, ops);
        ArrayList<Nodo> solucion_bf = bf.buscarNodo(new Nodo(0,0, Cost.translate(map[0][0].charAt(0)),map[0][0]), new Nodo(0,1,Cost.translate(map[1][0].charAt(0)), map[1][0]));

        if (solucion_bf != null){
            System.out.print("Camino encontrado por el algoritmo Best First:\n");
            solucion_bf.forEach(Nodo -> System.out.println(Nodo.toString()));
        }
        else{
            System.out.print("No se ha podido encontrar solución con el algoritmo Best first.");
        }
    }
}
