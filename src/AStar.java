import java.util.ArrayList;

/**
 * Algoritmo de busqueda informada A*
 */
public class AStar extends InformedSearch {
    /**
     * Inicializa el algoritmo de bussqueda
     *
     * @param map Mapa
     */
    public AStar(String[][] map, int heuristica) {
        super(map, heuristica);
    }

    @Override
    public void add(Tupla trip, ArrayList<Tupla> ListaPendientes) {
        ListaPendientes.add(trip);
        ListaPendientes.sort(getComparator());
    }

    @Override
    public int calcular_heuristica(Nodo current_node, Nodo final_node, int costeAcumulado, int heuristica) {
        // Para el algoritmo A* se tendra en cuenta el coste acumulado
        // f(n) = g(n) + h'(n)
        return costeAcumulado + super.calcular_heuristica(current_node, final_node, 0, heuristica);
    }
}