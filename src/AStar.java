import java.util.*;

public class AStar extends InformedSearch {

    public AStar(String [][] map, Operator[] operadores) {
        super(map, operadores);
    }

    public Collection<Tupla> setNewStructure(){
        return new ArrayList<>();
    }

    protected Comparator<Tupla> getComparator() {
        return (o1, o2) -> {
            float v1, v2;
            v1 =  o1.getValorHeuristico();
            v2 = o2.getValorHeuristico();
            return Float.compare(v1, v2);  //(o1.node.X * o1.node.Y) < o2.v3 ? -1 : o1.v3 > o2.v3 ? 1 : 0;
        };
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