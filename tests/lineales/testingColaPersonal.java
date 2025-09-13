
package tests.lineales;
import lineales.dinamicas.Cola;

public class testingColaPersonal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cola q1 = new Cola ();
        Cola q2 = new Cola();
        q1.poner(1);
        q1.poner(2);
        q1.poner(3);
        q1.poner(4);
        q1.poner(5);
        q1.poner(6);
        q1.poner(7);
        q1.poner(8);
        q1.poner(9);
        q1.poner(10);
        q1.poner(11);
        q1.poner(12);
        q1.poner(13);
        
        
        q2=q1.clone();
        
        System.out.print(q1.toString());
        
    }
    
}
