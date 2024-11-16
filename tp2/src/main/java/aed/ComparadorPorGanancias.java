package aed;

import java.util.Comparator;

public class ComparadorPorGanancias implements Comparator<Traslado> {
    @Override

    public int compare(Traslado a, Traslado b) {
        if (a.gananciaNeta != b.gananciaNeta) {
            return a.gananciaNeta > b.gananciaNeta ? 1 : -1;
        } else {
            return a.id < b.id ? 1 : -1;
        }
    }
}
