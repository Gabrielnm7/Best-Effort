package aed;

import java.util.ArrayList;

public class BestEffort {
    private ColaDePrioridad<Traslado> TrasladosPorTiempo;
    private ColaDePrioridad<Traslado> TrasladosPorCosto;
    private Ciudad[] ciudades;
    private ArrayList<Integer> CiudadesMayorGanancia;
    private ArrayList<Integer> CiudadesMenorGanancia;
    private ColaDePrioridad<Ciudad> CiudadMayorSuperavit;
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

    public BestEffort(int cantCiudades, Traslado[] traslados) {
        this.ciudades = new Ciudad[cantCiudades];
        this.gananciaMayor = 0;
        this.perdidaMayor = 0;
        
        // ESTO ES O(C) porque recorro todas las ciudades que hay y le asigno un valor.
        for (int i = 0; i < cantCiudades; i++) {
            ciudades[i] = new Ciudad(i);
        }
        
        CiudadesMayorGanancia = new ArrayList<Integer>(); 
        CiudadesMenorGanancia = new ArrayList<Integer>();
        estadisticasGrales = new EstadisticasGrales();

        ComparadorSuperavit ComparadorSuperavit = new ComparadorSuperavit();
        CiudadMayorSuperavit = new ColaDePrioridad<Ciudad>(ciudades, ComparadorSuperavit);
        
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
            i++;
        }
    } // Cumplimos con O(|traslados| * log |T|) 

    public int[] despacharMasRedituables(int n) {
        int veces = n;
        int[] resultado = new int[n];
        while (veces != 0){
            Traslado encargo = this.TrasladosPorCosto.desencolarMax();
            
            // Lo elimino del heap de tiempo
            int indiceTiempo = encargo.obtenerHandler().getIndiceTiempo();
            this.TrasladosPorTiempo.eliminar(indiceTiempo);

            // Actualizo las estadisticas generales
            this.estadisticasGrales.GananciaTotal += encargo.gananciaNeta;
            this.estadisticasGrales.DespachosTotales += 1;
            
            // Actualizo las estadisticas de las ciudades
            ciudades[encargo.origen].Ganancia += encargo.gananciaNeta;
            ciudades[encargo.destino].Perdida += encargo.gananciaNeta;
        
            despacharAux(encargo);
            
            // Actualizamos el superávit en el heap de ciudades
            superavitAux(ciudades[encargo.origen], ciudades[encargo.destino]);

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
        int veces = n;
        int[] resultado = new int[n];
        
        while (veces != 0){
            Traslado encargo = this.TrasladosPorTiempo.desencolarMax();

            // Lo elimino del heap de costo
            int indiceCosto = encargo.obtenerHandler().getIndiceCosto();
            this.TrasladosPorCosto.eliminar(indiceCosto);

            // Actualizo las estadisticas generales
            this.estadisticasGrales.GananciaTotal += encargo.gananciaNeta;
            this.estadisticasGrales.DespachosTotales += 1;

            // Actualizo las estadisticas de las ciudades
            ciudades[encargo.origen].Ganancia += encargo.gananciaNeta;
            ciudades[encargo.destino].Perdida += encargo.gananciaNeta;

            despacharAux(encargo);

            // Actualizamos el superávit en el heap de ciudades
            superavitAux(ciudades[encargo.origen], ciudades[encargo.destino]);

            resultado[n-veces] = encargo.id;
            veces -= 1;
        }

        return resultado;
    }
    // Cambiar prioridad tiene complejidad O(log n) -> O(log n) + O(log n) = O(log n)
    private void superavitAux(Ciudad ciudadOrigen, Ciudad ciudadDestino){
        
        // Actualizamos el superávit para la ciudad de origen
        int indiceOrigen = ciudadOrigen.obtenerHandler().getIndiceSuperavit();
        CiudadMayorSuperavit.cambiarPrioridad(indiceOrigen, ciudadOrigen);

        // Actualizamos el superávit para la ciudad de destino
        int indiceDestino = ciudadDestino.obtenerHandler().getIndiceSuperavit();
        CiudadMayorSuperavit.cambiarPrioridad(indiceDestino, ciudadDestino);
    }

    public int ciudadConMayorSuperavit() {
        return this.CiudadMayorSuperavit.consultarMax().id;
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

    @Override
    public String toString(){ // Para testear
        String res = "";
        // res += "Heap de traslados por costo: " + this.TrasladosPorCosto.toString() + "\n";
        res += "Heap de traslados por tiempo: " + this.TrasladosPorTiempo.toString() + "\n";
        res += "Heap de ciudades con mayor superavit: " + this.CiudadMayorSuperavit.toString() + "\n";

        return res;
    }
}
