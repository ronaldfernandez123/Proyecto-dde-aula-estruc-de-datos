/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Estructuras.ListaSimpleReservas;
import Estructuras.NodoReserva;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class GuardarReservasJSON {

    // Ajusta la ruta si es necesario (ver nota al final)
    private static final String RUTA = "src/data/reservas.json";

    public static boolean guardar(ListaSimpleReservas lista) {
        JSONArray arr = new JSONArray();

        NodoReserva aux = lista.cabeza;
        while (aux != null) {
            JSONObject obj = new JSONObject();
            obj.put("nombres", aux.nombres);
            obj.put("apellidos", aux.apellidos);
            obj.put("tipoIdentificacion", aux.tipoIdentificacion);
            obj.put("documento", aux.documento);
            obj.put("personas", aux.personas);
            obj.put("habitaciones", aux.habitaciones);
            obj.put("checkIn", aux.checkIn);
            obj.put("checkOut", aux.checkOut);
            arr.add(obj);

            aux = aux.siguiente;
        }

        try (java.io.FileWriter writer = new java.io.FileWriter(RUTA)) {
            writer.write(arr.toJSONString());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}


