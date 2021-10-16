import AuxClass.Cost;

import java.util.ArrayList;

public class PrintMap {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public static void print(String[][] map) {
        for (String[] strings : map) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    public static void printpath(ArrayList<Nodo> path, String[][] map) {
        for (int i=0; i < map.length; i++) {
            System.out.println();
            for (int j=0; j < map[i].length; j++) {
                if (path.contains(new Nodo(j,i, Cost.translate(map[i][j].charAt(0)),map[i][j]))) {
                    System.out.print(ANSI_RED + map[i][j] + ANSI_RESET);
                } else {
                    System.out.print(map[i][j]);
                }
            }
        }

    }
}
