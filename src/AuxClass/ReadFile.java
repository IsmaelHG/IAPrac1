package AuxClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {

    /**
     * Metodo para leer el mapa.
     *
     * @param filepath Ruta del fichero de texto que contiene el mapa. El fichero esta delimitado por comas
     * @param row Numero de filas del mapa
     * @param col Numero de columnas del mapa
     * @return Matriz de tipo String donde cada posición contiene el tipo de casilla definido
     * @throws IOException En caso de fallo al leer el fichero indicado
     */
    public static String[][] ReadMap(String filepath, int row, int col) throws IOException {
        String[][] map = new String[row][col];
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line; String[] sliced;

        for (int i=0; i < row; i++) {
            line = reader.readLine();
            sliced = line.split(",");
            System.arraycopy(sliced, 0, map[i], 0, col);
        }

        // Retornamos la matriz
        return map;
    }
}
