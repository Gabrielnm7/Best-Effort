package aed;

import java.util.ArrayList;
import aed.*; // Importo todo de aed para poder usar las clases que necesito

public class BestEffort {
    private ColaDePrioridad<Traslado> TrasladosPorTiempo;
    private ColaDePrioridad<Traslado> TrasladosPorCosto;
    private Ciudad[] ciudades;
    private ArrayList<Integer> CiudadesMayorGanancia;
    private ArrayList<Integer> CiudadesMenorGanancia;
    private int CiudadMayorSuperavit;

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
        Traslado[] guardados = new Traslado[n];
        int[] resultado = new int[n];
        while (veces != 0){
            Traslado encargo = this.TrasladosPorCosto.desencolarMax();
            // Tambien deberia sacarlo de TrasladosPorTiempo -> IDEA: handlers

            this.estadisticasGrales.GananciaTotal += encargo.gananciaNeta;
            this.estadisticasGrales.DespachosTotales += 1;

            encargo.origen += ciudades[encargo.origen].Ganancia;
            encargo.destino += ciudades[encargo.destino].Perdida;
            // ESTO ES PARA USAR EN UNA FUNCION AUXILIAR Y MODIFICAR LOS ATRIBUTOS DE LAS ESTADISTICAS DE CIUDADES EN BASE A COMO CAMBIARON EN ESTA OPERACION
            guardados [n-veces] = encargo; 
            resultado[n-veces] = encargo.id;
            veces -= 1;
        }
        despacharAux(guardados);
        return resultado;
    }

    private void despacharAux(Traslado[] guardados){        // COMPLEJIDAD????
        
        int valorMaximoGanancia;
        if (CiudadesMayorGanancia.size() != 0){
        valorMaximoGanancia = ciudades[CiudadesMayorGanancia.get(0)].Ganancia;
        }
        else{
        valorMaximoGanancia = 0;
        }
        int i = 0;
        while (i < guardados.length){
            if ((ciudades[guardados[i].origen].Ganancia) > valorMaximoGanancia){
                CiudadesMayorGanancia.clear();
                valorMaximoGanancia = ciudades[guardados[i].origen].Ganancia;
                CiudadesMayorGanancia.add(guardados[i].origen);
            }
            else if (((ciudades[guardados[i].origen].Ganancia) == valorMaximoGanancia) && CiudadesMayorGanancia.contains(guardados[i].origen)){ 
                CiudadesMayorGanancia.add(guardados[i].origen);
            }
        
        }
        
        int valorMaximoPerdida;
        
        if (CiudadesMenorGanancia.size() != 0){
        valorMaximoPerdida = ciudades[CiudadesMenorGanancia.get(0)].Ganancia;
        }
        else{
        valorMaximoPerdida = 0;
        }
        int j = 0;
        while (j < guardados.length){
            if ((ciudades[guardados[j].origen].Perdida) > valorMaximoPerdida){
                CiudadesMenorGanancia.clear();
                valorMaximoPerdida = ciudades[guardados[j].destino].Perdida;
                CiudadesMenorGanancia.add(guardados[j].destino);
            }
            else if (((ciudades[guardados[j].destino].Perdida) == valorMaximoPerdida) && CiudadesMenorGanancia.contains(guardados[j].destino)){
                CiudadesMenorGanancia.add(guardados[j].destino);
            }
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
