package aed;

import java.util.Comparator;

public class ComparadorPorTiempo implements Comparator<Traslado> {
    @Override
    public int compare(Traslado a, Traslado b) {
        if (a.timestamp < b.timestamp) {
            return 1;
        } else if (a.timestamp > b.timestamp) {
            return (-1);
        } else {
            if (a.id < b.id) {
                return 1;
            } else {
                return (-1);
            }
        }
    }
}