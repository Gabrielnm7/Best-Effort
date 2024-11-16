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
        // O(T + C) porque se utizan 2 operaciones O(T), lo cual es O(T) y 1 O(C)
        this.ciudades = new Ciudad[cantCiudades];
        this.gananciaMayor = 0;
        this.perdidaMayor = 0;
        
        // ESTO ES O(C) porque se recorren todas las ciudades que hay y se les asigna un valor en O(1).
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
        // Se utiliza 2 el algoritmo de Floyd como constructor, el cual es O(T), 
        // y queda una complejidad de O(T) + O(T) = max (O(T), O(T)) = O(T)

        this.TrasladosPorCosto = nuevoGastos; 
        this.TrasladosPorTiempo = nuevoTiempo; 
    }

    public void registrarTraslados(Traslado[] traslados) {
        // O(T log T) por hacer una operación O(log T) T veces
        int i = 0;
        while (i < traslados.length){     // Se utiliza la operación encolar T veces
            this.TrasladosPorCosto.encolar(traslados[i]); // encolar es O(log T, y 2 veces encolar es O(log T) a su vez
            this.TrasladosPorTiempo.encolar(traslados[i]); 
            i++;
        }
    } 

    public int[] despacharMasRedituables(int n) {
        // O(n (log T + log C)) ya que se realizan n veces desencolar, que es O(log T) y despacharAux, que es O(log C)
        int veces = n;       
        int tamaño = this.TrasladosPorCosto.tamaño(); // O(1)
        boolean esMayor = false;
        if (n > tamaño){
            veces = tamaño;
            esMayor = true;
        }

        int[] resultado = new int[veces]; 
        while (veces != 0){// el ciclo se realiza n veces, dependiendo de lo pasado por parametro.
            Traslado encargo = this.TrasladosPorCosto.desencolarMax(); // O(log T)
            
            // Se elimina del heap de tiempo
            int indiceTiempo = encargo.obtenerHandler().getIndiceTiempo();  // O(1)
            this.TrasladosPorTiempo.eliminar(indiceTiempo); // O(log T)

            // Se actualizan las estadisticas generales
            this.estadisticasGrales.GananciaTotal += encargo.gananciaNeta;
            this.estadisticasGrales.DespachosTotales += 1;
            
            // Se actualizan las estadisticas de las ciudades
            ciudades[encargo.origen].Ganancia += encargo.gananciaNeta;
            ciudades[encargo.destino].Perdida += encargo.gananciaNeta;
        
            despacharAux(encargo); //O(1)
            
            // Se actualiza el superávit en el heap de ciudades
            superavitAux(ciudades[encargo.origen], ciudades[encargo.destino]); // O(log C)
            if (esMayor){
            resultado[tamaño - veces] = encargo.id;}
            else {
            resultado[n - veces] = encargo.id;
            }
            veces -= 1;
        }
    return resultado;
    }

    public int[] despacharMasAntiguos(int n) { 
        // Por exactamente las mismas razones que despachar más redituables es O(n (log T + log C))
        int veces = n;
        int tamaño = this.TrasladosPorTiempo.tamaño();
        boolean esMayor = false;
        if (n > tamaño){
            veces = tamaño;
            esMayor = true;
        }
        int[] resultado = new int[veces];
        
        while (veces != 0){
            Traslado encargo = this.TrasladosPorTiempo.desencolarMax();

            // Se elimina del heap de costo
            int indiceCosto = encargo.obtenerHandler().getIndiceCosto();
            this.TrasladosPorCosto.eliminar(indiceCosto);

            // Se actualizan las estadisticas generales
            this.estadisticasGrales.GananciaTotal += encargo.gananciaNeta;
            this.estadisticasGrales.DespachosTotales += 1;

            // Se actualizan las estadisticas de las ciudades
            ciudades[encargo.origen].Ganancia += encargo.gananciaNeta;
            ciudades[encargo.destino].Perdida += encargo.gananciaNeta;

            despacharAux(encargo);

            // Se actualiza el superávit en el heap de ciudades
            superavitAux(ciudades[encargo.origen], ciudades[encargo.destino]);

            if (esMayor){
                resultado[tamaño - veces] = encargo.id;
            }
            else {
                resultado[n-veces] = encargo.id;
            }
            veces -= 1;
        }

        return resultado;
    }

    public int ciudadConMayorSuperavit() {
        // O(1) porque devuelve el valor de un atributo interno.
        return this.CiudadMayorSuperavit.consultarMax().id;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia() {
        // O(1) porque devuelve el valor de un atributo interno.
        return this.CiudadesMayorGanancia;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() {
        // O(1) porque devuelve el valor de un atributo interno.
        return this.CiudadesMenorGanancia;
    }

    public int gananciaPromedioPorTraslado() {
        // O(1) porque devuelve el valor de un atributo interno.
        return this.estadisticasGrales.GananciaPromedio(); 
    }

    // Funciones auxiliares
    private void despacharAux(Traslado encargo){    // O(1) No hay ciclos y se usan todas operaciónes O(1)
        int GananciaOrigen = ciudades[encargo.origen].Ganancia;
        int PerdidaDestino = ciudades[encargo.destino].Perdida;
        
        if (GananciaOrigen > gananciaMayor){
            CiudadesMayorGanancia.clear(); // clear es O(1) por enunciado
            CiudadesMayorGanancia.add(encargo.origen); // add es O(1)
            gananciaMayor = GananciaOrigen;
        }
        else if (GananciaOrigen == gananciaMayor){
                CiudadesMayorGanancia.add(encargo.origen);
        }
        if(PerdidaDestino > perdidaMayor){
            CiudadesMenorGanancia.clear();
            CiudadesMenorGanancia.add(encargo.destino);
            perdidaMayor = PerdidaDestino;
        }
        else if (PerdidaDestino == perdidaMayor) {
            CiudadesMenorGanancia.add(encargo.destino);
        }
    }

    private void superavitAux(Ciudad ciudadOrigen, Ciudad ciudadDestino){
        // Cambiar prioridad tiene complejidad O(log C) -> O(log C) + O(log C) = O(log C)
        
        // Se actualiza el superávit para la ciudad de origen
        int indiceOrigen = ciudadOrigen.obtenerHandler().getIndiceSuperavit();
        CiudadMayorSuperavit.cambiarPrioridad(indiceOrigen, ciudadOrigen);

        // Se actualiza el superávit para la ciudad de destino
        int indiceDestino = ciudadDestino.obtenerHandler().getIndiceSuperavit();
        CiudadMayorSuperavit.cambiarPrioridad(indiceDestino, ciudadDestino);
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
