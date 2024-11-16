package aed;

import java.util.ArrayList;
import java.util.Comparator;
 
public class ColaDePrioridad<T> implements ColaPrioridad<T> {                    
    private ArrayList<T> datos;
    private Comparator<T> comparador;
    
    //O(1) tan solo limitadas operaciones elementales sin ciclos y sin depender de la entrada.
    public ColaDePrioridad(Comparator<T> c) {     
        this.datos = new ArrayList<>();
        this.comparador = c;
    }

    //Constructor con agoritmo de Floyd Heapify, el cual es O(n).
    public ColaDePrioridad(T[] secuencia, Comparator<T> c){
        this.comparador = c;
        int i = 0;
        ArrayList<T> data = ArrayASecuencia(secuencia); // O(n)
        this.datos = data;
        
        while (i < data.size()){  //O(n)
            // Gran parte de las veces bajar no hace nada porque son hojas o ya cumple el invariante, lo que seria O(1) por no entrar al ciclo.
            this.bajar(data.size() - 1 - i);
            i++;
        }
    }
 
    public int tamaño(){    // O(1), pues la operación size es O(1)
        return this.datos.size();
    }
   
    public boolean vacía() {     // O(1), porque se utiliza una operacion O(1)            
        return (this.tamaño() == 0);
    }

    public void encolar(T t) { 
        // O(log n) ya que se utiliza una vez la funcion subir que es O(log T), y add es O(1) para este TP
        this.datos.add(t);
        
        // Actualizo el índice en el Handler
        int ultimoIndice = datos.size() - 1;
        T ultimo = datos.get(ultimoIndice);
        actualizarHandlers(ultimo, ultimoIndice);
        
        // Se sube el elemento
        this.subir(ultimoIndice); // O(log n)
    }

    // Esta funcion se utiliza para actualizar los handlers
    private void actualizarHandlers(T elemento, int indice) {
        if (elemento instanceof Traslado) {
            Traslado traslado = (Traslado) elemento;
            if (comparador instanceof ComparadorPorGanancias) {
                traslado.obtenerHandler().setIndiceCosto(indice);
            } else if (comparador instanceof ComparadorPorTiempo) {
                traslado.obtenerHandler().setIndiceTiempo(indice);
            }
        } else if (elemento instanceof Ciudad) {
            Ciudad ciudad = (Ciudad) elemento;
            ciudad.obtenerHandler().setIndiceSuperavit(indice);
        }
    }

    private void subir(int indice) {
        int i = indice;
        // Esta operación es O(log n) ya que a lo sumo se recorre la altura del arbol,
        // y la altura es logaritmica respecto a la cantidad de nodos.
        while (i != 0 &&  esMayor(i, (i-1) / 2)){         
            int padre = (i-1)/2;
            swap(i, padre);
            i = padre;
        }
    }

    private boolean esMayor(int i, int j){  // O(1), ya que se hacen limitadas operaciones elementales, y compare es O(1).
        if (comparador.compare(datos.get(i), datos.get(j)) > 0){
            return true;
        }
        return false;
    }

    public T desencolarMax(){
         //O(log n), ya que utilizo operaciónes O(1) y O(log n) una cantidad constante de veces.
        T max = datos.get(0);
        int ultimoIndice = datos.size() - 1;
        T ultimo = datos.get(ultimoIndice);
        
        // Pongo el último elemento en la raíz
        datos.set(0, ultimo);
        // Actualizo el handler
        actualizarHandlers(ultimo, 0);
        // Elimino el último elemento
        datos.remove(ultimoIndice);
        // Bajo la raiz
        this.bajar(0); // O(log n)
        
        return max;
    }

    private void bajar(int indice){   
        // O(log n) por las mismas rázones de subir.      
        int i = indice;
        while (!EsHoja(i)){
            int hijoIzq = i*2 + 1;
            int hijoDer = i*2 + 2;
            
            // Si tiene solo un hijo (seria el izquierdo)
            if (descendencia(i) == 1) {
                if (esMayor(hijoIzq,i)) {
                    swap(i, hijoIzq);
                    i = hijoIzq;
                } else { // Si el padre es mayor que el hijo izquierdo
                    break;
                }
            }
            // Si tiene dos hijos
            else if (descendencia(i) == 2) {
                // Elegimos el mayor
                int hijoMayor = (esMayor(hijoIzq, hijoDer)) ? hijoIzq : hijoDer;
                
                if (esMayor(hijoMayor,i)) {
                    swap(i, hijoMayor); 
                    i = hijoMayor;
                } else { // Si el padre es mayor que ambos hijos
                    break;
                }
            }
        }
    }

    private int descendencia(int indice) { // Funcion auxiliar para saber si tiene 0, 1 o 2 hijos
        // O(1), debido a que hago una cantidad constante de operaciones O(1)
        if (indice * 2 + 2 < datos.size()) {
            return 2; // Tiene dos hijos
        }
        if (indice * 2 + 1 < datos.size()) {
            return 1; // Tiene un hijo izquierdo
        }
        return 0; // No tiene hijos -> es hoja (Ya la tenemos en la función EsHoja)
    }
    
    // Intercambiar elementos y actualizar handlers
    private void swap(int i, int j){
        // Es O(1) ya que se hacen una cantidad constante de operaciones O(1).
        T aux = datos.get(i);
        datos.set(i, datos.get(j));
        datos.set(j, aux);

        // Actualiza los handlers después del intercambio
        actualizarHandlers(datos.get(i), i);
        actualizarHandlers(datos.get(j), j);
    }

    private boolean EsHoja(int indice){
        // O(1) debido a que son limitadas operaciones elementales O(1)
        // Solo me fijo si tiene hijos a la izquierda
        return ((indice * 2 + 1) >= datos.size()); 
    }
    
    public void cambiarPrioridad (int i, T prioridad){
        // O(log n), porque hay casos en los que se debe bajar o subir el elemento, operaciones que son O(log n)     
        // Reordeno el heap
        subir(i);
        bajar(i);
    }

    public T consultarMax(){   
    // O(1), debido a que el máximo siempre sera el primer elemento de la secuencia, entonces se necesitan constantes operaciones elementales O(1)
    return datos.get(0);}

    private ArrayList<T> ArrayASecuencia (T[] arreglo){   
        // O(n) porque se debe recorrer la lista elemento por elemento y realizar operaciónes O(1)
       ArrayList<T> res = new ArrayList<>();
        int i = 0;
        while (i < arreglo.length){
            res.add(arreglo[i]);
            i ++;
        }
        i = 0;
        while (i < res.size()) { 
            T elemento = res.get(i);
            actualizarHandlers(elemento, i); 
            i++;
        }
        return res;
    }

    public void eliminar(int indice){
        // O(n) por enunciado.
        int ultimoIndice = datos.size() - 1;
        if (indice == ultimoIndice){
            datos.remove(indice);
        }
        else{// Cambio el elemento que quiero eliminar por el ultimo elemento de la secuencia
            T ultimo = datos.get(ultimoIndice);
            datos.set(indice, ultimo);
            datos.remove(ultimoIndice);
            
            actualizarHandlers(ultimo, indice); // O(1)
            // Solo necesito bajar el elemento que cambie de lugar, jamas subir
            this.bajar(indice);
        }
    }

    @Override
    public String toString() { // Para testear
        String res = "";
        for (T t : datos) {
            res += t.toString() + " ";
        }
        return res;
    }
}
