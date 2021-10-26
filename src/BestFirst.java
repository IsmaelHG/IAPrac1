import java.util.ArrayList;

/**
 * Algoritmo de busqueda informada BestFirst
 */
public class BestFirst extends InformedSearch {

    /**
     * Inicializa el algoritmo de busqueda
     *
     * @param map Mapa
     */
    public BestFirst(String[][] map, int heuristica) {
        super(map, heuristica);
    }

    @Override
    public void add(Tupla trip, ArrayList<Tupla> ListaPendientes) {
        if (!ListaPendientes.contains(trip)) {
            ListaPendientes.add(trip);
            ListaPendientes.sort(getComparator());
        }

    }

}
