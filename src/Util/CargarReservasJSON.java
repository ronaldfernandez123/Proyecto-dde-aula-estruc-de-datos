/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

import Estructuras.ListaSimpleReservas;
import Estructuras.NodoReserva;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class CargarReservasJSON {

    // Ajusta la ruta si cambias de ubicación (ver nota al final)
    private static final String RUTA = "src/data/reservas.json";

    public static ListaSimpleReservas cargar() {
        ListaSimpleReservas lista = new ListaSimpleReservas();
        try {
            JSONParser parser = new JSONParser();
            JSONArray arr = (JSONArray) parser.parse(new FileReader(RUTA));

            for (Object o : arr) {
                JSONObject obj = (JSONObject) o;

                NodoReserva nodo = new NodoReserva(
                        obj.get("nombres").toString(),
                        obj.get("apellidos").toString(),
                        obj.get("tipoIdentificacion").toString(),
                        obj.get("documento").toString(),
                        Integer.parseInt(obj.get("personas").toString()),
                        Integer.parseInt(obj.get("habitaciones").toString()),
                        obj.get("checkIn").toString(),
                        obj.get("checkOut").toString()
                );
                lista.agregar(nodo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Si falla lectura, lista vacía
        }
        return lista;
    }
}


