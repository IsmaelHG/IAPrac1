package AuxClass;

/**
 * Enumerador para clasificar los tipos de desplazamientos que se pueden
 * realizar sobre el mapa
 */
public enum Operacion {
    ABAJO(0, 1), ARRIBA(0, -1),
    IZQUIERDA(-1, 0), DERECHA(1, 0);

    int desplX;
    int desplY;

    Operacion(int desplX, int desplY) {
        this.desplX = desplX;
        this.desplY = desplY;
    }

    /**
     * Getter
     *
     * @return Desplazamiento en el eje horizontal
     */
    public int getDesplX() {
        return this.desplX;
    }

    /**
     * Getter
     *
     * @return Desplazamiento en el eje vertical
     */
    public int getDesplY() {
        return this.desplY;
    }
}
