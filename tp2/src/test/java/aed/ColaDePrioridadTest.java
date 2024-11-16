package aed;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ColaDePrioridadTest {

    Traslado t1, t2, t3, t4, t5, t6;

    @BeforeEach
    void init() {
        t1 = new Traslado(1, 2, 3, 4, 5);
        t2 = new Traslado(11, 3, 2, 200, 7);
        t3 = new Traslado(3, 3, 1, 4, 5);
        t4 = new Traslado(34, 1, 2, 300, 10);
        t5 = new Traslado(10, 2, 1, 200, 5);
        t6 = new Traslado(9, 2, 1, 200, 5);
    }

    ComparadorPorGanancias c1 = new ComparadorPorGanancias();
    ComparadorPorTiempo c2 = new ComparadorPorTiempo();
    ColaDePrioridad<Traslado> colaGasto = new ColaDePrioridad<Traslado>(c1);
    ColaDePrioridad<Traslado> colaTimeStamp = new ColaDePrioridad<Traslado>(c2);

    @Test
    void nueva_cola_vacia() {
        assertEquals(true, colaGasto.vacía());
        assertEquals(true, colaTimeStamp.vacía());
    }

    @Test
    void encolar_elemento() {
        colaGasto.encolar(t1);
        colaTimeStamp.encolar(t1);
        assertEquals(t1, colaGasto.consultarMax());
        assertEquals(t1, colaTimeStamp.consultarMax());
        assertEquals(false, colaGasto.vacía());
        assertEquals(false, colaTimeStamp.vacía());

    }

    @Test
    void desencolar_maximo() {
        colaGasto.encolar(t1);
        colaGasto.encolar(t2);
        colaGasto.encolar(t3);

        colaTimeStamp.encolar(t1);
        colaTimeStamp.encolar(t2);
        colaTimeStamp.encolar(t3);

        assertEquals(t2, colaGasto.consultarMax());
        assertEquals(t1, colaTimeStamp.consultarMax());

        colaGasto.desencolarMax();
        colaTimeStamp.desencolarMax();
        assertEquals(t1, colaGasto.consultarMax());
        assertEquals(t3, colaTimeStamp.consultarMax());

        colaGasto.desencolarMax();
        colaTimeStamp.desencolarMax();

        assertEquals(t3, colaGasto.consultarMax());
        assertEquals(t2, colaTimeStamp.consultarMax());
    }

    @Test
    void desencolar_mas_dificil() {
        colaGasto.encolar(t1);
        colaTimeStamp.encolar(t1);
        colaGasto.encolar(t2);
        colaTimeStamp.encolar(t2);
        colaGasto.encolar(t3);
        colaTimeStamp.encolar(t3);
        colaGasto.encolar(t4);
        colaTimeStamp.encolar(t4);
        colaGasto.encolar(t5);
        colaTimeStamp.encolar(t5);
        colaGasto.encolar(t6);
        colaTimeStamp.encolar(t6);

        assertEquals(t4, colaGasto.consultarMax());
        assertEquals(t1, colaTimeStamp.consultarMax());

        colaGasto.desencolarMax();
        colaTimeStamp.desencolarMax();

        assertEquals(t6, colaGasto.consultarMax());
        assertEquals(t3, colaTimeStamp.consultarMax());

        colaGasto.desencolarMax();
        colaTimeStamp.desencolarMax();

        assertEquals(t5, colaGasto.consultarMax());
        assertEquals(t6, colaTimeStamp.consultarMax());

        colaGasto.desencolarMax();
        colaTimeStamp.desencolarMax();

        assertEquals(t2, colaGasto.consultarMax());
        assertEquals(t5, colaTimeStamp.consultarMax());

        colaGasto.desencolarMax();
        colaTimeStamp.desencolarMax();

        assertEquals(t1, colaGasto.consultarMax());
        assertEquals(t2, colaTimeStamp.consultarMax());
    }

}