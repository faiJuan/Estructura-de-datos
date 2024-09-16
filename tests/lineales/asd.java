/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;

/**
 *
 * @author Fenix
 */
public class asd {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cola c1 = new Cola();
        Cola c5=new Cola();
        c1.poner('A');
        c1.poner('B');
        c1.poner('#');
        c1.poner('C');
        c1.poner('#');
        c1.poner('D');
        c1.poner('E');
        c1.poner('F');
        
        c5=generarCola(c1);
        System.out.println(c5.toString());

    }

    public static Cola generarCola(Cola c1) {
        Cola c2 = new Cola();
        Cola c3 = new Cola();
        Pila p1 = new Pila();
        Cola c4 = c1.clone();

        while (!c4.esVacia()) {
            if (c4.obtenerFrente().equals('#')) {
                while (!c3.esVacia()) {
                    if (!p1.esVacia()) {
                        c2.poner(p1.obtenerTope());
                        p1.desapilar();
                    } else {
                        c2.poner(c3.obtenerFrente());
                        c3.sacar();
                    }
                }
                c2.poner(c4.obtenerFrente());
                c4.sacar();
            } else {
                c2.poner(c4.obtenerFrente());
                c3.poner(c4.obtenerFrente());
                p1.apilar(c4.obtenerFrente());
                c4.sacar();
            }
        }
        if (!c3.esVacia()) {
            while (!c3.esVacia()) {
                if (!p1.esVacia()) {
                    c2.poner(p1.obtenerTope());
                    p1.desapilar();
                } else {
                    c2.poner(c3.obtenerFrente());
                    c3.sacar();
                }
            }
        }
      return c2;  
    }
}