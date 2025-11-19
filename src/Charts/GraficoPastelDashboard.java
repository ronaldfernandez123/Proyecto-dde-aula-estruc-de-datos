/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Administrator
 */

public class GraficoPastelDashboard {

    // -------------------------------
    // 1. CREAR EL DATASET DEL PASTEL
    // -------------------------------
    public DefaultPieDataset crearDatasetPastel() {
        DefaultPieDataset datos = new DefaultPieDataset();

        // Datos temporales (luego los conectamos al JSON)
        datos.setValue("Habitación Simple", 45);
        datos.setValue("Doble", 25);
        datos.setValue("Suite", 15);
        datos.setValue("Familiar", 15);

        return datos;
    }

    // -------------------------------
    // 2. CREAR EL GRÁFICO EN SÍ
    // -------------------------------
    public JFreeChart crearGraficoPastel() {
        return ChartFactory.createPieChart(
                "Distribución de Reservas por Tipo de Habitación", // Título
                crearDatasetPastel(), // Datos
                true,   // leyenda
                true,   // tooltips
                false   // URLs
        );
    }

    // -------------------------------
    // 3. CREAR EL PANEL PARA INSERTAR
    // -------------------------------
    public ChartPanel crearPanelGraficoPastel() {
        JFreeChart grafico = crearGraficoPastel();
        ChartPanel panel = new ChartPanel(grafico);
        panel.setMouseWheelEnabled(true); // Zoom
        return panel;
    }
}

