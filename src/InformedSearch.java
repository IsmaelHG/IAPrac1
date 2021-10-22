import AuxClass.Cost;
import AuxClass.Operacion;

import java.util.*;

public abstract class InformedSearch {
    String[][] map; // (y,x)
    int maxX;
    int maxY;
    private final int heuristica;

    public InformedSearch(String[][] map, int heuristica) {
        this.map = map;
        this.maxX = map[0].length; // Asumme that all the cols have the same length
        this.maxY = map.length;
        this.heuristica =  heuristica;
    }

    public ArrayList<Nodo> encontrarCamino(Nodo nodo_inicial, Nodo nodo_final){
        Collection<Tupla> ListaPendientes = setNewStructure();
        HashSet<Nodo> ListaTratados = new HashSet<>();

        Nodo current_node;
        ArrayList<Nodo> current_camino = new ArrayList<>();
        int coste = 0;
        int valHeu = 0;
        Tupla current_tup = new Tupla(nodo_inicial, coste, current_camino, valHeu);
        ListaPendientes.add(current_tup);

        ArrayList<Nodo> solucion = null;
        boolean found=false;
        while (!found && !ListaPendientes.isEmpty()) {

            current_tup = next_trip(ListaPendientes);
            current_node = current_tup.getNodo();
            current_camino = current_tup.getCamino();

            delete_node(current_tup, ListaPendientes);

            // Si nos encontramos con el nodo final, habremos terminado.
            if (current_node.equals(nodo_final)){
                found=true;
                solucion = current_camino;
                solucion.add(current_node);
                System.out.println("\nNº de nodos tratados: "+ListaTratados.size());
                System.out.println("Coste (tiempo) total por este camino: "+current_tup.getCosteAcumulado());
            }
            else {
                ArrayList<Nodo> sucesores = getSucesores(current_node);
                // Iteramos sobre todos los sucesores disponibles
                for(Nodo succ: sucesores){
                    // Ignoramos las casillas ya tratadas
                    if (!ListaTratados.contains(succ)){

                        ArrayList<Nodo> new_camino = new ArrayList<>(current_camino);
                        new_camino.add(current_node);
                        coste = current_tup.getCosteAcumulado() + calcular_coste(current_node, succ);
                        valHeu = calcular_valor_estimado(succ, nodo_final, coste, this.heuristica);
                        // Añadimos nodo
                        add(new Tupla(succ, coste, new_camino,valHeu), ListaPendientes);
                    }
                }
                ListaTratados.add(current_node);
            }
        }

        // Retornamos la solucion (null si no encontramos ningun camino)
        return solucion;
    }

    public Collection<Tupla> setNewStructure() { return new ArrayList<>(); }

    protected Comparator<Tupla> getComparator() {
        return (o1, o2) -> {
            float v1, v2;
            v1 =  o1.getValorHeuristico();
            v2 = o2.getValorHeuristico();
            return Float.compare(v1, v2);  //(o1.node.X * o1.node.Y) < o2.v3 ? -1 : o1.v3 > o2.v3 ? 1 : 0;
        };
    }

    public abstract Tupla next_trip(Collection<Tupla> ListaPendientes);

    public int calcular_valor_estimado(Nodo current_node, Nodo final_node, int costeAcumulado, int heuristica){
        // Calculamos el coste de desplazamiento en función de la heuristica que hayamos tomado
        return switch (heuristica) {
            // Heuristica 1: Escogemos el desplazamiento con menor coste
            case 1 -> calcular_coste(current_node, final_node);
            // Heuristica 2:
            case 2 -> calcular_coste(current_node, final_node);
            // Heuristica 3:
            case 3 -> calcular_coste(current_node, final_node);
            // Heuristica invalida
            default -> throw new IllegalStateException("Unexpected value: " + heuristica);
        };
    }

    public int calcular_coste(Nodo nodo_orig, Nodo nodo_desti){
        // Conseguimos el coste de desplazamiento del nodo destino
        int value = nodo_desti.getValue();
        // Si hacemos un cambio de carretera, añadiremos "5" al coste
        if ( !nodo_orig.getType().equals(nodo_desti.getType()) ) { value += 5; }

        return value;
    }

    public abstract void add(Tupla trip, Collection<Tupla> ListaPendientes);

    public abstract void delete_node(Tupla trip, Collection<Tupla> ListaPendientes);

    public ArrayList<Nodo> getSucesores(Nodo nodo_actual){
        ArrayList<Nodo> sucesores = new ArrayList<>();
        // Iteramos sobre los movimientos permitidos
        for (Operacion op: Operacion.values()){
            int next_x = nodo_actual.getX() + op.getDesplX();
            int next_y =  nodo_actual.getY() + op.getDesplY();
            // Comprobamos que el siguiente movimiento no salga del mapa
            if (next_x >= 0 && next_x < maxX && next_y >= 0 && next_y < maxY ){
                // Si la casilla no es una "X", la añadiremos como posible sucesor
                if (!Objects.equals(map[next_y][next_x], "X")){
                    int value = Cost.translate(map[next_y][next_x].charAt(0));
                    sucesores.add(new Nodo(next_x, next_y, value, map[next_y][next_x]));
                }
            }
        }

        // Retornamos la lista
        return sucesores;
    }

}

class Tupla {
    Nodo node;
    ArrayList<Nodo> camino;
    int valorHeuristico, costeAcumulado;

    public Tupla(Nodo estado, int costeAcumulado, ArrayList<Nodo> camino, int valorHeuristico){
        this.node = estado;
        this.costeAcumulado = costeAcumulado;
        this.camino = camino;
        this.valorHeuristico = valorHeuristico;
    }


    @Override
    public boolean equals(Object object){
        if (object instanceof Nodo) {
            return (this.node.equals(object));
        }
        else if (object instanceof Tupla){
            // Retornaremos true si el camino es exactamente igual (misma ruta tomada de Nodos)
            return (this.node.equals(((Tupla) object).getNodo())) && this.camino.equals(((Tupla) object).getCamino());
        }
        return false;
    }

    @Override
    public int hashCode() { return this.node.hashCode() + this.camino.hashCode();}

    // Getters
    public Nodo getNodo(){
        return this.node;
    }

    public ArrayList<Nodo> getCamino(){
        return this.camino;
    }

    public int getCosteAcumulado() { return this.costeAcumulado;}

    public int getValorHeuristico(){
        return this.valorHeuristico;
    }
}
    class Nodo {
        int X,Y,value;
        String type;

        public Nodo(int X, int Y, int value, String type){
            this.X = X;
            this.Y = Y;
            this.value = value;
            this.type = type;
        }

        // Getters
        public int getX() {return this.X;}

        public int getY() {return this.Y;}

        public int getValue() {return this.value;}

        public String getType() {return this.type;}

        @Override
        public boolean equals(Object object){
            if (object instanceof Nodo) {
                return (this.X == ((Nodo) object).getX()) && (this.Y == ((Nodo) object).getY())
                        && (this.value == ((Nodo) object).getValue());
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() { return (X+Y) * (value); }

    }
