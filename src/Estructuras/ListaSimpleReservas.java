/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;



/**
 *
 * @author Administrator
 */

public class ListaSimpleReservas {
    public NodoReserva cabeza;

    public void agregar(NodoReserva nodo) {
        if (cabeza == null) {
            cabeza = nodo;
        } else {
            NodoReserva aux = cabeza;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nodo;
        }
    }
}




