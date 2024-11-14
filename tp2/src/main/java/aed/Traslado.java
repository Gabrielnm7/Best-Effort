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
        this.handler = new Handler(); // Inicializo el handler
    }

    public Handler obtenerHandler() {
        return this.handler;
    }

    @Override
    public String toString() {
        return "[" + id + "]";
    }
}
