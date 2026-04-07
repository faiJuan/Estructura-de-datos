
package jerarquicas;

import lineales.dinamicas.Lista;
import lineales.dinamicas.Cola;

public class ArbolGen {

    private NodoGen raiz;

    // constructor
    public ArbolGen() {
        this.raiz = null;
    }

    public boolean insertar(Object elemNuevo, Object elemPadre) {
        boolean exito = true;
        // Metodo para insertar elementos al arbol, en caso de que el arbol
        // este vacio el elemento que entra se inserta en la raiz, en caso contrario
        // se necesita saber a que nodo padre se va a insertar
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
        // Busca el nodo que contiene al elemento que entra
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
        // llamado recursivo con el primer hijo de n
        if (n.getHijoIzquierdo() != null) {
            listarInordenAux(n.getHijoIzquierdo(), ls);
        }
        // visita del nodo n
        ls.insertar(n.getElem(), ls.longitud() + 1);

        // llamados recursivos con los otros hijos de n
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
            // visita del nodo n
            s += n.getElem().toString() + " -> ";
            NodoGen hijo = n.getHijoIzquierdo();
            while (hijo != null) {
                s += hijo.getElem().toString() + ", ";
                hijo = hijo.getHermanoDerecho();
            }
            // comienza recorrido de los hijos de n llamando recursivamente
            // para que cada hijo agregue su subcadena a la general
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

    // De aqui en adelante son ejercicios para el parcial
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
        // elimina un elemento del arbol junto con sus desendientes
        boolean exito = false;
        if (!esVacio()) {
            exito = eliminarAux(this.raiz, elem, null, null);
        }
        return exito;
    }

