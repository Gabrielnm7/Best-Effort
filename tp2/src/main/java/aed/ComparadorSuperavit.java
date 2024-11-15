package aed;

import java.util.Comparator;

public class ComparadorSuperavit implements Comparator<Ciudad> {
    @Override

    public int compare(Ciudad a, Ciudad b) {
        int superavit_A = a.Ganancia - a.Perdida;
        int superavit_B = b.Ganancia - b.Perdida;

        if (superavit_A != superavit_B){
            return superavit_A > superavit_B ? 1 : -1;
        }
        else {
            return a.id < b.id ? 1 : -1;
        }
    }
}
