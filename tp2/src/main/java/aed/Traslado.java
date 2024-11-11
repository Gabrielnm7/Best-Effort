package aed;

public class Traslado {

    int id;
    int origen;
    int destino;
    int gananciaNeta;
    int timestamp;
    private Handler handler;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
        this.handler = new Handler(this); // Inicializo el handler con el traslado
    }

    public Handler obtenerHandler() {
        return this.handler;
    }
}
