package AuxClass;

public class Cost {
    /***
     *
     * Calcula el coste de una casilla
     *
     * A (Autovia) = 1
     * N (Nacional) = 2
     * C (Comarcal) = 3
     * X (No transitable) = Inf
     *
     * @param c Tipo casilla
     * @return Coste (tiempo)
     */
    public static int translate(char c) {

        return switch (c) {
            case 'A' -> 1;
            case 'N' -> 2;
            case 'C' -> 3;
            case 'X' -> Integer.MAX_VALUE;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
