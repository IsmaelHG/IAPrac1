package AuxClass;

public class PrintMap {
    public static void print(String[][] map) {
        for (String[] strings : map) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }
}
