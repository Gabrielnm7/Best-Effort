package aed;

import java.util.ArrayList;
 
public class Heap {                    // VAMOS A IMPLEMETARLO CON UN ARRAY YA QUE DICEN QUE ES LA MEJOR FORMA / ME CONVIENE ARRAY O ARRAY LIST?
    private ArrayList<int[]> datos;
    
public Heap(){
    datos = new ArrayList<int[]>();
}

public boolean EsVacio(){
    return (this.datos.size() == 0);      // PODEMOS USAR SIZE?

}

public void Insertar(int n){
}


}
