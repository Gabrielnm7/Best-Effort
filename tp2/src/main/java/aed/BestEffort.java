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
    private ArrayList<Integer> CiudadesMayorGanancia;
    private ArrayList<Integer> CiudadesMayorPerdida;
    private int CiudadMayorSuperavit;
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
        CiudadesMayorGanancia = new ArrayList<Integer>(); 
        CiudadMayorSuperavit = 0;
        CiudadesMayorPerdida = new ArrayList<Integer>();
        gananciaTotal = 0;
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad();
            CiudadesMayorGanancia.add(i);             
            CiudadesMayorPerdida.add(i);
        }
        ComparadorPorGanancias comparadorGanancia = new ComparadorPorGanancias();
        ComparadorPorTiempo comparadorTiempo = new ComparadorPorTiempo();
        ColaDePrioridad<Traslado> nuevoGastos = new ColaDePrioridad<Traslado>(traslados, comparadorGanancia);
        ColaDePrioridad<Traslado> nuevoTiempo = new ColaDePrioridad<Traslado>(traslados, comparadorTiempo);
        
                                                                                       // HEAPIFY ES O(T), con esto
                                                                                       // cumpliria con la complejidad
                                                                                       // de la consigna O(T + C) 
    }



    public void registrarTraslados(Traslado[] traslados) {
        int i = 0;
        while (i < traslados.length){
            this.TrasladosPorCosto.encolar(traslados[i]);
            this.TrasladosPorTiempo.encolar(traslados[i]);
        }
    }

    public int[] despacharMasRedituables(int n) {
        int veces = n;
        Traslado[] guardados = new Traslado[n];
        int[] resultado = new int[n];
        while (veces != 0){
            Traslado encargo = this.TrasladosPorCosto.desencolarMax();
            // TAMBIEN DEBERIA SACAR LOS DE LA COLA DE TIEMPO TENEMOS QUE VER COMO HACER
            this.gananciaTotal += encargo.gananciaNeta;
            encargo.origen += ciudades[encargo.origen].Ganancia;
            encargo.destino += ciudades[encargo.destino].Perdida;
            guardados [n-veces] = encargo;            // ESTO ES PARA USAR EN UNA FUNCION AUXILIAR Y MODIFICAR LOS ATRIBUTOS DE LAS ESTADISTICAS DE CIUDADES EN BASE A COMO CAMBIARON EN ESTA OPERACION
            resultado[n-veces] = encargo.id;
            veces -= 1;
        }
        despacharAuxEstadisticasCiudades(guardados);
        return resultado;
    }

    private void despacharAuxEstadisticasCiudades(Traslado[] cambiar){       // FUNCION AUXILIAR PARA MODIFICAR ESTADISTICAS DE CIUDADES

    }

    public int[] despacharMasAntiguos(int n) {
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit() {
        return this.CiudadMayorSuperavit;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() {
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
