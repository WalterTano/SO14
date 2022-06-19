/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

/**
 *
 * @author Walter
 */
public class ProcesadorTableModel extends ProcesosTableModel {
    
    private static final int COLUMNAS = 5;
    
    private Procesador[] procesadores;
    
    public ProcesadorTableModel(Procesador[] procesadores) {
        super();
        this.procesadores = procesadores;
    }
    
    @Override
    public Proceso obtenerProcesoEn(int fila) {
        return this.procesadores[fila].getProceso();
    }
    
    @Override
    public int getRowCount() {
        return this.procesadores.length;
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
            case 4: return "E/S En";
        }
        
        return null;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Procesador procesador = this.procesadores[rowIndex];
        if (procesador != null) {
            Proceso p = procesador.getProceso();
            if (p != null) {
                switch(columnIndex) {
                    case 0: return p.getPID();
                    case 1: return p.getTipo().toString();
                    case 2: return String.valueOf(p.getPrioridad() + 1);
                    case 3: return String.valueOf(p.getTiempoRestanteEnCPU());
                    case 4: return String.valueOf(p.getPeriodoESRestante());
                }
            }
        }
        
        
        return null;
    }
}
