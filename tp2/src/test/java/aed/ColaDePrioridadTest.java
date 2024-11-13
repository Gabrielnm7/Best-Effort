package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ColaDePrioridadTest {

    Traslado t1;
    Traslado t2;
    Traslado t3;
    Traslado t4;
    Traslado t5;
    Traslado t6;

    @BeforeEach
    void init(){
        t1 = new Traslado(1, 2, 3, 4, 5);
        t2 = new Traslado(11, 3, 2, 200, 7);
        t3 = new Traslado(3, 3, 1, 4, 5);
        t4 = new Traslado(34, 1, 2, 300, 10);
        t5 = new Traslado(10, 2, 1, 200, 5);
        t6 = new Traslado(9, 2, 1, 200, 5);
    }
    ComparadorPorGanancias c1 = new ComparadorPorGanancias();
    ComparadorPorTiempo c2 = new ComparadorPorTiempo();
    ColaDePrioridad<Traslado> cola1 = new ColaDePrioridad<Traslado>(c1);
    ColaDePrioridad<Traslado> cola2 = new ColaDePrioridad<Traslado>(c2);


    //Comparator<> c = new Comparator<T>();


    @Test
    void nueva_cola_vacia(){
        assertEquals(true, cola1.vacía());
        assertEquals(true, cola2.vacía());
    }

    @Test
    void encolar_elemento(){
        cola1.encolar(t1);
        cola2.encolar(t1);
        assertEquals(t1, cola1.consultarMax());
        assertEquals(t1, cola2.consultarMax());
        assertEquals(false, cola1.vacía());
        assertEquals(false, cola2.vacía());

    }

    @Test
    void desencolar_maximo(){
        cola1.encolar(t1);
        cola2.encolar(t1);
        cola1.encolar(t2);
        cola2.encolar(t2);
        cola1.encolar(t3);
        cola2.encolar(t3);

        assertEquals(t2, cola1.consultarMax());
        assertEquals(t1, cola2.consultarMax());

        cola1.desencolarMax();
        cola2.desencolarMax();
     //   assertEquals(2, cola1.datos.size());
      //  assertEquals(3, cola2.datos.size());
       // assertEquals(2, cola2.datos.size());
      //  assertEquals(false, cola1.vacía());
      //  assertEquals(false, cola2.vacía());
        assertEquals(t1, cola1.consultarMax());
        assertEquals(t3, cola2.consultarMax());

        cola1.desencolarMax();
        cola2.desencolarMax();

        assertEquals(t3, cola1.consultarMax());
        assertEquals(t2, cola2.consultarMax());
    } 

    @Test
    void desencolar_mas_dificil(){
        cola1.encolar(t1);
        cola2.encolar(t1);
        cola1.encolar(t2);
        cola2.encolar(t2);
        cola1.encolar(t3);
        cola2.encolar(t3);
        cola1.encolar(t4);
        cola2.encolar(t4);
        cola1.encolar(t5);
        cola2.encolar(t5);
        cola1.encolar(t6);
        cola2.encolar(t6);

        assertEquals(t4, cola1.consultarMax());
        assertEquals(t1, cola2.consultarMax());

        cola1.desencolarMax();
        cola2.desencolarMax();

        assertEquals(t6, cola1.consultarMax());
        assertEquals(t3, cola2.consultarMax());

        cola1.desencolarMax();
        cola2.desencolarMax();

        assertEquals(t5, cola1.consultarMax());
        assertEquals(t6, cola2.consultarMax());

        cola1.desencolarMax();
        cola2.desencolarMax();

        assertEquals(t2, cola1.consultarMax());
        assertEquals(t5, cola2.consultarMax());

        cola1.desencolarMax();
        cola2.desencolarMax();

        assertEquals(t1, cola1.consultarMax());
        assertEquals(t2, cola2.consultarMax());
    }

}