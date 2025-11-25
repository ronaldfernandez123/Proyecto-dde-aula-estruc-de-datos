/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author Administrator
 */

public class NodoReporte {
    public String id;
    public String nombre;
    public String correo;
    public String problema;
    public String descripcion;

    public NodoReporte siguiente;

    public NodoReporte(String id, String nombre, String correo, String problema, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.problema = problema;
        this.descripcion = descripcion;
        this.siguiente = null;
    }
}

