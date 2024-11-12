package aed;

public class Ciudad {
    int Ganancia;
    int Perdida;
    int id;
    private Handler handler;

    public Ciudad(int id) {
        Ganancia = 0;
        Perdida = 0;
        this.id = id;
        this.handler = new Handler(); // Inicializo el handler
    }

    public Handler obtenerHandler() {
        return this.handler;
    }
}

