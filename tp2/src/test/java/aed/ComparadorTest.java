package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ComparadorTest {
    Traslado t1, t2, t3, t4, t5;

    @BeforeEach
    void init() {
        t1 = new Traslado(8, 1, 2, 400, 15);
        t2 = new Traslado(9, 2, 1, 300, 10);
        t3 = new Traslado(5, 1, 2, 300, 10);
        t4 = new Traslado(34, 1, 2, 300, 10);
        t5 = new Traslado(10, 2, 1, 200, 5);
    }

    ComparadorPorTiempo c1 = new ComparadorPorTiempo();

    @Test
    void primero_mayor_timestamp() {
        assertEquals(-1, c1.compare(t1, t2));
    }

    @Test
    void primero_menor_id() {
        assertEquals(1, c1.compare(t3, t4));
    }

    @Test
    void primero_mayor_id() {
        assertEquals(-1, c1.compare(t2, t3));
    }

    @Test
    void primero_menor_timestamp() {
        assertEquals(1, c1.compare(t5, t4));
    }

    ComparadorPorGanancias c2 = new ComparadorPorGanancias();

    @Test
    void primero_mayor_ganancia() {
        assertEquals(1, c1.compare(t5, t4));
    }

    @Test
    void primero_menor_id_2() {
        assertEquals(1, c1.compare(t3, t4));
    }

    @Test
    void primero_mayor_id_2() {
        assertEquals(-1, c1.compare(t2, t3));
    }

    @Test
    void primero_menor_ganancia() {
        assertEquals(1, c1.compare(t5, t4));
    }
}