import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * Algoritmo de busqueda informada BestFirst
 *
 */
public class BestFirst extends InformedSearch {

    /**
     *
     * Inicializa el algoritmo de bussqueda
     *
     * @param map Mapa
     */
    public BestFirst(String[][] map) {
        super(map);
    }

    @Override
    public Tupla next_trip(Collection<Tupla> ListaPendientes) {
        return ((ArrayList<Tupla>) ListaPendientes).get(0); //The list is sorted so at the first position will always be the maximun element.
    }

    @Override
    public void add(Tupla trip, Collection<Tupla> ListaPendientes) {
        if ( !ListaPendientes.contains(trip)){
            ListaPendientes.add(trip);
            ((ArrayList) ListaPendientes).sort(getComparator());
        }

    }

    @Override
    public void delete_node(Tupla trip, Collection<Tupla> ListaPendientes) {
        ((ArrayList) ListaPendientes).remove(0);
    }

}
