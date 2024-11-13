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
        }
        i = 0;

        // Actualizar los índices en los Handlers después de construir el Heap (sigue siendo O(n))
        while (i < data.size()) {
            T elemento = datos.get(i);
            if (elemento instanceof Traslado) {
                Traslado traslado = (Traslado) elemento;
                if (comparador instanceof ComparadorPorGanancias) {
                    traslado.obtenerHandler().setIndiceCosto(i);
                } else if (comparador instanceof ComparadorPorTiempo) {
                    traslado.obtenerHandler().setIndiceTiempo(i);
                }
            }
            else {
                // Si es Ciudad
                Ciudad ciudad = (Ciudad) elemento;
                ciudad.obtenerHandler().setIndiceSuperavit(i);
            }
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
        if (ultimo instanceof Traslado) { // Si es Traslado
            Traslado traslado = (Traslado) ultimo;
            if (comparador instanceof ComparadorPorGanancias) {
                traslado.obtenerHandler().setIndiceCosto(ultimoIndice);
            } else if (comparador instanceof ComparadorPorTiempo) {
                traslado.obtenerHandler().setIndiceTiempo(ultimoIndice);
            }
        }
        else {
            // Si es Ciudad
            Ciudad ciudad = (Ciudad) ultimo;
            ciudad.obtenerHandler().setIndiceSuperavit(ultimoIndice);
        }
        
        // Subimos el elemento
        this.subir(ultimoIndice);
    }

    // Esta funcion la uso para actualizar los handlers
    private void actualizarHandlers(T valor, T intercambiado, int indiceActual, int indiceNuevo) {
        // Verificar si ambos elementos son de tipo Traslado
        if (valor instanceof Traslado && intercambiado instanceof Traslado) {
            Traslado trasladoActual = (Traslado) valor;
            Traslado trasladoIntercambiado = (Traslado) intercambiado;
    
            // Actualizar los índices en el Handler según el tipo de comparador
            if (comparador instanceof ComparadorPorGanancias) {
                trasladoActual.obtenerHandler().setIndiceCosto(indiceNuevo);
                trasladoIntercambiado.obtenerHandler().setIndiceCosto(indiceActual);
            } else if (comparador instanceof ComparadorPorTiempo) {
                trasladoActual.obtenerHandler().setIndiceTiempo(indiceNuevo);
                trasladoIntercambiado.obtenerHandler().setIndiceTiempo(indiceActual);
            }
        }
        else if (valor instanceof Ciudad && intercambiado instanceof Ciudad) {
            Ciudad ciudadActual = (Ciudad) valor;
            Ciudad ciudadIntercambiada = (Ciudad) intercambiado;
            ciudadActual.obtenerHandler().setIndiceSuperavit(indiceNuevo);
            ciudadIntercambiada.obtenerHandler().setIndiceSuperavit(indiceActual);
        }
    }

    private void subir(int indice) {
        int i = indice;
        // Esta operación es O(log n) ya que no debo recorrer todos los elementos del arbol, 
        // y la altura es logaritmica respecto a la cantidad de nodos.
        while (i != 0 && (comparador.compare(datos.get(i), (datos.get((i - 1)/2)))) > 0 ){         
            int padre = (i-1)/2;

            // Intercambiamos los elementos
            T ultimo = datos.get(i);
            datos.set(i, datos.get(padre));
            datos.set(padre, ultimo);
            // swap(i, padre);
            
            // Actualizamos los handlers
            actualizarHandlers(ultimo, datos.get(i), i, padre);

            i = padre;
        }
    }

    //O(1), debido a que el máximo siempre sera el primer elemento de la secuencia, entonces necesito limitadas operaciones elementales O(1)
    public T desencolarMax(){                
        T valor = datos.get(0);
        datos.set(0, datos.get(datos.size() - 1));
        datos.remove(datos.size()-1);
        this.bajar(0);
        return valor;

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
                }
                i = hijoIzq;
            }
            // Si tiene dos hijos
            else if (descendencia(i) == 2) {
                // Elegimos el mayor
                int hijoMayor = (comparador.compare(datos.get(hijoIzq), datos.get(hijoDer)) > 0) ? hijoIzq : hijoDer;
                
                if (comparador.compare(datos.get(hijoMayor), datos.get(i)) > 0) {
                    swap(i, hijoMayor); 
                }
                i = hijoMayor;
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
        actualizarHandlers(aux, datos.get(i), i, j);
    }

    // O(1) debido a que son limitadas operaciones elementales O(1)
    private boolean EsHoja(int indice){                                               
        return ((indice * 2 + 1) >= datos.size() && indice * 2 + 2 >= datos.size());  
    }
    
    // O(log n), porque por ahi tengo que bajar o subir el elemento, operaciones que son O(log n)
    public void cambiarPrioridad (int i, T prioridad){          
    T valorAnterior = datos.get(i);
        datos.set(i, prioridad);
    if (comparador.compare (valorAnterior, prioridad) < 0){   // Reordeno segun si es mayor o menos al elemento anterior
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
            
            Traslado traslado = (Traslado) ultimo;
            if (comparador instanceof ComparadorPorGanancias) {
                traslado.obtenerHandler().setIndiceCosto(indice);
            } else if (comparador instanceof ComparadorPorTiempo) {
                traslado.obtenerHandler().setIndiceTiempo(indice);
            }
            // Solo necesito bajar el elemento que cambie de lugar, jamas subir porque el ultimo elemento es el menor de todos
            this.bajar(indice);
        }
    }
}
