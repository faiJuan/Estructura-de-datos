package jerarquicas;

import lineales.dinamicas.Lista;

public class Test {
    public static void main(String[] args) {
        ArbolGen arbol=new ArbolGen();
        Lista lis=new Lista();
        arbol.insertar(1, 1);
        arbol.insertar(2, 1);
        arbol.insertar(3, 1);
        arbol.insertar(4, 1);
        arbol.insertar(5, 2);
        arbol.insertar(6, 5);
        arbol.insertar(7, 2);
        arbol.insertar(8, 3);
        arbol.insertar(9, 3);
        arbol.insertar(10, 4);
        arbol.insertar(11, 10);
        arbol.insertar(12, 10);
        //arbol.insertar(13, 12);


        


        //lis.insertar(1, 1);
        //lis.insertar(2, 2);
        //lis.insertar(5, 3);
        //lis.insertar(6, 4);

        
        System.out.println(arbol.listaQueJustificaAltura());



        //System.out.println(arbol.listarPreorden());
        //System.out.println(arbol.insertarPorPosicion(55, 60));
        
        //System.out.println(arbol.toString());
        //System.out.println(arbol.ancestro(3,3));
        
    }
}
