package aed;

import java.util.Comparator;

public class ComparadorSuperavit implements Comparator<Ciudad> {
    @Override

    public int compare(Ciudad a, Ciudad b) {
        int superavit_A = a.Ganancia - a.Perdida;
        int superavit_B = b.Ganancia - b.Perdida;

        if (superavit_A > superavit_B) {
            return 1;
        } else if (superavit_A < superavit_B) {
            return -1;
        } else {
            // Comparamos por id
            if (a.id > b.id) {
                return 1;
            } else if (a.id < b.id) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}
