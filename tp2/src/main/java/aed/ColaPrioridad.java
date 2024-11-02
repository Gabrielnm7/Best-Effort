package aed;

interface ColaPrioridad <T> {

    public boolean vacía();
    // Devuelve true si la Cola de Prioridad está vacía.

    public void encolar(T t);
    // Agrega un elemento a la Cola de Prioridad y reordena para manetener el Invariante de representación.

    public T desencolarMax();
    // Saca el primer elemento (El de mayor prioridad) y reordena para mantener el Invariante de representación.

    public void cambiarPrioridad (int i, T prioridad);
    // Cambia la prioridad del elemento en la posicion i y reordena para mantener el Invariante de representación.

    public T consultarMax();
    // Devuelve el elemento de máxima prioridad sin cambiar nada.

    public ColaPrioridad<T> ColaDePrioridadDesdeSecuencia(T[] secuencia);
    // Agarra una secuencia la reordena y la convierte en un tipo ColaPrioridad.

    
}
