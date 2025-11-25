/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

import Modelo.Reserva;

/**
 *
 * @author Administrator
 */

public class NodoReserva {
    public String nombres;
    public String apellidos;
    public String tipoIdentificacion;
    public String documento;
    public int personas;
    public int habitaciones;
    public String checkIn;
    public String checkOut;

    public NodoReserva siguiente;

    public NodoReserva(String nombres, String apellidos, String tipoIdentificacion, String documento,
                       int personas, int habitaciones, String checkIn, String checkOut) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoIdentificacion = tipoIdentificacion;
        this.documento = documento;
        this.personas = personas;
        this.habitaciones = habitaciones;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.siguiente = null;
    }
}



