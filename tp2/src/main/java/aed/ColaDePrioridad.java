package aed;

import java.util.ArrayList;
import java.util.Comparator;
 
public class ColaDePrioridad<T> implements ColaPrioridad<T> {                    // VAMOS A IMPLEMETARLO CON UN ARRAY YA QUE DICEN QUE ES LA MEJOR FORMA / ME CONVIENE ARRAY O ARRAY LIST?
    private ArrayList<T> datos;
    private Comparator<T> comparador;
    

    public ColaDePrioridad(Comparator<T> c) {     //O(1) tan solo limitadas operaciones elementales sin ciclos y sin depender de la entrada.
        this.datos = new ArrayList<>();
        this.comparador = c;
    }


    public ColaDePrioridad(T[] secuencia, Comparator<T> c){        //CONSTRUCTOR CON ALGORITMO DE FLOYD HEAPIFY O(n) por alguna razón.  
        this.comparador = c;
        int i = 0;
        ArrayList<T> data = ArrayASecuencia(secuencia); // O(n)
        this.datos = data;
        while (i < data.size()){  //O(n)
            this.bajar(data.size() - 1 - i); // Gran parte de las veces bajar no hace nada porque son hojas o ya cumple el invariante, lo que seria O(1) por no entrar al ciclo.
        }
    }

    private ArrayList<T> ArrayASecuencia (T[] arreglo){         // O(n) porque tengo que recorrer la lista elemento por elemento y realizar una operación O(1)
        ArrayList<T> res = new ArrayList<>();
         int i = 0;
         while (i < arreglo.length){
             res.add(arreglo[i]);
             i ++;
         }
         return res;
     }


    public boolean vacía() {                // O(1) pues la operación size es O(1)
        return (this.datos.size() == 0);
    }
    public void encolar(T t) { // O(log n) ya que utilizo la funcion subir que es O(log n), y add es O(1) para este TP
        this.datos.add(t);
        this.subir(this.datos.size() - 1);

    }

    private void subir(int indice) {
        int i = indice;
        while (i != 0 && (comparador.compare(datos.get(i), (datos.get((i - 1)/2)))) > 0 ){         
            T ultimo = datos.get(i);
           datos.set(i, datos.get(( i-1)/2));                       // Esta operación es O(log n) ya que no debo recorrer todos los elementos del arbol, y la altura es logaritmica respecto a la cantidad de nodos.
            datos.set(((i-1)/2), ultimo);       
            i = (i-1)/2; 

    }}

    public T desencolarMax(){                //O(1), debido a que el máximo siempre sera el primer elemento de la secuencia, entonces necesito limitadas operaciones elementales O(1)
        T valor = datos.get(0);
        datos.set(0, datos.get(datos.size()));
        datos.remove(datos.size()-1);
        this.bajar(0);
        return valor;

    }

    

private void bajar(int indice){         // O(n) por las mismas rázones de subir.
    int i = indice;
    while(!EsHoja(i)){
        if (descendencia(i) == 1){
            if(comparador.compare(datos.get(i*2+1),datos.get(i)) > 0){ // LO CAMBIO POR EL HIJO MAS GRANDE
                T valor = datos.get(i);
                datos.set(i, datos.get(i*2+1));
                datos.set(2*i+1, valor);
                
                }
                i = i*2 + 1;
            }
        else if (descendencia(i) == 2){
            if (comparador.compare(datos.get(i*2+1), datos.get(i*2+2)) > 0){   // LO CAMBIO POR EL HIJO MAS GRANDE
                T valor = datos.get(i);
                datos.set(i, datos.get(i*2+1));
                datos.set(2*i+1, valor);
                i = i*2 + 1;
                }
            else if (comparador.compare(datos.get(i*2+1), datos.get(i*2+2)) < 0){
                T valor = datos.get(i);
                datos.set(i, datos.get(i*2+2));
                datos.set(2*i+2, valor);
                i = i*2 + 2;
                }

            }
        }
    
}

private int descendencia(int indice){
    int res = 1;
    if(datos.size() > indice*2 + 2){
        return 2;
    }
    return res;
}

    private boolean EsHoja(int indice){                                               // O(1) debido a que son limitadas operaciones elementales O(1)
        return ((indice * 2 + 1) >= datos.size() && indice * 2 + 2 >= datos.size());  
    }
    

    public void cambiarPrioridad (int i, T prioridad){          // O(log n), porque por ahi tengo que bajar o subir el elemento, operaciones que son O(log n)
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


}
