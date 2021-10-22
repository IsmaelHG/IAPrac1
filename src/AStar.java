import java.util.ArrayList;
import java.util.Collection;

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
    public Tupla next_trip(Collection<Tupla> ListaPendientes) {
        // La lista esta ordenada
        return ((ArrayList<Tupla>) ListaPendientes).get(0);
    }

    @Override
    public void add(Tupla trip, Collection<Tupla> ListaPendientes) {
        ListaPendientes.add(trip);
        ((ArrayList) ListaPendientes).sort(getComparator());
    }

    @Override
    public void delete_node(Tupla trip, Collection<Tupla> ListaPendientes) {
        ((ArrayList) ListaPendientes).remove(0);
    }

    @Override
    public int calcular_valor_estimado(Nodo current_node, Nodo final_node, int costeAcumulado, int heuristica) {
        // Para el algoritmo A* se tendra en cuenta el coste acumulado
        // f(n) = g(n) + h'(n)
        return costeAcumulado + super.calcular_valor_estimado(current_node, final_node, 0, heuristica);
    }


}