package aed;

public class Handler {
    private int indiceCosto; // Índice en el Heap de Costo
    private int indiceTiempo; // Índice en el Heap de Tiempo
    private int indiceSuperavit; // Índice en el Heap de Superavit

    // Constructor
    public Handler() {
        this.indiceCosto = -1; // Inicializamos en -1 hasta que se inserte en el Heap
        this.indiceTiempo = -1;
        this.indiceSuperavit = -1;
    }

    // Métodos para obtener y establecer los índices
    public void setIndiceCosto(int indice) {
        this.indiceCosto = indice;
    }

    public void setIndiceTiempo(int indice) {
        this.indiceTiempo = indice;
    }

    public void setIndiceSuperavit(int indice) {
        this.indiceSuperavit = indice;
    }

    public int getIndiceCosto() {
        return this.indiceCosto;
    }

    public int getIndiceTiempo() {
        return this.indiceTiempo;
    }

    public int getIndiceSuperavit() {
        return this.indiceSuperavit;
    }

    // devolvemos el handler
    public Handler obtenerHandler() {
        return this;
    }
}
