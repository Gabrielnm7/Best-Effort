package aed;

import java.util.Comparator;

public class ComparadorPorTiempo implements Comparator<Traslado> {
    @Override
    public int compare(Traslado a, Traslado b) {
        if (a.timestamp != b.timestamp) {
            return a.timestamp < b.timestamp ? 1 : -1;
        } else {
            return a.id < b.id ? 1 : -1;
        }
    }
}