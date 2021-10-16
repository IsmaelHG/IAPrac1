package AuxClass;

public class Cost {
    public static int translate (char c) {

        return switch (c) {
            case 'A' -> 1;
            case 'N' -> 2;
            case 'C' -> 3;
            default -> throw new IllegalStateException("Unexpected value: " + c);
        };
    }
}
