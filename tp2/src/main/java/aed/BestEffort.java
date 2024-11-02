package aed;

import java.util.ArrayList;

public class BestEffort {
    private Heap TrasladosPorTiempo; // TENEMOS QUE HACER LA CLASE HEAP    // No se si habra que hacer 2 heaps, uno por cada orden o con uno y algun truco ya estaría.
    private Heap TrasladosPorCosto;
    private Ciudad[] ciudades;
    private int CiudadConMayorGanancia;
    private int CiudadConMenorGanancia;
    private int CiudadConMayorSuperavit;
    private int gananciaTotal;

    private class Traslado{
        int Id;
        int origen;
        int destino;
        int gananciaNeta;
        int timeStamp;
        Traslado(int nombre){ Id = nombre;}
    }

    private class Ciudad{
        int Ganancia;
        int Perdida;
    }
  

    public BestEffort(int cantCiudades, Traslado[] traslados){  
        this.ciudades = new Ciudad[cantCiudades];
        int i = 0;
        while (i < cantCiudades){
            ciudades[i].Ganancia = 0;                 // ESTO ES O(T) porque recorro todas las ciudades que hay y es asigno un valor.
            ciudades[i].Perdida = 0;    
        }
        CiudadConMayorGanancia = 0;                // ME QUEDO CON EL DE MENOR INDICE PORQUE LA GANANCIA Y PERDIDA DE TODOS ES 0
        CiudadConMayorSuperavit = 0;
        CiudadConMenorGanancia = 0;
        int j = 0;
        this.TrasladosPorCosto = heapify(traslados); //Orden en base al costo     // HEAPIFY ES O(T), con esto cumpliria con la complejidad de la consigna O(T + C)
        this.TrasladosPorTiempo = heapify(traslados); //Orden n base al tiempo
    }
    }
    public void registrarTraslados(Traslado[] traslados){
        // Implementar
    }


   /*  private void despacharAux(Traslado[] traslados){ 
        int i = 0;
        while (i < traslados.length){                                                      // ESTA FUNCION SERIA PARA MODIFICAR LAS ESTADISTICAS DE LA CIUDAD DE ACUERDO A LOS DESPACHOS REALIZADOS
        int cityOrigen =  traslados[i].origen;                                             // ES O(T)
        int cityDestino = traslados[i].destino;
        ciudades[cityOrigen].Ganancia += traslados[i].gananciaNeta;
        ciudades[cityDestino].Perdida += traslados[i].gananciaNeta;
        gananciaTotal += traslados[i].gananciaNeta;
        if (ciudades[cityOrigen].Ganancia > ciudades[CiudadConMayorGanancia].Ganancia){
            CiudadConMayorGanancia = cityOrigen;
        }
        else if (ciudades[cityOrigen].Ganancia == ciudades[CiudadConMayorGanancia].Ganancia){
            if(cityOrigen > CiudadConMayorGanancia);
            CiudadConMayorGanancia = cityOrigen;
        }
        if (ciudades[cityDestino].Perdida > ciudades[CiudadConMenorGanancia].Perdida){
            CiudadConMenorGanancia = cityDestino;
        }
        else if (ciudades[cityDestino].Perdida == ciudades[CiudadConMenorGanancia].Perdida){
            if(cityDestino < CiudadConMayorGanancia);
            CiudadConMayorGanancia = cityDestino;
        }
        if (ciudades[cityOrigen].Ganancia - ciudades[cityOrigen].Perdida > ciudades[CiudadConMayorSuperavit].Ganancia - ciudades[CiudadConMayorSuperavit].Perdida){
            CiudadConMayorSuperavit = cityOrigen;
        }
        else if (ciudades[cityOrigen].Ganancia - ciudades[cityOrigen].Perdida == ciudades[CiudadConMayorSuperavit].Ganancia - ciudades[CiudadConMayorSuperavit].Perdida){
            if (cityOrigen < CiudadConMayorSuperavit){
                CiudadConMayorSuperavit = cityOrigen;
            }
        }
        if (ciudades[cityOrigen].Ganancia - ciudades[cityOrigen].Perdida > ciudades[CiudadConMayorSuperavit].Ganancia - ciudades[CiudadConMayorSuperavit].Perdida){
                CiudadConMayorSuperavit = cityOrigen;
            }
        else if (ciudades[cityDestino].Ganancia - ciudades[cityDestino].Perdida == ciudades[CiudadConMayorSuperavit].Ganancia - ciudades[CiudadConMayorSuperavit].Perdida){
                if (cityDestino < CiudadConMayorSuperavit){
                    CiudadConMayorSuperavit = cityDestino;
                }
        }
    }
    }
*/
    public int[] despacharMasRedituables(int n){
        // Implementar
        return null;
    }

    public int[] despacharMasAntiguos(int n){
        // Implementar
        return null;
    }

    public int ciudadConMayorSuperavit(){
        // Implementar
        return 0;
    }

    public ArrayList<Integer> ciudadesConMayorGanancia(){
        // Implementar
        return null;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida(){
        // Implementar
        return null;
    }

    public int gananciaPromedioPorTraslado(){
        // Implementar
        return 0;
    }
    
}
