/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Datos;

/**
 *
 * @author SARA
 */
import Modelo.Reserva;

public class NodoReserva {
    public Reserva dato;
    public NodoReserva siguiente;

    public NodoReserva(Reserva dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

