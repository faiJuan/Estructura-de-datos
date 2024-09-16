/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;

import jerarquicas.ArbolGen;
import lineales.dinamicas.Lista;

/**
 *
 * @author Juan Ramirez
 */
public class TestsonFrontera {

    public static void main(String[] args) {
        ArbolGen a = new ArbolGen();
        Lista l = new Lista();
        System.out.println("Si la lista y el arbol son vacios espera true --> "+a.sonFrontera(l) );
        l.insertar(1, 1);
        System.out.println("Si el arbol esta vacio y la lista no espera true --> "+a.sonFrontera(l));
        l.vaciar();
        a.insertar(1, 1);
        System.out.println("Inserta 1 en la raiz");
        System.out.println("Si el arbol no esta vacio y la lista si espera false --> "+a.sonFrontera(l));
        a.insertar(2, 1);
        System.out.println("Inserta 2 como hijo");
        a.insertar(3, 1);
        System.out.println("Inserta 3 como hijo");
        a.insertar(4, 2);
        System.out.println("Inserta 4 como hijo de 2");
        l.insertar(1, 1);
        l.insertar(3, 2);
        l.insertar(4, 3);
        l.insertar(5, 4);
        System.out.println("Inserta 1,3,4,5 en la lista --> "+l.toString());
        System.out.println("Verifica si el 4 y 5 pertenecen a la lista, espera true --> "+a.sonFrontera(l) );
        l.eliminar(1);
        l.eliminar(1);
        System.out.println("Elimina 1 y 2 de lista para que queden solo las hojas del arbol" + l.toString());
        System.out.println("Verifica si la frontera es igual a todos los elementos de la lista-->"+a.sonFrontera(l));
        l.eliminar(1);
        System.out.println("elimina 4 de la lista");
        System.out.println("Verifica si estan todos los elementos en la lista, espera false-->" +a.sonFrontera(l));
        
    }

}
