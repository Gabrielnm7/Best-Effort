package aed;

import java.util.ArrayList;
 
public class ColaDePrioridad implements ColaPrioridad<Traslado> {                    // VAMOS A IMPLEMETARLO CON UN ARRAY YA QUE DICEN QUE ES LA MEJOR FORMA / ME CONVIENE ARRAY O ARRAY LIST?
    private ArrayList<Traslado> datos;
    
    public ColaDePrioridad(){
        this.datos = new ArrayList<>();
    }

    public boolean vacía(){
        return (this.datos.size() == 0);
    }

    public void encolar(Traslado t){                // O(log n) ya que utilizo la funcion subir y add es O(1) para este TP
        this.datos.add(t);
        this.subir(this.datos.size()-1);

    }

    private void subir(int indice){
        int i = indice;
        ComparadorPorGanancias comparador = new ComparadorPorGanancias();
        while (i != 0 && (comparador.compare(datos.get(i), (datos.get((i - 1)/2)))) > 0 ){       
            Traslado ultimo = datos.get(i);
            datos.set(i, datos.get((i-1)/2));                                       // Esta operación es O(log n) ya que no debo recorrer todos los elementos del arbol.
            datos.set(((i-1)/2), ultimo);
            i = (i-1)/2; 
        }
    
    }

    public Traslado desencolarMax();
    // Saca el primer elemento (El de mayor prioridad) y reordena para mantener el Invariante de representación.

    public void cambiarPrioridad (int i, Traslado prioridad);
    // Cambia la prioridad del elemento en la posicion i y reordena para mantener el Invariante de representación.

    public Traslado consultarMax();
    // Devuelve el elemento de máxima prioridad sin cambiar nada.

    public ColaPrioridad<Traslado> ColaDePrioridadDesdeSecuencia(Traslado[] secuencia);
    // Agarra una secuencia la reordena y la convierte en un tipo ColaPrioridad.


}
