/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoDashboard {

    // -------------------------------
    // 1. CREAR EL DATASET DEL GRÁFICO
    // -------------------------------
    public DefaultCategoryDataset crearDatasetReservas() {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();

        // EJEMPLO — más adelante lo reemplazamos con datos reales de reservas.json
        datos.addValue(10, "Reservas", "Enero");
        datos.addValue(20, "Reservas", "Febrero");
        datos.addValue(15, "Reservas", "Marzo");
        datos.addValue(30, "Reservas", "Abril");

        return datos;
    }

    // ---------------------------------
    // 2. CREAR EL GRÁFICO (BAR CHART)
    // ---------------------------------
    public JFreeChart crearGraficoReservas() {
        DefaultCategoryDataset dataset = crearDatasetReservas();

        JFreeChart grafico = ChartFactory.createBarChart(
                "Reservas Realizadas",  // Título del gráfico
                "Mes",                  // Etiqueta eje X
                "Cantidad",             // Etiqueta eje Y
                dataset                 // Datos
        );

        return grafico;
    }

    // ---------------------------------------------------
    // 3. CREAR EL PANEL QUE SE AGREGA AL DASHBOARD
    // ---------------------------------------------------
    public ChartPanel crearPanelGraficoReservas() {
        JFreeChart grafico = crearGraficoReservas();

        ChartPanel panel = new ChartPanel(grafico);
        panel.setMouseWheelEnabled(true); // Zoom con la rueda del ratón

        return panel;
    }
}


