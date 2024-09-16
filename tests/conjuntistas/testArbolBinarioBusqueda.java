/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.conjuntistas;

import conjuntistas.ArbolBB;

/**
 *
 * @author Juan Ramirez
 */
public class testArbolBinarioBusqueda {

    public static void main(String[] args) {
        ArbolBB a1 = new ArbolBB();
        ArbolBB a2;
        a1.insertar(43);
        a1.insertar(22);
        a1.insertar(6);
        a1.insertar(4);
        a1.insertar(28);
        a1.insertar(40);
        a1.insertar(58);
        a1.insertar(50);
        a1.insertar(90);
        a1.insertar(77);
        a1.insertar(59);
        a1.insertar(86);

//        a2=a1.clonarParteInvertida(86);
//        System.out.println(a1.toString());
        System.out.println(a1.listarRango(22, 50));

        
    }

}
