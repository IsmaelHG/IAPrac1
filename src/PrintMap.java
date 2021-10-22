import AuxClass.Cost;

import java.util.ArrayList;

public class PrintMap {
    // Constantes ANSI para imprimir texto en color
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * Metodo para imprimir una matriz en pantalla
     *
     * @param map Matriz de tipo string que contiene el mapa
     */
    public static void print(String[][] map) {
        for (String[] strings : map) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    /**
     * Metodo para imprimir en pantalla el camino tomado sobre un mapa
     *
     * @param path Ruta tomada
     * @param map  Matriz de tipo string que contiene el mapa usado por la ruta
     */
    public static void printpath(ArrayList<Nodo> path, String[][] map) {
        // Mostramos el mapa
        for (int i = 0; i < map.length; i++) {
            System.out.println();
            for (int j = 0; j < map[i].length; j++) {
                // En caso de que la casilla que vamos a mostrar fue usada por la ruta, esta se mostrara de color rojo
                if (path.contains(new Nodo(j, i, Cost.translate(map[i][j].charAt(0)), map[i][j]))) {
                    System.out.print(ANSI_RED + map[i][j] + ANSI_RESET);
                } else {
                    System.out.print(map[i][j]);
                }
            }
        }

    }
}
