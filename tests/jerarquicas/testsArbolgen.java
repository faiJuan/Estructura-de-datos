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
 * @author juan.ramirez
 */
public class testsArbolgen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolGen q = new ArbolGen();
        q.insertar(1, 1);
        q.insertar(2, 1);
        q.insertar(3, 1);
        q.insertar(4, 2);
        q.insertar(5, 2);
        q.insertar(6, 3);
        q.insertar(7, 3);
        q.insertar(8, 3);
        q.insertar(9, 3);
        q.insertar(10, 7);

        Lista l1=new Lista();
        l1.insertar(1, 1);
        l1.insertar(15, 2);
        l1.insertar(7, 3);
        l1.insertar(10, 4);
//        l1.insertar(8, 5);
//        l1.insertar(9, 6);
//        l1.insertar(10, 7);
//        q.eliminar(3);
//        System.out.println(q.toString());

        System.out.println(q.toString());
        System.out.println();
        q.repetirHEI(1);
        System.out.println(q.toString());

    }

}
