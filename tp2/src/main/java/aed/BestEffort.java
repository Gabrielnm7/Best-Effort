package aed;

import java.util.ArrayList;

public class BestEffort {
    private Heap TrasladosPorTiempo; // TENEMOS QUE HACER LA CLASE HEAP
    private Heap TrasladosPorCosto;
    private Ciudad[] ciudades;
    private int CiudadConMayorGanancia;
    private int CiudadConMenorGanancia;
    private int CiudadConMayorSuperavit;

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
        int  MayorId = 0;
        while (i < cantCiudades){
            ciudades[i].Ganancia = 0;
            ciudades[i].Perdida = 0;
        }
        CiudadConMayorGanancia = cantCiudades-1;
        CiudadConMayorSuperavit = cantCiudades-1;
        CiudadConMenorGanancia = cantCiudades-1;
        int j = 0;
        while (j < traslados.length){
            int cityOrigen =  traslados[i].origen;
            int cityDestino = traslados[i].destino;
            ciudades[cityOrigen].Ganancia += traslados[i].gananciaNeta;
            ciudades[cityDestino].Perdida += traslados[i].gananciaNeta;
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
                if(cityDestino > CiudadConMayorGanancia);
                CiudadConMayorGanancia = cityDestino;
            }
            if (ciudades[cityOrigen].Ganancia - ciudades[cityOrigen].Perdida > ciudades[CiudadConMayorSuperavit].Ganancia - ciudades[CiudadConMayorSuperavit].Perdida){
                CiudadConMayorSuperavit = cityOrigen;
            }
            else if (ciudades[cityOrigen].Ganancia - ciudades[cityOrigen].Perdida == ciudades[CiudadConMayorSuperavit].Ganancia - ciudades[CiudadConMayorSuperavit].Perdida){
                if (cityOrigen > CiudadConMayorSuperavit){
                    CiudadConMayorSuperavit = cityOrigen;
                }
            }
            if (ciudades[cityOrigen].Ganancia - ciudades[cityOrigen].Perdida > ciudades[CiudadConMayorSuperavit].Ganancia - ciudades[CiudadConMayorSuperavit].Perdida){
                    CiudadConMayorSuperavit = cityOrigen;
                }
            else if (ciudades[cityDestino].Ganancia - ciudades[cityDestino].Perdida == ciudades[CiudadConMayorSuperavit].Ganancia - ciudades[CiudadConMayorSuperavit].Perdida){
                    if (cityDestino > CiudadConMayorSuperavit){
                        CiudadConMayorSuperavit = cityDestino;
                    }
                j ++;
            }

            TrasladosPorTiempo.insertar(traslados[j]);
            TrasladosPorCosto.insertar(traslados[j]);        
            j++;
    }
    }
    public void registrarTraslados(Traslado[] traslados){
        // Implementar
    }

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
