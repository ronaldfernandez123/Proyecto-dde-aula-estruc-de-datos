/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI.Panels;

import Estructuras.ListaSimpleReservas;
import Estructuras.NodoReserva;
import javax.swing.table.DefaultTableModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class PanelReservas extends javax.swing.JPanel {

    // Lista en memoria (base)
    private ListaSimpleReservas listaBase;
    // Sorter para ordenar/filtrar sin recrear listas (opcional)
    private TableRowSorter<DefaultTableModel> sorter;

    public PanelReservas() {
        initComponents();
        configurarTabla();
        cargarReservasEnTabla(); // carga inicial
        configurarEventosBotones(); // conecta botones
    }

    private void configurarTabla() {
        String[] columnas = {
            "Nombre", "Apellido", "Identificación",
            "Personas", "Habitaciones", "Check-In", "Check-Out"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabla solo lectura
            }
        };

        tblReservas.setModel(modelo);

        // Configurar sorter (opcional pero recomendado)
        sorter = new TableRowSorter<>(modelo);
        tblReservas.setRowSorter(sorter);

        // Selección de una sola fila
        tblReservas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    }

    public void cargarReservasEnTabla() {
        // 1) Cargar la lista desde JSON
        listaBase = Util.CargarReservasJSON.cargar();

        // 2) Poblar la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblReservas.getModel();
        modelo.setRowCount(0);

        NodoReserva aux = listaBase.cabeza;
        while (aux != null) {
            modelo.addRow(new Object[]{
                aux.nombres,
                aux.apellidos,
                aux.documento,    // aquí muestras "Identificación"
                aux.personas,
                aux.habitaciones,
                aux.checkIn,
                aux.checkOut
            });
            aux = aux.siguiente;
        }
    }

    private void configurarEventosBotones() {
        // Botón Actualizar: recarga archivo y repinta tabla
        btnActualizar.addActionListener(evt -> {
            cargarReservasEnTabla();
            // limpiar filtro al actualizar
            if (sorter != null) sorter.setRowFilter(null);
        });

        // Botón Buscar: filtra por cualquier columna usando regex case-insensitive
        btnBuscar.addActionListener(evt -> {
            String q = txtBuscar.getText().trim();
            if (sorter == null) return;
            if (q.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + q));
            }
        });

        // Botón Eliminar: elimina la fila seleccionada y guarda el JSON
        btnEliminar.addActionListener(evt -> eliminarSeleccionYGuardar());
    }

    private void eliminarSeleccionYGuardar() {
        int viewRow = tblReservas.getSelectedRow();
        if (viewRow < 0) {
            JOptionPane.showMessageDialog(this, "Selecciona una fila para eliminar.");
            return;
        }

        // Convertir índice de vista a índice de modelo
        int modelRow = tblReservas.convertRowIndexToModel(viewRow);
        DefaultTableModel modelo = (DefaultTableModel) tblReservas.getModel();

        // Datos para identificar el nodo en la lista (usaremos documento como clave)
        String documento = String.valueOf(modelo.getValueAt(modelRow, 2)); // columna "Identificación"

        // Eliminar de la lista enlazada base
        boolean okLista = eliminarNodoPorDocumento(listaBase, documento);
        if (!okLista) {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar en la lista base.");
            return;
        }

        // Eliminar de la tabla
        modelo.removeRow(modelRow);

        // Guardar en JSON
        boolean okGuardar = Util.GuardarReservasJSON.guardar(listaBase);
        if (!okGuardar) {
            JOptionPane.showMessageDialog(this, "Error al guardar reservas.json");
        }
    }

    // Elimina el primer nodo cuyo documento coincida exactamente
    private boolean eliminarNodoPorDocumento(ListaSimpleReservas lista, String doc) {
        if (lista.cabeza == null) return false;

        // Caso: cabeza
        if (doc.equals(lista.cabeza.documento)) {
            lista.cabeza = lista.cabeza.siguiente;
            return true;
        }

        // Recorrer para encontrar y enlazar
        NodoReserva prev = lista.cabeza;
        NodoReserva curr = lista.cabeza.siguiente;
        while (curr != null) {
            if (doc.equals(curr.documento)) {
                prev.siguiente = curr.siguiente;
                return true;
            }
            prev = curr;
            curr = curr.siguiente;
        }
        return false;
    }


      



    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblReservas = new javax.swing.JTable();
        contenedorTarjetas = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();

        tblReservas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblReservas);

        contenedorTarjetas.setBackground(new java.awt.Color(255, 255, 255));
        contenedorTarjetas.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Total Reservas");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Reservas Activas");

        jLabel4.setText("Reservas Canceladas");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel5.setText("   0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel6.setText("   0");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel7.setText("   0");

        javax.swing.GroupLayout contenedorTarjetasLayout = new javax.swing.GroupLayout(contenedorTarjetas);
        contenedorTarjetas.setLayout(contenedorTarjetasLayout);
        contenedorTarjetasLayout.setHorizontalGroup(
            contenedorTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenedorTarjetasLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(contenedorTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(119, 119, 119)
                .addGroup(contenedorTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addGroup(contenedorTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorTarjetasLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)))
                .addGap(32, 32, 32))
        );
        contenedorTarjetasLayout.setVerticalGroup(
            contenedorTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contenedorTarjetasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(contenedorTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(contenedorTarjetasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Gestion de Reservas");

        btnBuscar.setText("Buscar");

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(contenedorTarjetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(contenedorTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JPanel contenedorTarjetas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblReservas;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
