package aed;

import java.util.ArrayList;
import aed.ColaDePrioridad;
import aed.ComparadorPorGanancias;
import aed.ComparadorPorTiempo;
import aed.Traslado;

public class BestEffort {
    private ColaDePrioridad TrasladosPorTiempo;
    private ColaDePrioridad TrasladosPorCosto;
    private Ciudad[] ciudades;
    private int CiudadConMayorGanancia;
    private int CiudadConMenorGanancia;
    private int CiudadConMayorSuperavit;
    private int gananciaTotal;

    private class Ciudad { // CON ESTO VAMOS A LLEVAR UN REGISTRO DE LAS ESTADISTICAS DE CADA CIUDAD.
        int Ganancia;
        int Perdida;

        // Hago un constructor para inicializar las variables
        public Ciudad() {
            Ganancia = 0;
            Perdida = 0;
        }
    }

    public BestEffort(int cantCiudades, Traslado[] traslados) {
        this.ciudades = new Ciudad[cantCiudades];
        // ESTO ES O(T) porque recorro todas las ciudades que hay y le asigno un valor.
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad();
        }

        CiudadConMayorGanancia = 0; // ME QUEDO CON EL DE MENOR INDICE PORQUE LA GANANCIA Y PERDIDA DE TODOS ES 0
        CiudadConMayorSuperavit = 0;
        CiudadConMenorGanancia = 0;
        gananciaTotal = 0;
        ComparadorPorGanancias comparadorGanancia = new ComparadorPorGanancias();
        ComparadorPorTiempo comparadorTiempo = new ComparadorPorTiempo();
        ColaDePrioridad<Traslado> nuevoGastos = new ColaDePrioridad<Traslado>(comparadorGanancia);
        ColaDePrioridad<Traslado> nuevoTiempo = new ColaDePrioridad<Traslado>(comparadorTiempo);

        this.TrasladosPorCosto = nuevoGastos.ColaDePrioridadDesdeSecuencia(traslados); // Orden en base al costo //
                                                                                       // HEAPIFY ES O(T), con esto
                                                                                       // cumpliria con la complejidad
                                                                                       // de la consigna O(T + C)
        this.TrasladosPorTiempo = nuevoTiempo.ColaDePrioridadDesdeSecuencia(traslados); // Orden n base al tiempo
    }


    public void registrarTraslados(Traslado[] traslados) {
        int i = 0;
        while (i < traslados.length){
            this.TrasladosPorCosto.encolar(traslados[i]);
            this.TrasladosPorTiempo.encolar(traslados[i]);
        }
    }

    public int[] despacharMasRedituables(int n) {
        // Implementar
        return null;
    }

    public int[] despacharMasAntiguos(int n) {
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit() {
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() {
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() {
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado() {
        // Implementar
        return 0;
    }

}
