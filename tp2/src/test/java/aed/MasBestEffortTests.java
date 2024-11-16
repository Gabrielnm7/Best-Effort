package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class MasBestEffortTests {

    int cantCiudades;
    Traslado[] listaTraslados;
    ArrayList<Integer> actual;


    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 7;
        listaTraslados = new Traslado[] {
                                            new Traslado(1, 0, 1, 100, 10),
                                            new Traslado(2, 0, 1, 400, 20),
                                            new Traslado(3, 3, 4, 500, 50),
                                            new Traslado(4, 4, 3, 500, 11),
                                            new Traslado(5, 1, 0, 1000, 40),
                                            new Traslado(6, 1, 0, 1000, 41),
                                            new Traslado(7, 6, 3, 2000, 42)
                                        };
    }

    @Test
    void despacharMasRedituablesDeAUnoTest(){
        BestEffort b = new BestEffort(cantCiudades, listaTraslados);
        int[] devuelve1 = b.despacharMasRedituables(1);
        assertEquals(devuelve1[0], 7);

        int[] devuelve2 = b.despacharMasRedituables(1);
        assertEquals(devuelve2[0], 5);

        int[] devuelve3 = b.despacharMasRedituables(1);
        assertEquals(devuelve3[0], 6);

        int[] devuelve4 = b.despacharMasRedituables(1);
        assertEquals(devuelve4[0], 3);

        int[] devuelve5 = b.despacharMasRedituables(1);
        assertEquals(devuelve5[0], 4);

        int[] devuelve6 = b.despacharMasRedituables(1);
        assertEquals(devuelve6[0], 2);

        int[] devuelve7 = b.despacharMasRedituables(1);
        assertEquals(devuelve7[0], 1);
    }
    @Test
    void despacharMasRedituablesDeAVariosTest(){
        BestEffort b = new BestEffort(cantCiudades, listaTraslados);
        int[] devuelve1 = b.despacharMasRedituables(3);
        int[] res1 = new int[]{7, 5, 6};
        int i = 0;
        while (i != devuelve1.length){
            assertEquals(devuelve1[i], res1[i]);
            i++;

        }
        int[] devuelve2 = b.despacharMasRedituables(4);
        int[] res2 = new int[]{3, 4, 2, 1};
        i = 0;
        while (i != devuelve2.length){
            assertEquals(devuelve2[i], res2[i]);
            i++;
        }
      }
        @Test 
        void despacharPorGananciaMasDelTamaño(){
            BestEffort b = new BestEffort(cantCiudades, listaTraslados);
            int[] devuelve = b.despacharMasRedituables(9);
            int[] res = new int[]{7, 5, 6, 3, 4, 2, 1};
            int i = 0;
            while (i != devuelve.length){
                assertEquals(devuelve[i], res[i]);
                i++;
              }
        }
        @Test
        void despacharMasAntiguosDeAUnoTest(){
            BestEffort b = new BestEffort(cantCiudades, listaTraslados);
            int[] devuelve1 = b.despacharMasAntiguos(1);
            assertEquals(devuelve1[0], 1);
    
            int[] devuelve2 = b.despacharMasAntiguos(1);
            assertEquals(devuelve2[0], 4);
    
            int[] devuelve3 = b.despacharMasAntiguos(1);
            assertEquals(devuelve3[0], 2);
    
            int[] devuelve4 = b.despacharMasAntiguos(1);
            assertEquals(devuelve4[0], 5);
    
            int[] devuelve5 = b.despacharMasAntiguos(1);
            assertEquals(devuelve5[0], 6);
    
            int[] devuelve6 = b.despacharMasAntiguos(1);
            assertEquals(devuelve6[0], 7);
    
            int[] devuelve7 = b.despacharMasAntiguos(1);
            assertEquals(devuelve7[0], 3);
        }
        @Test
        void despacharMasAntiguosDeAVariosTest(){
            BestEffort b = new BestEffort(cantCiudades, listaTraslados);
            int[] devuelve1 = b.despacharMasAntiguos(3);
            int[] res1 = new int[]{1, 4, 2};
            int i = 0;
            while (i != devuelve1.length){
                assertEquals(devuelve1[i], res1[i]);
                i++;
    
            }
            int[] devuelve2 = b.despacharMasAntiguos(4);
            int[] res2 = new int[]{5, 6, 7, 3};
            i = 0;
            while (i != devuelve2.length){
                assertEquals(devuelve2[i], res2[i]);
                i++;
            }
          }
            @Test 
            void despacharPorTiempoMasDelTamaño(){
                BestEffort b = new BestEffort(cantCiudades, listaTraslados);
                int[] devuelve = b.despacharMasAntiguos(9);
                int[] res = new int[]{1, 4, 2, 5, 6, 7, 3};
                int i = 0;
                while (i != devuelve.length){
                    assertEquals(devuelve[i], res[i]);
                    i++;
                  }
            }



      }

      