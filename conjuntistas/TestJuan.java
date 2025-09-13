package conjuntistas;

public class TestJuan {
    public static void main(String[] args) {
        ArbolAVL arbol;

        arbol=new ArbolAVL();

        arbol.insertar(30);
        arbol.insertar(15);
        arbol.insertar(50);
        arbol.insertar(15);
        arbol.insertar(60);
        arbol.insertar(5);
        arbol.insertar(25);
        arbol.insertar(18);
        //arbol.insertar(13);


        arbol.eliminar(60);
        System.out.println(arbol.toString());


    }
}
