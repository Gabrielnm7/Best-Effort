package aed;

public class Handler {
    private Traslado traslado;
    private int indiceCosto; // Índice en el Heap de Costo
    private int indiceTiempo; // Índice en el Heap de Tiempo

    // Constructor
    public Handler(Traslado traslado) {
        this.traslado = traslado;
        this.indiceCosto = -1; // Inicializamos en -1 hasta que se inserte en el Heap
        this.indiceTiempo = -1;
    }

    // Métodos para obtener y establecer los índices
    public void setIndiceCosto(int indice) {
        this.indiceCosto = indice;
    }

    public void setIndiceTiempo(int indice) {
        this.indiceTiempo = indice;
    }

    public int getIndiceCosto() {
        return this.indiceCosto;
    }

    public int getIndiceTiempo() {
        return this.indiceTiempo;
    }

    public Traslado obtenerTraslado() {
        return this.traslado;
    }
}
