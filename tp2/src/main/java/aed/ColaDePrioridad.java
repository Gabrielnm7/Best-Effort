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

    //CONSTRUCTOR CON ALGORITMO DE FLOYD HEAPIFY O(n) por alguna razón.  
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

        // Actualizar los índices en los Handlers después de construir el Heap (sigue siendo O(n))
        i = 0;
        while (i < data.size()) {
            T elemento = datos.get(i);
            actualizarHandlers(elemento, i);
            i++;
        }
    }

    // O(1) pues la operación size es O(1)
    public boolean vacía() {                
        return (this.datos.size() == 0);
    }

    // O(log n) ya que utilizo la funcion subir que es O(log n), y add es O(1) para este TP
    public void encolar(T t) { 
        this.datos.add(t);
        
        // Actualizo el índice en el Handler
        int ultimoIndice = datos.size() - 1;
        T ultimo = datos.get(ultimoIndice);
        
        actualizarHandlers(ultimo, ultimoIndice);
        
        // Subimos el elemento
        this.subir(ultimoIndice);
    }

    // Esta funcion la uso para actualizar los handlers
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
        // Esta operación es O(log n) ya que no debo recorrer todos los elementos del arbol, 
        // y la altura es logaritmica respecto a la cantidad de nodos.
        while (i != 0 && (comparador.compare(datos.get(i), (datos.get((i - 1)/2)))) > 0 ){         
            int padre = (i-1)/2;
            swap(i, padre);
            i = padre;
        }
    }

    //O(1), debido a que el máximo siempre sera el primer elemento de la secuencia, entonces necesito limitadas operaciones elementales O(1)
    public T desencolarMax(){                
        T valor = datos.get(0);
        int ultimoIndice = datos.size() - 1;
        T ultimo = datos.get(ultimoIndice);
        
        // Pongo el último elemento en la raíz
        datos.set(0, ultimo);
        // Actualizo el handler
        actualizarHandlers(ultimo, 0);
        // Elimino el último elemento
        datos.remove(ultimoIndice);
        // Bajo la raiz
        this.bajar(0);
        
        return valor;
    }

    @Override
    public String toString() {
        String res = "";
        for (T t : datos) {
            res += t.toString() + " ";
        }
        return res;
    }

    // O(log n) por las mismas rázones de subir.
    private void bajar(int indice){         
        int i = indice;
        while (!EsHoja(i)){
            int hijoIzq = i*2 + 1;
            int hijoDer = i*2 + 2;
            
            // Si tiene solo un hijo (seria el izquierdo)
            if (descendencia(i) == 1) {
                if (comparador.compare(datos.get(hijoIzq), datos.get(i)) > 0) {
                    swap(i, hijoIzq);
                    i = hijoIzq;
                } else { // Si el padre es mayor que el hijo izquierdo
                    break;
                }
            }
            // Si tiene dos hijos
            else if (descendencia(i) == 2) {
                // Elegimos el mayor
                int hijoMayor = (comparador.compare(datos.get(hijoIzq), datos.get(hijoDer)) > 0) ? hijoIzq : hijoDer;
                
                if (comparador.compare(datos.get(hijoMayor), datos.get(i)) > 0) {
                    swap(i, hijoMayor); 
                    i = hijoMayor;
                } else { // Si el padre es mayor que ambos hijos
                    break;
                }
            }
        }
    }

    private int descendencia(int indice) {
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
        T aux = datos.get(i);
        datos.set(i, datos.get(j));
        datos.set(j, aux);

        // Actualiza los handlers después del intercambio
        actualizarHandlers(datos.get(i), i);
        actualizarHandlers(datos.get(j), j);
    }

    // O(1) debido a que son limitadas operaciones elementales O(1)
    private boolean EsHoja(int indice){                                               
        return ((indice * 2 + 1) >= datos.size() && indice * 2 + 2 >= datos.size());  
    }
    
    // O(log n), porque por ahi tengo que bajar o subir el elemento, operaciones que son O(log n)
    public void cambiarPrioridad (int i, T prioridad){          
    T valorAnterior = datos.get(i);
        datos.set(i, prioridad);
    if (comparador.compare(valorAnterior, prioridad) < 0){   // Reordeno segun si es mayor o menos al elemento anterior
        subir(i);}
    else{
        bajar(i);}
    }

    public T consultarMax(){
    return datos.get(0);}

    // O(n) porque tengo que recorrer la lista elemento por elemento y realizar una operación O(1)
    private ArrayList<T> ArrayASecuencia (T[] arreglo){         
       ArrayList<T> res = new ArrayList<>();
        int i = 0;
        while (i < arreglo.length){
            res.add(arreglo[i]);
            i ++;
        }
        return res;
    }

    // Podria hacer eliminar por indice(lo mas sencillo)
    public void eliminar(int indice){
        if (indice == datos.size() - 1){
            datos.remove(indice);
        }
        else{
            // Cambio el elemento que quiero eliminar por el ultimo elemento de la secuencia
            T ultimo = datos.get(datos.size() - 1);
            datos.set(indice, ultimo);
            datos.remove(datos.size() - 1);
            
            actualizarHandlers(ultimo, indice);
            // Solo necesito bajar el elemento que cambie de lugar, jamas subir
            this.bajar(indice);
        }
    }
}
