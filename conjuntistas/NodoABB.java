/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conjuntistas;

/**
 *
 * @author juan.ramirez
 */
public class NodoABB {
    private Comparable elem;
    private NodoABB izquierdo;
    private NodoABB derecho;
    
    public NodoABB (Comparable elem, NodoABB izquierdo, NodoABB derecho){
        this.elem=elem;
        this.izquierdo=izquierdo;
        this.derecho=derecho;
    }
    
    public Comparable getElem (){
        return this.elem;
    }
    
    public NodoABB getIzquierdo (){
        return this.izquierdo;
    }
    
    public NodoABB getDerecho (){
        return this.derecho;
    }
    
    public void setElem (Comparable elem){
        this.elem=elem;
    }
    
    public void setIzquierdo (NodoABB izquierdo){
        this.izquierdo=izquierdo;
    }
    
    public void setDerecho (NodoABB derecho){
        this.derecho=derecho;
    }
    
}
