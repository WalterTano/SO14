/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

import java.util.ArrayList;

/**
 *
 * @author TanoW
 */
public class ProcesosTableModel extends javax.swing.table.AbstractTableModel{

    private static final int COLUMNAS = 4;
    protected Proceso[] procesos;
    
    public ProcesosTableModel() {
        this.procesos = new Proceso[0];
    }
    
    @Override
    public int getRowCount() {        
        return this.procesos.length;
    }

    @Override
    public int getColumnCount() {
        return COLUMNAS;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "PID";
            case 1: return "Tipo";
            case 2: return "Prioridad";
            case 3: return "Restante";
            case 4: return "Estado";
        }
        
        return null;
    }
    
    public Proceso obtenerProcesoEn(int fila) {
        return this.procesos[fila];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proceso p = this.procesos[rowIndex];
        if (p != null) {
            switch(columnIndex) {
                case 0: return p.getPID();
                case 1: return p.getTipo().toString();
                case 2: return String.valueOf(p.getPrioridad());
                case 3: return String.valueOf(p.getTiempoRestanteEnCPU());
                case 4: return p.getEstado().toString();
            }
        }
        
        return null;
    }
    
}
