
package tests.lineales;

import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;
//import lineales.estaticas.Cola;
//import lineales.estaticas.Pila;

public class MixLineales {

    public static void main(String[] args) {
        Cola c1 = new Cola();
        Cola c2 = new Cola();
        Cola c3 = new Cola();
        System.out.println("Cola vacia:\t\t" + c1.toString());
        c1.poner('A');
        System.out.println("Inserto 'A'\t\t" + c1.toString());
        c1.poner('B');
        System.out.println("Inserto 'B'\t\t" + c1.toString());
        c1.poner('$');
        System.out.println("Inserto '$'\t\t" + c1.toString());
        c1.poner('C');
        System.out.println("Inserto 'C'\t\t" + c1.toString());
        c1.poner('$');
        System.out.println("Inserto '$'\t\t" + c1.toString());
        c1.poner('D');
        System.out.println("Inserto 'D'\t\t" + c1.toString());
        c1.poner('E');
        System.out.println("Inserto 'E'\t\t" + c1.toString());
        c1.poner('F');
        System.out.println("Inserto 'F'\t\t" + c1.toString());

        c2 = generarOtraCola(c1);
        c3 = generarOtraColaMas(c1);

        System.out.println("Cola 1\t\t\t\t" + c1.toString());
        System.out.println("Cola 2 del tp\t\t\t" + c2.toString());
        System.out.println("Cola 3 del simulacro\t\t" + c3.toString());

    }

    public static Cola generarOtraColaMas(Cola c1) {
        // Este es el del simulacro AB$C$DEF entonces ABBAAB$CCC$DEFFEDDEF
        Pila p1 = new Pila();
        Pila p2 = new Pila();
        Cola c3 = new Cola();
        Cola c4 = new Cola();
        c3 = c1.clone();

        while (!c3.esVacia()) {
            //  Mientras c3 no este vacia
            if (!c3.obtenerFrente().equals('$')) {
                //  Entra si el char es distinto del '$'
                // Va llenando c4
                c4.poner(c3.obtenerFrente());
                p1.apilar(c3.obtenerFrente());
            }
            if (c3.obtenerFrente().equals('$')) {
                //  Entra si el char es igual del '$'
                while (!p1.esVacia()) {
                    // Va llenando c4 con la cadena invertida
                    c4.poner(p1.obtenerTope());
                    p2.apilar(p1.obtenerTope());
                    p1.desapilar();
                }
                while (!p2.esVacia()) {
                    // Llena c4 con la cadena no invertida
                    c4.poner(p2.obtenerTope());
                    p2.desapilar();
                }
                c4.poner(c3.obtenerFrente());
            }
            // Va vaciando c3
            c3.sacar();
            if (c3.esVacia()) {
                // Llena c4 con la ultima cadena de c3 que esta en p1
                while (!p1.esVacia()) {
                    // Va llenando c4 con la cadena invertida
                    c4.poner(p1.obtenerTope());
                    p2.apilar(p1.obtenerTope());
                    p1.desapilar();
                }
                while (!p2.esVacia()) {
                    // Llena c4 con la cadena no invertida
                    c4.poner(p2.obtenerTope());
                    p2.desapilar();
                }
            }
        }
        return c4;
    }

    public static Cola generarOtraCola(Cola c1) {
        // Este es el del TP AB$C$DEF entonces ABBA$CC$DEFFED
        Pila p1 = new Pila();
        Cola c3 = new Cola();
        Cola c4 = new Cola();
        c3 = c1.clone();

        while (!c3.esVacia()) {
            //  Mientras c3 no este vacia
            if (!c3.obtenerFrente().equals('$')) {
                //  Entra si el char es distinto del '$'
                c4.poner(c3.obtenerFrente());
                p1.apilar(c3.obtenerFrente());
            }
            if (c3.obtenerFrente().equals('$')) {
                //  Entra si el char es igual del '$'
                while (!p1.esVacia()) {
                    c4.poner(p1.obtenerTope());
                    p1.desapilar();
                }
                c4.poner(c3.obtenerFrente());
            }
            c3.sacar();
            if (c3.esVacia()) {
                while (!p1.esVacia()) {
                    c4.poner(p1.obtenerTope());
                    p1.desapilar();
                }
            }
        }
        return c4;
    }

}