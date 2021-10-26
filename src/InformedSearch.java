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
        this.maxX = map[0].length;
        this.maxY = map.length;
        this.heuristica =  heuristica;
    }

    public ArrayList<Nodo> encontrarCamino(Nodo nodo_inicial, Nodo nodo_final){
        ArrayList<Tupla> Pendientes = new ArrayList<>();
        HashSet<Nodo> Tratados = new HashSet<>();

        Nodo current_node;
        ArrayList<Nodo> curr_path = new ArrayList<>();
        int tiempo = 0;
        int valHeu = 0;
        Tupla curr_tupl = new Tupla(nodo_inicial, tiempo, curr_path, valHeu);
        Pendientes.add(curr_tupl);

        ArrayList<Nodo> solucion = null;
        boolean trobat=false;
        while (!trobat && !Pendientes.isEmpty()) {

            curr_tupl = Pendientes.get(0);
            current_node = curr_tupl.getNodo();
            curr_path = curr_tupl.getCamino();

            Pendientes.remove(0);

            // Si nos encontramos con el nodo final, habremos terminado.
            if (current_node.equals(nodo_final)){
                trobat=true;
                solucion = curr_path;
                solucion.add(current_node);
                System.out.println("CAMINO ENCONTRADO");
                System.out.println("Num de nodos tratados: "+Tratados.size());
                System.out.println("Tiempo del camino: "+curr_tupl.gettiempoAcumulado());
            }
            else {
                ArrayList<Nodo> sucesores = getSucesores(current_node);
                // Iteramos sobre todos los sucesores disponibles
                for(Nodo succ: sucesores){
                    // Ignoramos las casillas ya tratadas
                    if (!Tratados.contains(succ)){

                        ArrayList<Nodo> new_camino = new ArrayList<>(curr_path);
                        new_camino.add(current_node);
                        tiempo = curr_tupl.gettiempoAcumulado() + calcular_tiempo(current_node, succ);
                        valHeu = calcular_heuristica(succ, nodo_final, tiempo, this.heuristica);
                        // Añadimos nodo
                        add(new Tupla(succ, tiempo, new_camino,valHeu), Pendientes);
                    }
                }
                Tratados.add(current_node);
            }
        }
        // Retornamos la solucion (null si no encontramos ningun camino)
        return solucion;
    }

    protected Comparator<Tupla> getComparator() {
        return (o1, o2) -> {
            float v1, v2;
            v1 =  o1.getValorHeuristico();
            v2 = o2.getValorHeuristico();
            return Float.compare(v1, v2);  //(o1.node.X * o1.node.Y) < o2.v3 ? -1 : o1.v3 > o2.v3 ? 1 : 0;
        };
    }

    public int calcular_heuristica(Nodo curr_node, Nodo fi_node, int tiempoAcumulado, int heuristica){
        // Calculamos el tiempo de desplazamiento en función de la heuristica que hayamos tomado
        int val;
        switch (heuristica) {
            // Heuristica 1: Escogemos el desplazamiento con menor tiempo
            case 1:
                val = calcular_tiempo(curr_node, fi_node);
                break;
            // Heuristica 2: Evitamos un cambio de carretera
            case 2:
                if ( !curr_node.getType().equals(fi_node.getType()) ) {
                    val = calcular_tiempo(curr_node, fi_node) * 2;
                }
                else { val = calcular_tiempo(curr_node, fi_node); }
                break;
            // Heuristica 3: Evitamos las carreteras comarcales
            case 3:
                if (curr_node.getType().equals("C") ) {
                    val = calcular_tiempo(curr_node, fi_node) * 2;
                } else {
                    val = calcular_tiempo(curr_node, fi_node);
                }
                break;
            // Heuristica invalida
            default:
                throw new IllegalStateException("Unexpected value: " + heuristica);
        }

        return val;

    }

    public int calcular_tiempo(Nodo curr_node, Nodo fi_node){
        // Conseguimos el tiempo de desplazamiento del nodo destino
        int value = fi_node.getValue();
        // Si hacemos un cambio de carretera, añadiremos "5" al tiempo
        if ( !curr_node.getType().equals(fi_node.getType()) ) { value += 5; }

        return value;
    }

    public abstract void add(Tupla trip, ArrayList<Tupla> Pendientes);

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
    ArrayList<Nodo> path;
    int valorHeuristico, tiempoAcumulado;

    public Tupla(Nodo estado, int tiempoAcumulado, ArrayList<Nodo> camino, int valorHeuristico){
        this.node = estado;
        this.tiempoAcumulado = tiempoAcumulado;
        this.path = camino;
        this.valorHeuristico = valorHeuristico;
    }


    @Override
    public boolean equals(Object object){
        if (object instanceof Nodo) {
            return (this.node.equals(object));
        }
        else if (object instanceof Tupla){
            // Retornaremos true si el camino es exactamente igual (misma ruta tomada de Nodos)
            return (this.node.equals(((Tupla) object).getNodo())) && this.path.equals(((Tupla) object).getCamino());
        }
        return false;
    }

    @Override
    public int hashCode() { return this.node.hashCode() + this.path.hashCode();}

    // Getters
    public Nodo getNodo(){
        return this.node;
    }

    public ArrayList<Nodo> getCamino(){
        return this.path;
    }

    public int gettiempoAcumulado() { return this.tiempoAcumulado;}

    public int getValorHeuristico(){
        return this.valorHeuristico;
    }
}
    class Nodo {
        int X,Y,val;
        String type;

        public Nodo(int X, int Y, int val, String type){
            this.X = X;
            this.Y = Y;
            this.val = val;
            this.type = type;
        }

        // Getters
        public int getX() {return this.X;}

        public int getY() {return this.Y;}

        public int getValue() {return this.val;}

        public String getType() {return this.type;}

        @Override
        public boolean equals(Object object){
            if (object instanceof Nodo) {
                return (this.X == ((Nodo) object).getX()) && (this.Y == ((Nodo) object).getY())
                        && (this.val == ((Nodo) object).getValue());
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() { return (X+Y) * (val); }

    }
