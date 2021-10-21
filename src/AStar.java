import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * Algoritmo de busqueda informada A*
 *
 */
public class AStar extends InformedSearch {

    /**
     *
     * Inicializa el algoritmo de bussqueda
     *
     * @param map Mapa
     */
    public AStar(String [][] map) {
        super(map);
    }

    @Override
    public Tupla next_trip(Collection<Tupla> ListaPendientes) {
        return ((ArrayList<Tupla>) ListaPendientes).get(0); //The list is sorted so at the first position will always be the maximun element.
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
    public int calcular_valor_estimado(Nodo current_node, Nodo final_node, int costeAcumulado){
        return super.calcular_valor_estimado(current_node, final_node, 0) + costeAcumulado;
    }


}