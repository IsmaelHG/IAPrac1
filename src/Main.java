import AuxClass.AStar;
import AuxClass.BestFirst;
import AuxClass.Heuristic;
import AuxClass.Point;

import static AuxClass.Common.hashTypes;
import static AuxClass.Common.setHash;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    /**
     * Declaración constantes...
     */
    private static final int maxSize = 10;
    private static final String FILE_MAP = "mapa1.txt";

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    // Mapa maxSize x maxSize
    private static String[][] map = new String[maxSize][maxSize];

    /**
     *
     * Leer fichero mapa...
     *
     * @throws IOException
     */
    private static void readFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_MAP));
        String line;

        for(int i=0;(line = br.readLine()) != null; i++){
            StringTokenizer st = new StringTokenizer(line,",");
            for (int j=0; st.hasMoreTokens(); j++){
                switch (st.nextToken()){
                    case "A": map[i][j] = "A"; break;
                    case "N": map[i][j] = "N"; break;
                    case "C": map[i][j] = "C"; break;
                    case "X": map[i][j] = "X"; break;
                }
            }
        }
    }

    /**
     *
     * @param list
     * @param p
     * @return
     */
    private static boolean contains(ArrayList<Point> list, Point p) {
        for(Point s: list)
            if(s.compare(p))
                return true;
        return false;
    }

    /**
     *
     * @param solved_path
     */
    private static void ShowSolution(ArrayList<Point> solved_path) {
        int cost = 0;
        int time = 0;
        int other = 0;
        int nodes = 0;
        for(int i=0; i < maxSize; i++) {
            System.out.println();
            for (int j = 0; j < maxSize; j++){
                if(contains(solved_path, new Point(i, j))) {
                    System.out.print(ANSI_RED + map[i][j] + ANSI_RESET);
                    cost += hashTypes.get(map[i][j]).getCost();
                    time += hashTypes.get(map[i][j]).getTime();
                    other += hashTypes.get(map[i][j]).getOther();
                    nodes++;
                }
                else
                    System.out.print(map[i][j]);
            }
        }
        System.out.println();
        System.out.println("Nodes: " + nodes + " Cost: " + cost + "\nTime: " + time + " Other: " + other);
    }

    public static void main(String[] args) {
        setHash();
        try {
            readFile();
            BestFirst bf = new BestFirst(map, maxSize);
            AStar as = new AStar(map, maxSize);
            System.out.print("\n*** BFS per COST ***");
            ShowSolution(bf.BFSearch(Heuristic.COST));
            System.out.print("\n*** BFS per TIME ***");
            ShowSolution(bf.BFSearch(Heuristic.TIME));
            System.out.print("\n*** BFS per OTHER ***");
            ShowSolution(bf.BFSearch(Heuristic.OTHER));
            System.out.print("\n*** A* per COST ***");
            ShowSolution(as.ASSearch(Heuristic.COST));
            System.out.print("\n*** A* per TIME ***");
            ShowSolution(as.ASSearch(Heuristic.TIME));
            System.out.print("\n*** A* per OTHER ***");
            ShowSolution(as.ASSearch(Heuristic.OTHER));
        } catch (IOException e) {
            System.out.println("Error reading from file");
        }
    }

}