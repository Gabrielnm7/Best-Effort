package aed;

import java.util.Comparator;

public class ComparadorPorGanancias implements Comparator<Traslado> {
    @Override

    public int compare(Traslado a, Traslado b) {
        if (a.gananciaNeta > b.gananciaNeta) {
            return 1;
        } else if (a.gananciaNeta < b.gananciaNeta) {
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
