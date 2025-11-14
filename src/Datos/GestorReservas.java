
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Administrator
 */


package Datos;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Modelo.Reserva;
import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class GestorReservas {

    private static final String ARCHIVO = "data/reservas.json";
    private final Gson gson = new Gson();

    private ListaReservas cargarLista() {
        ListaReservas lista = new ListaReservas();

        try {
            File archivo = new File(ARCHIVO);
            if (!archivo.exists()) {
                File carpeta = archivo.getParentFile();
                if (carpeta != null && !carpeta.exists()) carpeta.mkdirs();
                archivo.createNewFile();
                try (FileWriter writer = new FileWriter(archivo)) {
                    writer.write("[]");
                }
            }

            try (Reader reader = new FileReader(archivo)) {
                Type tipoLista = new TypeToken<List<Reserva>>() {}.getType();
                List<Reserva> temp = gson.fromJson(reader, tipoLista);
                if (temp != null) {
                    for (Reserva r : temp) lista.agregar(r);
                }
            }

        } catch (IOException e) {
            System.out.println("Error al leer reservas: " + e.getMessage());
        }
        return lista;
    }

    private void guardarLista(ListaReservas lista) {
        try (Writer writer = new FileWriter(ARCHIVO)) {
            gson.toJson(lista.convertirListaJava(), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void agregarReserva(Reserva nueva) {
        ListaReservas lista = cargarLista();
        lista.agregar(nueva);
        guardarLista(lista);
    }

    public boolean eliminarReserva(String documento, String checkIn) {
        ListaReservas lista = cargarLista();
        boolean eliminado = lista.eliminar(documento, checkIn);
        if (eliminado) guardarLista(lista);
        return eliminado;
    }

    public List<Reserva> obtenerTodas() {
        return cargarLista().convertirListaJava();
    }

    public List<Reserva> obtenerReservasPorDocumento(String doc) {
        List<Reserva> todas = obtenerTodas();
        return todas.stream().filter(r -> r.getDocumento().equals(doc)).toList();
    }
}
