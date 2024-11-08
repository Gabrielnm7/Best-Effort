package aed;

import java.util.ArrayList;

import javax.crypto.Mac;

import aed.*; // Importo todo de aed para poder usar las clases que necesito

public class BestEffort {
    private ColaDePrioridad<Traslado> TrasladosPorTiempo;
    private ColaDePrioridad<Traslado> TrasladosPorCosto;
    private Ciudad[] ciudades;
    private ArrayList<Integer> CiudadesMayorGanancia;
    private ArrayList<Integer> CiudadesMenorGanancia;
    private int CiudadMayorSuperavit;
    private int gananciaMayor;
    private int perdidaMayor;

    private class EstadisticasGrales {
        int GananciaTotal;
        int DespachosTotales;

        public EstadisticasGrales(){
            GananciaTotal = 0;
            DespachosTotales = 0;
        }

        public int GananciaPromedio(){
            if (this.DespachosTotales == 0){
                return 0;
            }
            else{
                return this.GananciaTotal/this.DespachosTotales;
            }
        }
    } 

    private EstadisticasGrales estadisticasGrales;

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
        this.gananciaMayor = 0;
        this.perdidaMayor = 0;
        // ESTO ES O(C) porque recorro todas las ciudades que hay y le asigno un valor.
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad();
        }
        
        CiudadesMayorGanancia = new ArrayList<Integer>(); 
        CiudadesMenorGanancia = new ArrayList<Integer>();
        CiudadMayorSuperavit = 0;
        estadisticasGrales = new EstadisticasGrales();
        ComparadorPorGanancias comparadorGanancia = new ComparadorPorGanancias();
        ComparadorPorTiempo comparadorTiempo = new ComparadorPorTiempo();
        ColaDePrioridad<Traslado> nuevoGastos = new ColaDePrioridad<Traslado>(traslados,comparadorGanancia);
        ColaDePrioridad<Traslado> nuevoTiempo = new ColaDePrioridad<Traslado>(traslados,comparadorTiempo);

        this.TrasladosPorCosto = nuevoGastos; // Orden en base al costo //
                                            // HEAPIFY ES O(T), con esto
                                            // cumpliria con la complejidad
                                            // de la consigna O(T + C)
        this.TrasladosPorTiempo = nuevoTiempo; // Orden n base al tiempo
    }

    public void registrarTraslados(Traslado[] traslados) {
        int i = 0;
        while (i < traslados.length){
            this.TrasladosPorCosto.encolar(traslados[i]); 
            this.TrasladosPorTiempo.encolar(traslados[i]);
        }
    } // Cumplimos con O(|traslados| * log |T|) 

    public int[] despacharMasRedituables(int n) {
        int veces = n;
        int[] resultado = new int[n];
        while (veces != 0){
            Traslado encargo = this.TrasladosPorCosto.desencolarMax();
            // Tambien deberia sacarlo de TrasladosPorTiempo -> IDEA: handlers

            this.estadisticasGrales.GananciaTotal += encargo.gananciaNeta;
            this.estadisticasGrales.DespachosTotales += 1;

            encargo.origen += ciudades[encargo.origen].Ganancia;
            encargo.destino += ciudades[encargo.destino].Perdida;
            despacharAux(encargo);
            resultado[n-veces] = encargo.id;
            veces -= 1;
        }
        return resultado;
    }

    private void despacharAux(Traslado encargo){   
        if (ciudades[encargo.origen].Ganancia > gananciaMayor){
            CiudadesMayorGanancia.clear();
            CiudadesMayorGanancia.add(encargo.origen);
            gananciaMayor = ciudades[encargo.origen].Ganancia;}
            else if (ciudades[encargo.origen].Ganancia == gananciaMayor){
                CiudadesMayorGanancia.add(encargo.origen);
            }
        if(ciudades[encargo.destino].Perdida > perdidaMayor){
            CiudadesMenorGanancia.clear();
            CiudadesMenorGanancia.add(encargo.destino);
            perdidaMayor = ciudades[encargo.destino].Perdida;
        }
        else if (ciudades[encargo.destino].Perdida == perdidaMayor) {
            CiudadesMenorGanancia.add(encargo.destino);
        }

        }
    

    public int[] despacharMasAntiguos(int n) {
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit() {
        return this.CiudadMayorSuperavit;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() {
        return this.CiudadesMayorGanancia;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() {
        return this.CiudadesMenorGanancia;
    }

    public int gananciaPromedioPorTraslado() {
        return this.estadisticasGrales.GananciaPromedio(); 
    }

}
