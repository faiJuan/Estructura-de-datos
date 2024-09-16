/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jerarquicas;

import lineales.dinamicas.Lista;

/**
 *
 * @author juan.ramirez
 */
public class ArbolBin {

    private NodoArbol raiz;

    public ArbolBin() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre, char lugar) {
        boolean exito = true;

        if (this.raiz == null) {
            //si el arbol esta vacio, pone elem nuevo en la raiz
            this.raiz = new NodoArbol(elemNuevo, null, null);
        } else {
            //si el arbol no esta vacio, busca al padre
            NodoArbol nPadre = obtenerNodo(this.raiz, elemPadre);

            //si padre existe y lugar no esta ocupado lo pone, sino da error
            if (nPadre != null) {
                if (lugar == 'I' && nPadre.getIzquierdo() == null) {
                    nPadre.setIzquierdo(new NodoArbol(elemNuevo, null, null));
                } else if (lugar == 'D' && nPadre.getDerecho() == null) {
                    nPadre.setDerecho(new NodoArbol(elemNuevo, null, null));
                } else {
                    exito = false;
                }
            } else {
                exito = false;
            }
        }
        return exito;
    }

    private NodoArbol obtenerNodo(NodoArbol n, Object buscado) {
        //metodo PRIVADO que busca el elemento y devuelve el nodo que
        //lo contiene. Si no se encuentra buscado devuelve null
        NodoArbol resultado = null;
        if (n != null) {
            if (n.getElem().equals(buscado)) {
                //si el buscado es n, lo devuelve
                resultado = n;
            } else {
                //no es el buscado: busca el primero en el HI
                resultado = obtenerNodo(n.getIzquierdo(), buscado);
                if (resultado == null) {
                    resultado = obtenerNodo(n.getDerecho(), buscado);
                }
            }
        }
        return resultado;
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public int altura() {
        int altura = -1;
        if (!esVacio()) {
            altura = alturaAux(this.raiz, altura);
        }
        return altura;
    }

    private int alturaAux(NodoArbol nodo, int altura) {
        if (nodo != null) {
            int a1 = alturaAux(nodo.getIzquierdo(), altura + 1);
            int a2 = alturaAux(nodo.getDerecho(), altura + 1);
            if (a1 > a2) {
                altura = a1;
            } else {
                altura = a2;
            }
        }
        return altura;
    }

    public int nivel(Object elem) {
        int nivel = -1;
        if (!esVacio()) {
            nivel = nivelAux(this.raiz, elem, nivel);
        }
        return nivel;
    }

    //corregir
    private int nivelAux(NodoArbol nodo, Object elem, int nivel) {
        int n = -1;
        if (nodo != null) {
            if (nodo.getElem().equals(elem)) {
                n = nivel;
            } else {
                nivelAux(nodo.getIzquierdo(), elem, nivel + 1);
                nivelAux(nodo.getDerecho(), elem, nivel + 1);
                n = -1;
            }
        }
        return n;
    }

    public void vaciar() {
        this.raiz = null;
    }

    //public String toString (){
    //    String s="";
    //}
    public Lista listarPreorden() {
        Lista lis = new Lista();
        listarPreordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPreordenAux(NodoArbol nodo, Lista lis) {
        //metodo recursivo PRIVADO porque su parametro es de tipo NodoArbol

        if (nodo != null) {
            //visita el elemento en el nodo
            lis.insertar(nodo.getElem(), lis.longitud() + 1);//(1)

            //recorre a sus hijos en preorden
            listarPreordenAux(nodo.getIzquierdo(), lis); //(2)
            listarPreordenAux(nodo.getDerecho(), lis); //(3)
        }
    }

    public Lista listarPosorden() {
        Lista lis = new Lista();
        listarPosordenAux(this.raiz, lis);
        return lis;
    }

    private void listarPosordenAux(NodoArbol nodo, Lista lis) {
        if (nodo != null) {

            listarPreordenAux(nodo.getIzquierdo(), lis);

            listarPreordenAux(nodo.getDerecho(), lis);

            lis.insertar(nodo.getElem(), lis.longitud() + 1);

        }
    }

    public Lista listarInorden() {
        Lista lis = new Lista();
        listarInordenAux(this.raiz, lis);
        return lis;
    }

    private void listarInordenAux(NodoArbol nodo, Lista lis) {

        if (nodo != null) {

            listarInordenAux(nodo.getIzquierdo(), lis);

            lis.insertar(nodo.getElem(), lis.longitud() + 1);

            listarInordenAux(nodo.getDerecho(), lis);
        }
    }

    public String toString() {

        return toStringAux(this.raiz);
    }

    private String toStringAux(NodoArbol nodo) {
        String cadenaAux = "", cad = "ArbolBin vacio";
        if (nodo != null) {
            cad = "";
            cad += "\n" + nodo.getElem() + " ";
            if (nodo.getIzquierdo() != null) {
                cad += "HI: " + nodo.getIzquierdo().getElem() + " ";
            } else {
                cad += "HI: - ";
            }
            if (nodo.getDerecho() != null) {
                cad += "HD: " + nodo.getDerecho().getElem() + "\n";
            } else {
                cad += "HD: - \n";
            }

            if (nodo.getIzquierdo() != null) {
                cadenaAux = toStringAux(nodo.getIzquierdo());
                cad += cadenaAux;
            }
            if (nodo.getDerecho() != null) {
                cadenaAux = toStringAux(nodo.getDerecho());
                cad += cadenaAux;
            }

        }
        return cad;
    }

    public boolean verificarPatron(Lista patron) {
        boolean resultado = false;
        if (this.raiz != null) {
            Lista lis = patron.clone();
            resultado = verificarPatronAux(this.raiz, lis);
        }
        return resultado;
    }

    private boolean verificarPatronAux(NodoArbol n, Lista lis) {
        boolean resultado = true;
        if (n != null) {
            if (n.getElem().equals(lis.recuperar(1))) {
                lis.eliminar(1);
                if (!lis.esVacia()) {
                    resultado = verificarPatronAux(n.getIzquierdo(), lis);
                    if (resultado == false) {
                        resultado = verificarPatronAux(n.getDerecho(), lis);
                    }
                }
            } else {
                resultado = false;
            }
        }
        return resultado;
    }

    public Lista frontera() {
        Lista lis = new Lista();
        if (this.raiz != null) {
            fronteraAux(this.raiz, lis);
        }
        return lis;
    }

    private void fronteraAux(NodoArbol n, Lista lis) {
        if (n != null) {
            if (n.getIzquierdo() == null && n.getDerecho() == null) {
                lis.insertar(n.getElem(), lis.longitud() + 1);
            }
            fronteraAux(n.getIzquierdo(), lis);
            fronteraAux(n.getDerecho(), lis);
        }
    }

    public ArbolBin clonarInvertido() {
        ArbolBin clon = new ArbolBin();
        if (this.raiz != null) {
            clon.raiz = new NodoArbol(this.raiz.getElem(), null, null);
            clonarInvertidoAux(this.raiz, clon.raiz);
        }
        return clon;
    }

    private void clonarInvertidoAux(NodoArbol n, NodoArbol clon) {
        if (n != null) {

            if (n.getIzquierdo() != null && n.getDerecho() != null) {
                clon.setDerecho(new NodoArbol(n.getIzquierdo().getElem(), null, null));
                clon.setIzquierdo(new NodoArbol(n.getDerecho().getElem(), null, null));
            } else {
                if (n.getIzquierdo() == null && n.getDerecho() != null) {
                    clon.setIzquierdo(new NodoArbol(n.getDerecho().getElem(), null, null));
                } else if (n.getDerecho() == null && n.getIzquierdo() != null) {
                    clon.setDerecho(new NodoArbol(n.getIzquierdo().getElem(), null, null));
                }

            }
            clonarInvertidoAux(n.getIzquierdo(), clon.getDerecho());

            clonarInvertidoAux(n.getDerecho(), clon.getIzquierdo());

        }
    }

    public void completarHijos() {
        if (this.raiz != null) {
            completarHijosAux(this.raiz);
        }
    }

    private void completarHijosAux(NodoArbol n) {
        if (n != null) {
            if (!(n.getDerecho() == null && n.getIzquierdo() == null)) {
                if (n.getIzquierdo() != null && n.getDerecho() == null) {
                    n.setDerecho(new NodoArbol(n.getIzquierdo().getElem(), null, null));
                } else if (n.getIzquierdo() == null && n.getDerecho() != null) {
                    n.setIzquierdo(new NodoArbol(n.getDerecho().getElem(), null, null));
                }
                completarHijosAux(n.getIzquierdo());
                completarHijosAux(n.getDerecho());
            }
        }
    }
}
