
package tests.lineales;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;
import lineales.dinamicas.Pila;


public class TestingListaPersonal {

    public static void main(String[] args) {
    Lista q1=new Lista();
    Lista q2=new Lista();
    q1.insertar(9, 1);
    q1.insertar(9, 2);
    q1.insertar(3, 3);
    q1.insertar(4, 4);
    q1.insertar(5, 5);
    q1.insertar(6, 6);
    q1.insertar(7, 7);
    q1.insertar(8, 8);
    q1.insertar(1, 9);
    
    q2=q1.clone();
    q2.eliminarApariciones(9);
    
    System.out.println(q2.toString());
    }
    
//    public static boolean verificarBalanceo(Cola c1){
//       Cola q2=new Cola();
//       Pila p1=new Pila();
//       boolean exito=false;
//       
//       q2=c1.clone();
//       if(!q2.esVacia()){
//           while(!q2.esVacia()){
//               if(q2.obtenerFrente().equals('{') || q2.obtenerFrente().equals('[') || q2.obtenerFrente().equals('(')){
//                   p1.apilar(q2.obtenerFrente());
//                   q2.sacar();
//               }
//               if (q2.obtenerFrente().equals('}') || q2.obtenerFrente().equals(']') || q2.obtenerFrente().equals(')')){
//                   p1.desapilar();
//                   q2.sacar();
//               }else {
//                   q2.sacar();
//               }
//           }
//           if (p1.esVacia()){
//               exito=true;
//           }
//       }
//        return exito;
//    }
}
