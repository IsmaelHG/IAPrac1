package AuxClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
    public static String[][] ReadMap(String filepath, int row, int col) throws IOException {
        String[][] map = new String[row][col];
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line; String[] sliced;

        for (int i=0; i < row; i++) {
            line = reader.readLine();
            sliced = line.split(",");
            for (int j=0; j < col; j++) {
                map[i][j] = sliced[j];
            }
        }

        return map;
    }
}
