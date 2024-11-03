package aed;

import java.util.ArrayList;
<<<<<<< HEAD
import java.util.Comparator;
 
public class ColaDePrioridad<T> implements ColaPrioridad<T> {                    // VAMOS A IMPLEMETARLO CON UN ARRAY YA QUE DICEN QUE ES LA MEJOR FORMA / ME CONVIENE ARRAY O ARRAY LIST?
    private ArrayList<T> datos;
    private Comparator<T> comparador;
    
    public ColaDePrioridad(Comparator<T> c){
=======

public class ColaDePrioridad implements ColaPrioridad<Traslado> { // VAMOS A IMPLEMETARLO CON UN ARRAY YA QUE DICEN QUE
                                                                  // ES LA MEJOR FORMA / ME CONVIENE ARRAY O ARRAY LIST?
    private ArrayList<Traslado> datos;

    public ColaDePrioridad() {
>>>>>>> a15bf663fd58527bfb5100da84edc2cf2b06ce00
        this.datos = new ArrayList<>();
        this.comparador = c;
    }

    public boolean vacía() {
        return (this.datos.size() == 0);
    }

<<<<<<< HEAD
    public void encolar(T t){                // O(log n) ya que utilizo la funcion subir y add es O(1) para este TP
=======
    public void encolar(Traslado t) { // O(log n) ya que utilizo la funcion subir y add es O(1) para este TP
>>>>>>> a15bf663fd58527bfb5100da84edc2cf2b06ce00
        this.datos.add(t);
        this.subir(this.datos.size() - 1);

    }

    private void subir(int indice) {
        int i = indice;
<<<<<<< HEAD
        while (i != 0 && (comparador.compare(datos.get(i), (datos.get((i - 1)/2)))) > 0 ){         // TENEMOS QUE VER BIEN COMO ES LO DE COMPARAR ya que estoy comparando solamente con solo un parametro en este caso
            T ultimo = datos.get(i);
           datos.set(i, datos.get(( i-1)/2));                                       // Esta operación es O(log n) ya que no debo recorrer todos los elementos del arbol.
            datos.set(((i-1)/2), ultimo);       
            i = (i-1)/2; 
=======
        ComparadorPorGanancias comparador = new ComparadorPorGanancias();
        while (i != 0 && (comparador.compare(datos.get(i), (datos.get((i - 1) / 2)))) > 0) {
            Traslado ultimo = datos.get(i);
            datos.set(i, datos.get((i - 1) / 2)); // Esta operación es O(log n) ya que no debo recorrer todos los
                                                  // elementos del arbol.
            datos.set(((i - 1) / 2), ultimo);
            i = (i - 1) / 2;
>>>>>>> a15bf663fd58527bfb5100da84edc2cf2b06ce00
        }

    }

<<<<<<< HEAD
    public T desencolarMax(){
        T valor = datos.get(0);
        datos.set(0, datos.get(datos.size()));
        datos.remove(datos.size()-1);
        this.bajar(0);
        return valor;

    }

    private void bajar(int indice){
        int i = indice;
        while (!EsHoja(i) && (comparador.compare(datos.get(i*2+1),datos.get(i)) > 0 || comparador.compare(datos.get(i*2+2),datos.get(i)) > 0)){
            if (comparador.compare(datos.get(i*2+1), datos.get(i*2+2)) > 0){   // LO CAMBIO POR EL HIJO MAS GRANDE
                T valor = datos.get(i);
                datos.set(i, datos.get(i*2+1));
                datos.set(2*1+1, valor);
                i = i*2 + 1;
            }
            else if (comparador.compare(datos.get(i*2+1), datos.get(i*2+2)) < 0){
                T valor = datos.get(i);
                datos.set(i, datos.get(i*2+2));
                datos.set(2*1+2, valor);
                i = i*2 + 2;
            }

        }

    }

    private boolean EsHoja(int indice){
        return ((indice * 2 + 1) >= datos.size() && indice * 2 + 2 >= datos.size());
    }
    

    public void cambiarPrioridad (int i, T prioridad){
    T valorAnterior = datos.get(i);
        datos.set(i, prioridad);
    if (comparador.compare (valorAnterior, prioridad) < 0){   // Reordeno segun si es mayor o menos al elemento anterior
        subir(i);
    }
    else{
        bajar(i);
    }
    }

    public T consultarMax(){
    return datos.get(0);}

    public ColaDePrioridad ColaDePrioridadDesdeSecuencia(T[] secuencia);
=======
    // Saca el primer elemento (El de mayor prioridad) y reordena para mantener el
    // Invariante de representación.
    public Traslado desencolarMax() {
        // Implementar
        throw new UnsupportedOperationException("No implementada aun");
    };

    // Cambia la prioridad del elemento en la posicion i y reordena para mantener el
    // Invariante de representación.
    public void cambiarPrioridad(int i, Traslado prioridad) {
        // Implementar
        throw new UnsupportedOperationException("No implementada aun");
    };

    // Devuelve el elemento de máxima prioridad sin cambiar nada.
    public Traslado consultarMax() {
        // Implementar
        throw new UnsupportedOperationException("No implementada aun");
    };

>>>>>>> a15bf663fd58527bfb5100da84edc2cf2b06ce00
    // Agarra una secuencia la reordena y la convierte en un tipo ColaPrioridad.
    public ColaPrioridad<Traslado> ColaDePrioridadDesdeSecuencia(Traslado[] secuencia) {
        // Implementar
        throw new UnsupportedOperationException("No implementada aun");
    };

}
