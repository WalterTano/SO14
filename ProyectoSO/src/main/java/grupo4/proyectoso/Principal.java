/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package grupo4.proyectoso;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;

import grupo4.proyectoso.Proceso.Tipo;

/**
 *
 * @author danac
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    public Principal() {
        initComponents();
        this.panelProcesos.setVisible(false);

        MouseAdapter mouseAdapter = new SimulacionMouseAdapter(this) {
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                
                if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
                    ProcesosTableModel modelo;
                    if (table.getModel() instanceof ProcesadorTableModel) {
                        modelo = (ProcesadorTableModel) table.getModel();
                    } else {
                        modelo = (ProcesosTableModel) table.getModel();
                    }
                    
                    Proceso proc = modelo.obtenerProcesoEn(row);
                    Principal contexto = this.getContexto();
                    if (contexto.getPopupProceso() == null) {
                        contexto.setPopupProceso(new PopapProceso());
                    }
                    contexto.getPopupProceso().setPlanificador(contexto.getPlanificador());
                    contexto.getPopupProceso().setProceso(proc);
                    contexto.getPopupProceso().setVisible(true);
                }
            }
        };

        this.tblListos.addMouseListener(mouseAdapter);
        this.tblEnEjecucion.addMouseListener(mouseAdapter);
        this.tblBloqueados.addMouseListener(mouseAdapter);
        this.hiloRefrescador.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelProcesos = new javax.swing.JPanel();
        lblEnEjecucion = new javax.swing.JLabel();
        spanelEnEjecucion = new javax.swing.JScrollPane();
        tblEnEjecucion = new javax.swing.JTable();
        spanelListos = new javax.swing.JScrollPane();
        tblListos = new javax.swing.JTable();
        lblListos = new javax.swing.JLabel();
        lblBloqueados = new javax.swing.JLabel();
        spanelBloqueados = new javax.swing.JScrollPane();
        tblBloqueados = new javax.swing.JTable();
        btnAgregarProceso = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        panelInicial = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblTiempoEjecucion = new javax.swing.JLabel();
        spnCantProcesadores = new javax.swing.JSpinner();
        lblCantProcesadores = new javax.swing.JLabel();
        spnQuantum = new javax.swing.JSpinner();
        btnIniciar = new javax.swing.JButton();
        lblMs = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEnEjecucion.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        lblEnEjecucion.setText("Procesos en ejecución");

        tblEnEjecucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        spanelEnEjecucion.setViewportView(tblEnEjecucion);

        tblListos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        spanelListos.setViewportView(tblListos);

        lblListos.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        lblListos.setText("Procesos listos");

        lblBloqueados.setFont(new java.awt.Font("DialogInput", 1, 14)); // NOI18N
        lblBloqueados.setText("Procesos bloqueados");

        tblBloqueados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        spanelBloqueados.setViewportView(tblBloqueados);

        btnAgregarProceso.setText("Agregar Proceso");
        btnAgregarProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProcesoActionPerformed(evt);
            }
        });

        btnVolver.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelProcesosLayout = new javax.swing.GroupLayout(panelProcesos);
        panelProcesos.setLayout(panelProcesosLayout);
        panelProcesosLayout.setHorizontalGroup(
            panelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcesosLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(panelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelProcesosLayout.createSequentialGroup()
                        .addComponent(btnVolver)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelProcesosLayout.createSequentialGroup()
                        .addGroup(panelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spanelListos, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelProcesosLayout.createSequentialGroup()
                                .addComponent(lblListos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAgregarProceso)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                        .addGroup(panelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblEnEjecucion)
                            .addComponent(spanelEnEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblBloqueados)
                            .addComponent(spanelBloqueados, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(26, Short.MAX_VALUE))))
        );
        panelProcesosLayout.setVerticalGroup(
            panelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelProcesosLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEnEjecucion)
                    .addComponent(lblListos)
                    .addComponent(btnAgregarProceso))
                .addGap(25, 25, 25)
                .addGroup(panelProcesosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelProcesosLayout.createSequentialGroup()
                        .addComponent(spanelEnEjecucion, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(lblBloqueados)
                        .addGap(28, 28, 28)
                        .addComponent(spanelBloqueados, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spanelListos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        panelInicial.setName("Panel_inicial"); // NOI18N

        lblTitulo.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lblTitulo.setText("Planificador de procesos");

        lblTiempoEjecucion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblTiempoEjecucion.setText("Quantum:");

        spnCantProcesadores.setModel(new javax.swing.SpinnerNumberModel(1, 1, 32, 1));

        lblCantProcesadores.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        lblCantProcesadores.setText("Cantidad de procesadores:");

        spnQuantum.setModel(new javax.swing.SpinnerNumberModel(100, 100, null, 10));

        btnIniciar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });

        lblMs.setText("ms");

        javax.swing.GroupLayout panelInicialLayout = new javax.swing.GroupLayout(panelInicial);
        panelInicial.setLayout(panelInicialLayout);
        panelInicialLayout.setHorizontalGroup(
            panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicialLayout.createSequentialGroup()
                .addContainerGap(267, Short.MAX_VALUE)
                .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelInicialLayout.createSequentialGroup()
                        .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTiempoEjecucion)
                            .addComponent(lblCantProcesadores))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnQuantum)
                            .addComponent(spnCantProcesadores, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMs))
                    .addGroup(panelInicialLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(268, Short.MAX_VALUE))
        );
        panelInicialLayout.setVerticalGroup(
            panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInicialLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(48, 48, 48)
                .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTiempoEjecucion)
                    .addGroup(panelInicialLayout.createSequentialGroup()
                        .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnCantProcesadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCantProcesadores))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelInicialLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spnQuantum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMs))))
                .addGap(48, 48, 48)
                .addComponent(btnIniciar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(385, Short.MAX_VALUE))
        );

        lblMs.getAccessibleContext().setAccessibleName("lblMs");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 879, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelProcesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(panelInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(panelProcesos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        double quantum = Double.valueOf(String.valueOf(this.spnQuantum.getValue()));
        short cantProcesadores = Short.valueOf(String.valueOf(this.spnCantProcesadores.getValue()));
        this.planificador = new Planificador(cantProcesadores, quantum);
        this.panelInicial.setVisible(false);
        this.panelProcesos.setVisible(true);
        
        this.modeloTablaBloqueados = new ProcesosBloqueadosTableModel(this.planificador.getProcesosBloqueadosPorPID());
        this.tblBloqueados.setModel(this.modeloTablaBloqueados);
        this.modeloTablaEnEjecucion = new ProcesadorTableModel(this.planificador.getProcesadores());
        this.tblEnEjecucion.setModel(this.modeloTablaEnEjecucion);
        this.modeloTablaListos = new ColaMultiNivelTableModel(this.planificador.getMultiNivelListos());
        this.tblListos.setModel(this.modeloTablaListos);

        //for (int i = 0; i < 300; i++) {
        //    double tiempoEnCPU = 60000;
        //    double periodoES = 200;
        //    double esperaES = 5000;
        //    short prioridad = 55;
        //    Proceso.Tipo tipo = Proceso.Tipo.USUARIO;
        //    this.planificador.insertarProceso(tiempoEnCPU, periodoES, esperaES, prioridad, tipo);
        //}
    }//GEN-LAST:event_btnIniciarActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        this.planificador = null;
        this.panelInicial.setVisible(true);
        this.panelProcesos.setVisible(false);

    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnAgregarProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProcesoActionPerformed
        if (this.popupAgregarProceso != null && this.popupAgregarProceso.isVisible())
            return;
        
        this.popupAgregarProceso = new PopapAgregarProceso(this.planificador);
        this.popupAgregarProceso.setVisible(true);
    }//GEN-LAST:event_btnAgregarProcesoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    
    private static final short TIEMPO_REFRESCO = 100;
    
    private Planificador planificador;
    private PopapProceso popupProceso;
    private PopapAgregarProceso popupAgregarProceso;
    private javax.swing.table.AbstractTableModel modeloTablaListos;
    private javax.swing.table.AbstractTableModel modeloTablaBloqueados;
    private javax.swing.table.AbstractTableModel modeloTablaEnEjecucion;
    private Thread hiloRefrescador = new Thread(() -> {
        while(true) {
                try {
                    Thread.sleep(TIEMPO_REFRESCO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                this.tblBloqueados.repaint();
                this.tblEnEjecucion.repaint();
                this.tblListos.repaint();
            }
    });
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarProceso;
    private javax.swing.JButton btnIniciar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel lblBloqueados;
    private javax.swing.JLabel lblCantProcesadores;
    private javax.swing.JLabel lblEnEjecucion;
    private javax.swing.JLabel lblListos;
    private javax.swing.JLabel lblMs;
    private javax.swing.JLabel lblTiempoEjecucion;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel panelInicial;
    private javax.swing.JPanel panelProcesos;
    private javax.swing.JScrollPane spanelBloqueados;
    private javax.swing.JScrollPane spanelEnEjecucion;
    private javax.swing.JScrollPane spanelListos;
    private javax.swing.JSpinner spnCantProcesadores;
    private javax.swing.JSpinner spnQuantum;
    private javax.swing.JTable tblBloqueados;
    private javax.swing.JTable tblEnEjecucion;
    private javax.swing.JTable tblListos;
    // End of variables declaration//GEN-END:variables

    public Planificador getPlanificador() {
        return this.planificador;
    }
    
    public PopapProceso getPopupProceso() {
        return this.popupProceso;
    }
    
    public void setPopupProceso(PopapProceso popup) {
        this.popupProceso = popup;
    }
    
    public class SimulacionMouseAdapter extends MouseAdapter {
        private Principal contexto;
        
        public SimulacionMouseAdapter(Principal contexto) {
            this.contexto = contexto;
        }
        
        public Principal getContexto() {
            return this.contexto;
        }
    }
}
