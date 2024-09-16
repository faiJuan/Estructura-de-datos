/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.jerarquicas;
import jerarquicas.ArbolBin;

import lineales.dinamicas.Lista;
/**
 *
 * @author Fenix
 */
public class testPersonalArbolBin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArbolBin q1 = new ArbolBin ();
        q1.insertar(1, null, 'D');
        q1.insertar(2, 1, 'I');
        q1.insertar(3, 1, 'D');
        q1.insertar(4, 2, 'I');
        q1.insertar(5, 3, 'D');
//        Lista lis=new Lista();
//        lis.insertar(1, 1);
//        lis.insertar(2, 2);
//        lis.insertar(4, 3);
//        lis.insertar(4, 4);
//        lis.insertar(5, 5);
//        lis.insertar(6, 6);
//        lis.insertar(7, 7);
        
        System.out.println(q1.toString());
        System.out.println();
        q1.completarHijos();
        System.out.println(q1.toString());
    }
    
}
