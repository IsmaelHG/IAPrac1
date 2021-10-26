import AuxClass.Cost;
import AuxClass.ReadFile;

import java.io.IOException;
import java.util.ArrayList;

public class Practica1 {
    // Fichero que contiene el mapa
    private static final String filepath = "mapa1.txt";

    // Tamaño mapa
    private static final int maxsizeX = 10;
    private static final int maxsizeY = 10;

    // Heuristica (1,2,3) escogida
    private static final int heuristica = 1;

    public static void main(String[] args) throws IOException {
        String[][] map = ReadFile.ReadMap(filepath, maxsizeX, maxsizeY);
        System.out.println("MAPA ESCOGIDO:");
        PrintMap.print(map);

        BestFirst best_first = new BestFirst(map, heuristica);
        AStar a_estrella = new AStar(map, heuristica);

        // Prueba Best First
        ArrayList<Nodo> path_best = best_first.encontrarCamino(new Nodo(0, 0, Cost.translate(map[0][0].charAt(0)), map[0][0]), new Nodo(9, 9, Cost.translate(map[9][9].charAt(0)), map[9][9]));
        if (path_best == null) {
            System.out.print("Best first no ha podido encontrar un camino.");
        } else {
            System.out.print("Camino encontrado por Best First:\n");
            PrintMap.printpath(path_best, map);
        }

        // Prueba A*
        ArrayList<Nodo> path_a = a_estrella.encontrarCamino(new Nodo(0, 0, Cost.translate(map[0][0].charAt(0)), map[0][0]), new Nodo(9, 9, Cost.translate(map[9][9].charAt(0)), map[9][9]));
        if (path_a == null) {
            System.out.print("A* no ha podido encontrar un camino");
        } else {
            System.out.print("Camino encontrado por A*:\n");
            PrintMap.printpath(path_a, map);
        }
    }
}
