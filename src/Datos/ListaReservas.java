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
import java.util.ArrayList;
import java.util.List;

public class ListaReservas {
    private NodoReserva cabeza;

    public ListaReservas() {
        cabeza = null;
    }

    public void agregar(Reserva r) {
        NodoReserva nuevo = new NodoReserva(r);
        if (cabeza == null) cabeza = nuevo;
        else {
            NodoReserva temp = cabeza;
            while (temp.siguiente != null) temp = temp.siguiente;
            temp.siguiente = nuevo;
        }
    }

    public boolean eliminar(String documento, String fechaCheckIn) {
        NodoReserva actual = cabeza;
        NodoReserva anterior = null;

        while (actual != null) {
            if (actual.dato.getDocumento().equals(documento)
                && formatear(actual.dato.getCheckIn()).equals(fechaCheckIn)) {

                if (anterior == null) cabeza = actual.siguiente;
                else anterior.siguiente = actual.siguiente;

                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    public List<Reserva> convertirListaJava() {
        List<Reserva> lista = new ArrayList<>();
        NodoReserva temp = cabeza;
        while (temp != null) {
            lista.add(temp.dato);
            temp = temp.siguiente;
        }
        return lista;
    }

    private String formatear(java.util.Date fecha) {
        if (fecha == null) return "";
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }
}