    private boolean eliminarAux(NodoGen n, Object elem, NodoGen padre, NodoGen anterior) {
        boolean exito = false;
        if (n != null) {
            // si n es null no entra y devuelve false
            if (n.getElem().equals(elem)) {
                // compara el elemento, en caso de que padre sea null significa que es raiz
                if (padre == null) {
                    this.raiz = null;
                    exito = true;
                } else {
                    if (anterior == null) {
                        /*
                         * si anterior es null significa que es HI por ende no tiene
                         * un nodo anterior a el y procede a eliminarlo
                         */
                        padre.setHijoIzquierdo(n.getHermanoDerecho());
                    } else {
                        /*
                         * si anterior es !=null entonces es un hermano, procede a eliminarlo
                         * seteando al hermano anterior con el que le sigue a n
                         */
                        anterior.setHermanoDerecho(n.getHermanoDerecho());
                    }
                    exito = true;
                }
            } else {
                exito = eliminarAux(n.getHijoIzquierdo(), elem, n, null);
                // si no encontro el elem se va por el HI, con n como padre
                if (exito == false) {
                    // en caso de no estar en HI pasa a HD, con n como anterior
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
            // if () {

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

    // practica para final
    // metodo que devuelve un camino desde la raiz hasta la hoja mas cercana a
    // esta.El metodo recorre el arbol lo menos posible
    // ejercicio de final 2019
    public Lista caminoAHojaMasCercana() {
        Lista camino = new Lista();
        Lista aux = new Lista();
        if (!this.esVacio()) {
            camino = caminoMasCortoAux(raiz, camino, aux);
        }
        return camino;
    }

    private Lista caminoMasCortoAux(NodoGen n, Lista caminoMasCorto, Lista aux) {
        if (n != null) {
            if (aux.longitud() < caminoMasCorto.longitud() || caminoMasCorto.esVacia()) {
                aux.insertar(n.getElem(), aux.longitud() + 1);
                caminoMasCorto = caminoMasCortoAux(n.getHijoIzquierdo(), caminoMasCorto, aux);
                aux.eliminar(aux.longitud());
                if (n.getHermanoDerecho() != null) {
                    caminoMasCorto = caminoMasCortoAux(n.getHermanoDerecho(), caminoMasCorto, aux);
                }
            }
        } else {
            if (caminoMasCorto.esVacia() || caminoMasCorto.longitud() > aux.longitud()) {
                caminoMasCorto = aux.clone();
            }
        }
        return caminoMasCorto;
    }

    // metodo que devuelve un camino a la hoja mas lejana desde la raiz. Si hay
    // varias hojas en el mismo nivel devuelve
    // el primer camino que encuentra
    // ejercicio de final 2018

    public Lista caminoAHojaMasLejana() {
        Lista camino = new Lista();
        Lista aux = new Lista();
        if (!this.esVacio()) {
            camino = caminoMasLejanoAux(raiz, camino, aux);
        }
        return camino;
    }

    private Lista caminoMasLejanoAux(NodoGen n, Lista caminoMasLargo, Lista aux) {
        if (n != null) {
            aux.insertar(n.getElem(), aux.longitud() + 1);
            caminoMasLargo = caminoMasLejanoAux(n.getHijoIzquierdo(), caminoMasLargo, aux);
            aux.eliminar(aux.longitud());
            if (n.getHermanoDerecho() != null) {
                caminoMasLargo = caminoMasLejanoAux(n.getHermanoDerecho(), caminoMasLargo, aux);
            }

        } else {
            if (caminoMasLargo.esVacia() || caminoMasLargo.longitud() < aux.longitud()) {
                caminoMasLargo = aux.clone();
            }
        }
        return caminoMasLargo;
    }

    // altura2
    public int altu() {
        int[] suma = new int[2];
        suma[0] = -1;
        suma[1] = -1;
        if (this.raiz != null) {
            altuAux(raiz, suma);
        }
        return suma[0];
    }

    private void altuAux(NodoGen n, int[] sumas) {
        if (n != null) {
            sumas[1] += 1;
            altuAux(n.getHijoIzquierdo(), sumas);
            sumas[1] -= 1;
            altuAux(n.getHermanoDerecho(), sumas);
        } else {
            if (sumas[0] < sumas[1]) {
                sumas[0] = sumas[1];
            }
        }
    }

    // verifica si una lista que entra es frontera
    public boolean frontera(Lista lis) {
        boolean frontera = false;
        if (raiz != null && !lis.esVacia()) {
            frontera = frontAux(this.raiz, lis.clone());
        }
        return frontera;
    }

    private boolean frontAux(NodoGen n, Lista lis) {
        boolean frontera = false;
        if (n.getHijoIzquierdo() != null) {
            frontera = frontAux(n.getHijoIzquierdo(), lis);
        } else {
            if (n.getElem().equals(lis.recuperar(1))) {
                lis.eliminar(1);
                frontera = true;
            }
        }
        if (n.getHermanoDerecho() != null && frontera) {
            frontera = frontAux(n.getHermanoDerecho(), lis);
        }
        return frontera;
    }

    public boolean insertarPorPosicion(Object elem, int posPadre) {
        boolean exito = false;
        int[] posicion = new int[1];
        posicion[0] = 1;
        if (this.raiz != null && posPadre > 0) {
            exito = insertarPorPosicionAux(this.raiz, elem, posPadre, posicion);

        }
        return exito;
    }

    private boolean insertarPorPosicionAux(NodoGen n, Object elem, int posPadre, int[] posicion) {
        boolean exito = false;
        if (posPadre == posicion[0]) {
            n.setHijoIzquierdo(new NodoGen(elem, null, n.getHijoIzquierdo()));
        } else {
            posicion[0] = posicion[0] + 1;
            if (n.getHijoIzquierdo() != null) {
                exito = insertarPorPosicionAux(n.getHijoIzquierdo(), elem, posPadre, posicion);
            } else if (n.getHermanoDerecho() != null) {
                exito = insertarPorPosicionAux(n.getHermanoDerecho(), elem, posPadre, posicion);
            }
        }
        return exito;
    }

    public boolean ancestro(Object elem, Object ancestro) {
        boolean exito = false;

        if (this.raiz != null) {
            boolean encontrado = false;
            Lista visitados = new Lista();
            // exito = ancestroAux(this.raiz, elem, ancestro, encontrado, visitados);
            exito = ances2Aux(this.raiz, elem, ancestro, visitados);
        }

        return exito;
    }

    // metodo que verifica si un elemento es acentro de otro elemento dado, se
    // considera que pueden haber elementos repetidos
    // por lo cual el recorrido debe ser total ya que puede exister una relacion de
    // ancestros en la ultima rama
    private boolean ances2Aux(NodoGen n, Object elem, Object ancestro, Lista visitados) {
        boolean encontrado = false;
        if (n.getElem().equals(elem)) {
            if (visitados.localizar(ancestro) != -1) {
                encontrado = true;
            }
        }
        if (!encontrado) {
            if (n.getHijoIzquierdo() != null) {
                visitados.insertar(n.getElem(), visitados.longitud() + 1);
                System.out.println(visitados.toString());
                encontrado = ances2Aux(n.getHijoIzquierdo(), elem, ancestro, visitados);
                visitados.eliminar(visitados.longitud());
            }
            if (n.getHermanoDerecho() != null && !encontrado) {
                encontrado = ances2Aux(n.getHermanoDerecho(), elem, ancestro, visitados);
            }
        }
        return encontrado;
    }

    // primera version de ancestros pensada con franco.
    private boolean ancestroAux(NodoGen n, Object elem, Object ancestro, boolean encontrado, Lista visitados) {
        if (n != null && !encontrado) {
            visitados.insertar(n.getElem(), visitados.longitud() + 1);
            System.out.println(visitados.toString());
            if (n.getElem().equals(elem)) {
                if (visitados.localizar(ancestro) != -1) {
                    encontrado = true;
                } else {
                    NodoGen hijo = n.getHijoIzquierdo();
                    while (hijo != null) {
                        encontrado = ancestroAux(hijo, elem, ancestro, encontrado, visitados);
                        visitados.eliminar(visitados.longitud());
                        hijo = hijo.getHermanoDerecho();
                    }
                }
            } else {
                NodoGen hijo = n.getHijoIzquierdo();
                while (hijo != null) {
                    encontrado = ancestroAux(hijo, elem, ancestro, encontrado, visitados);
                    visitados.eliminar(visitados.longitud());
                    hijo = hijo.getHermanoDerecho();
                }
            }

        }
        return encontrado;
    }

    // verificarPatron(lisPatron), devuelve un booleano donde se busca un camino
    // desde la raiz a una hoja

    public boolean verificarPatron(Lista lisPatron) {
        boolean exito = false;
        if (this.raiz != null && !lisPatron.esVacia()) {
            exito = verificarPatronAux(this.raiz, lisPatron, lisPatron.longitud(), 1);
        }
        return exito;
    }

    private boolean verificarPatronAux(NodoGen n, Lista lis, int limite, int posActual) {
        boolean exito = false;
        if (n != null) {
            if (posActual < limite) {
                if (lis.recuperar(posActual).equals(n.getElem())) {
                    exito = verificarPatronAux(n.getHijoIzquierdo(), lis, limite, posActual + 1);
                } else {
                    exito = verificarPatronAux(n.getHermanoDerecho(), lis, limite, posActual);
                }
            } else if (posActual == limite) {
                if (n.getHijoIzquierdo() == null) {
                    exito = true;
                }
            }

        }
        return exito;
    }

    // camino desde la raiz hasta la hoja mas cercana(tratar de hacer con colas por
    // nivel)

    public Lista caminoHojaCercana(){
        Lista camino=new Lista();
        Lista visitados=new Lista();
        if(this.raiz!=null){
            caminoHojaCercanaAux(this.raiz,camino,visitados);
        }
        return camino;
    }

    private Lista caminoHojaCercanaAux(NodoGen n,Lista camino,Lista visitados){
        visitados.insertar(n.getElem(),visitados.longitud()+1);
        if(n.getHijoIzquierdo()!=null){
            if(camino.longitud()-1<visitados.longitud()){
            caminoHojaCercanaAux(n.getHijoIzquierdo(), camino, visitados);
            }
        }else{
            camino=visitados.clone();
        }
        visitados.eliminar(visitados.longitud());
        if(n.getHermanoDerecho()!=null && camino.longitud()-1<visitados.longitud()){
            caminoHojaCercanaAux(n.getHermanoDerecho(), camino, visitados);
        }
        return camino;
    }

    // descendienteMasCercano(dato d) que devuelve el nivel del elemento
    // con valor d que esta mas cerca de la raiz.Uitilizar representacion dinamina
    // HEI-HD.

    public int descendienteMasCercano(Object elem) {
        int[] altu = { 0 };
        int[] nivel = { Integer.MAX_VALUE}; //para que encuentre al menos un primer camino
        if (this.raiz != null) {
            descendienteMasCercanoAux(this.raiz, elem, altu, nivel);
        }
        return nivel[0];
    }

    private void descendienteMasCercanoAux(NodoGen n, Object elem, int[] altura, int[] valorFinal) {
        altura[0]++;
        if (n.getElem().equals(elem)) {
            valorFinal[0] = altura[0];
        } else {
            NodoGen hermano = n;
            while (hermano != null) {
                if (altura[0] -1< valorFinal[0] && n.getHijoIzquierdo() != null) {
                    descendienteMasCercanoAux(n.getHijoIzquierdo(), elem, altura, valorFinal);
                }
                hermano.getHermanoDerecho();
            }

        }
        altura[0]--;
    }


    public Lista listaQueJustificaAltura(){
        Lista camino=new Lista();
        Lista visitados=new Lista();

        if(this.raiz!=null){
            camino=justificaAlturaAux(this.raiz,camino,visitados);
        }
        return camino;
    }

    private Lista justificaAlturaAux(NodoGen n, Lista camino, Lista visitados){
        if(n.getHijoIzquierdo()!=null){
            visitados.insertar(n.getElem(), visitados.longitud()+1);
            camino=justificaAlturaAux(n.getHijoIzquierdo(), camino, visitados);
            visitados.eliminar(visitados.longitud());
        }else{
            if(visitados.longitud()>camino.longitud()-1 || camino.esVacia()){
                camino=visitados.clone();
                camino.insertar(n.getElem(), camino.longitud()+1);
            }
        }

        if(n.getHermanoDerecho()!=null){
            camino=justificaAlturaAux(n.getHermanoDerecho(), camino, visitados);
        }

        return camino;
    }
}
