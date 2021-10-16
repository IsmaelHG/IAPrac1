package AuxClass;

public class Cost {
    public static int translate (char c) {
        int val;

        switch (c) {
            case 'A' : val = 1; break;
            case 'N' : val = 2; break;
            case 'C' : val = 3;

                break;
            default:
                throw new IllegalStateException("Unexpected value: " + c);
        }
        
        return val;
    }
}
