/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

/**
 *
 * @author Juan Ramirez
 */
public class ArbolGen {

    private NodoGen raiz;

    //constructor
    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        //Metodo para insertar elementos al arbol, en caso de que el arbol
        //este vacio el elemento que entra se inserta en la raiz, en caso contrario
        //se necesita saber a que nodo padre se va a insertar
        if (this.raiz == null) {
            this.raiz = new NodoGen(elemNuevo, null, null);
        } else {
            NodoGen nodoAux = obtenerNodo(this.raiz, elemPadre);
            if (nodoAux != null) {
                if (nodoAux.getHijoIzquierdo() == null) {
                    nodoAux.setHijoIzquierdo(new NodoGen(elemNuevo, null, null));
                } else {
                    NodoGen aux2 = nodoAux.getHijoIzquierdo();
                    while (aux2.getHermanoDerecho() != null) {
                        aux2 = aux2.getHermanoDerecho();
                    }
                    aux2.setHermanoDerecho(new NodoGen(elemNuevo, null, null));
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    public boolean pertenece(Object elemento) {
        //Busca el nodo que contiene al elemento que entra
        return obtenerNodo(this.raiz, elemento) != null;
    }

    private NodoGen obtenerNodo(NodoGen n, Object buscado) {
        NodoGen resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                resultado = n;
            } else {
                resultado = obtenerNodo(n.getHijoIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getHermanoDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public Lista ancestros(Object elemento) {
        Lista lis = new Lista();
        if (this.raiz != null) {
            ancentrosAux(this.raiz, elemento, lis, 1);
        }
        return lis;
    }

    private boolean ancentrosAux(NodoGen n, Object elem, Lista lis, int pos) {

        boolean exito = false;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                exito = true;
            } else {
                lis.insertar(n.getElem(), pos);
                NodoGen aux = n.getHijoIzquierdo();
                while (aux != null && !exito) {
                    exito = ancentrosAux(aux, elem, lis, pos + 1);
                    aux = aux.getHermanoDerecho();
                }
                if (!exito) {
                    lis.eliminar(pos);
                }

            }
        }
        return exito;
    }

    public Lista listarInorden() {
        Lista salida = new Lista();
        listarInordenAux(this.raiz, salida);
        return salida;
    }

    private void listarInordenAux(NodoGen n, Lista ls) {
        //llamado recursivo con el primer hijo de n
        if (n.getHijoIzquierdo() != null) {
            listarInordenAux(n.getHijoIzquierdo(), ls);
        }
        //visita del nodo n
        ls.insertar(n.getElem(), ls.longitud() + 1);

        //llamados recursivos con los otros hijos de n
        if (n.getHijoIzquierdo() != null) {
            NodoGen hijo = n.getHijoIzquierdo().getHermanoDerecho();
            while (hijo != null) {
                listarInordenAux(hijo, ls);
                hijo = hijo.getHermanoDerecho();
            }
        }
    }

    public Lista listarPreorden() {
        Lista salida = new Lista();
        listarPreordenAux(this.raiz, salida);
        return salida;
    }

    private void listarPreordenAux(NodoGen n, Lista ls) {
        ls.insertar(n.getElem(), ls.longitud() + 1);
        if (n.getHijoIzquierdo() != null) {
            listarPreordenAux(n.getHijoIzquierdo(), ls);
        }
        if (n.getHermanoDerecho() != null) {
            listarPreordenAux(n.getHermanoDerecho(), ls);
        }
    }

    public Lista listarPosorden() {
        Lista salida = new Lista();
        if (!esVacio()) {
            listarPosordenAux(this.raiz, salida);
        }

        return salida;
    }

    private void listarPosordenAux(NodoGen n, Lista lis) {
        if (n.getHijoIzquierdo() != null) {
            listarPosordenAux(n.getHijoIzquierdo(), lis);
        }
        lis.insertar(n.getElem(), lis.longitud() + 1);
        if (n.getHermanoDerecho() != null) {
            listarPosordenAux(n.getHermanoDerecho(), lis);
        }
    }

    public Lista listarPorNiveles() {
        Lista lis = new Lista();
        if (!esVacio()) {
            listarNivelesAux(this.raiz, lis);
        }
        return lis;
    }

    private void listarNivelesAux(NodoGen n, Lista ls) {
        Cola q = new Cola();
        q.poner(n);
        ls.insertar(n.getElem(), ls.longitud() + 1);
        while (!q.esVacia()) {
            NodoGen t = (NodoGen) q.obtenerFrente();
            NodoGen hijo = t.getHijoIzquierdo();
            while (hijo != null) {
                ls.insertar(hijo.getElem(), ls.longitud() + 1);
                q.poner(hijo);
                hijo = hijo.getHermanoDerecho();
            }
            q.sacar();
        }
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public void vaciar() {
        this.raiz = null;
    }

    public int nivel(Object elem) {
        return nivelAux(this.raiz, elem, -1);
    }

    private int nivelAux(NodoGen n, Object elem, int nivel) {
        int resultado = -1;
        if (n != null) {
            if (n.getElem().equals(elem)) {
                resultado = nivel + 1;
            } else {
                if (n.getHijoIzquierdo() != null) {
                    resultado = nivelAux(n.getHijoIzquierdo(), elem, nivel + 1);
                }
                if (resultado == -1) {
                    if (n.getHermanoDerecho() != null) {
                        resultado = nivelAux(n.getHermanoDerecho(), elem, nivel);
                    }
                }
            }
        }
        return resultado;
    }

    public int altura() {
        int altura = -1;
        if (this.raiz != null) {
            altura = altr(this.raiz);
        }
        return altura;
    }

    private int altr(NodoGen m) {
        int aux = -1, res = -1;
        if (m != null) {
            NodoGen h = m.getHijoIzquierdo();
            while (h != null) {
                aux = altr(h);
                if (aux > res) {
                    res = aux;
                }
                h = h.getHermanoDerecho();
            }
            res++;
        }
        return res;
    }

    public String toString() {
        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoGen n) {
        String s = "";
        if (n != null) {
            //visita del nodo n
            s += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            //comienza recorrido de los hijos de n llamando recursivamente
            //para que cada hijo agregue su subcadena a la general
            hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += "\n" + toStringAux(hijo);
                hijo = hijo.getHermanoDerecho();
            }
        }
        return s;
    }

    public Object padre(Object elemento) {
        Object obtenido = null;
        if (!esVacio()) {
            if (this.raiz.getElem() != elemento) {
                obtenido = padreAux(this.raiz, elemento);
            }
        }
        return obtenido;
    }

    public boolean sonFrontera(Lista lis) {
        Lista ls = new Lista();
        ls = lis.clone();
        boolean exito = false;

        if (!esVacio() && !lis.esVacia()) {
            exito = fronteraAux(this.raiz, ls);
        } else {
            exito = !(!esVacio() && lis.esVacia());
        }

        return exito;
    }

    private boolean fronteraAux(NodoGen n, Lista ls) {
        boolean encontrado = true;
        if (encontrado) {
            if (n.getHijoIzquierdo() != null) {
                encontrado = fronteraAux(n.getHijoIzquierdo(), ls);
            } else if (encontrado) {
                int i;
                i = ls.localizar(n.getElem());
                if (i == -1) {
                    encontrado = false;
                } else {
                    ls.eliminar(i);
                }
                if (n.getHermanoDerecho() != null && encontrado) {
                    encontrado = fronteraAux(n.getHermanoDerecho(), ls);
                }
            }

        }
        return encontrado;
    }

    private Object padreAux(NodoGen n, Object elemento) {
        Object padre = null;
        if (n != null) {
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null && !hijo.getElem().equals(elemento)) {
                hijo = hijo.getHermanoDerecho();
            }
            if (hijo != null) {
                padre = n.getElem();
            } else {
                padre = padreAux(n.getHijoIzquierdo(), elemento);
                if (padre == null) {
                    padre = padreAux(n.getHermanoDerecho(), elemento);
                }
            }

        }
        return padre;
    }

    public ArbolGen clone() {
        ArbolGen clone = new ArbolGen();
        if (!esVacio()) {
            clone.raiz = cloneAux(this.raiz);
        }
        return clone;
    }

    private NodoGen cloneAux(NodoGen nodo) {
        NodoGen nuevo = new NodoGen(nodo.getElem(), null, null);

        if (nodo.getHijoIzquierdo() != null) {
            nuevo.setHijoIzquierdo(cloneAux(nodo.getHijoIzquierdo()));
        }
        if (nodo.getHermanoDerecho() != null) {
            nuevo.setHermanoDerecho(cloneAux(nodo.getHermanoDerecho()));
        }
        return nuevo;
    }

    //De aqui en adelante son ejercicios para el parcial
    public boolean verificarCamino(Lista lis) {
        boolean exito = false;
        if (!lis.esVacia() && !esVacio()) {
            exito = verificarCaminoAux(this.raiz, lis);
        } else if (lis.esVacia() && esVacio()) {
            exito = true;
        }
        return exito;
    }

    private boolean verificarCaminoAux(NodoGen n, Lista lis) {
        boolean exito = true;
        if (n != null) {
            if (n.getElem().equals(lis.recuperar(1))) {
                lis.eliminar(1);
                if (!lis.esVacia()) {
                    exito = verificarCaminoAux(n.getHijoIzquierdo(), lis);
                }
            } else {
                if (n.getHermanoDerecho() != null) {
                    exito = verificarCaminoAux(n.getHermanoDerecho(), lis);
                } else {
                    exito = false;
                }
            }
        }
        return exito;
    }

    public boolean eliminar(Object elem) {
        //elimina un elemento del arbol junto con sus desendientes
        boolean exito = false;
        if (!esVacio()) {
            exito = eliminarAux(this.raiz, elem, null, null);
        }
        return exito;
    }

    private boolean eliminarAux(NodoGen n, Object elem, NodoGen padre, NodoGen anterior) {
        boolean exito = false;
        if (n != null) {
            //si n es null no entra y devuelve false
            if (n.getElem().equals(elem)) {
                //compara el elemento, en caso de que padre sea null significa que es raiz
                if (padre == null) {
                    this.raiz = null;
                    exito = true;
                } else {
                    if (anterior == null) {
                        /*si anterior es null significa que es HI por ende no tiene 
                        un nodo anterior a el y procede a eliminarlo*/
                        padre.setHijoIzquierdo(n.getHermanoDerecho());
                    } else {
                        /*si anterior es !=null entonces es un hermano, procede a eliminarlo
                        seteando al hermano anterior con el que le sigue a n*/
                        anterior.setHermanoDerecho(n.getHermanoDerecho());
                    }
                    exito = true;
                }
            } else {
                exito = eliminarAux(n.getHijoIzquierdo(), elem, n, null);
                //si no encontro el elem se va por el HI, con n como padre
                if (exito == false) {
                    //en caso de no estar en HI pasa a HD, con n como anterior
                    exito = eliminarAux(n.getHermanoDerecho(), elem, padre, n);
                }
            }
        }
        return exito;
    }

    public Lista listarEntreNiveles(int niv1, int niv2) {
        Lista lis = new Lista();
        if (!esVacio() || !lis.esVacia()) {
            listarEntreNivelesAux(this.raiz, lis, niv1, niv2, 0);
        }
        return lis;
    }

    private void listarEntreNivelesAux(NodoGen n, Lista lis, int niv1, int niv2, int actual) {
        if (n != null) {
//            if () {

        }
    }

    public void repetirHEI(Object a) {
        if (this.raiz != null) {
            NodoGen n = obtenerNodo(this.raiz, a);
            if (n != null) {
                if (n.getHijoIzquierdo() != null) {
                    NodoGen izq = n.getHijoIzquierdo().getHermanoDerecho();
                    if (izq != null) {
                        Object elem = n.getHijoIzquierdo().getElem();
                        boolean corte = false;
                        NodoGen anterior = null;
                        while (corte == false && izq != null) {
                            if (izq.getElem().equals(elem)) {
                                corte = true;
                            } else {
                                anterior = izq;
                                izq = izq.getHermanoDerecho();
                            }
                        }
                        if (izq == null) {
                            anterior.setHermanoDerecho(new NodoGen(elem, null, null));
                        }
                    }
                }
            }
        }
    }

}
