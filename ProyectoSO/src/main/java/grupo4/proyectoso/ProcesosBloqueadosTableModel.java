/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

/**
 *
 * @author Walter
 */
public class ProcesosBloqueadosTableModel extends ProcesosTableModel {
    
    private static final short TIEMPO_REFRESCO = 100;
    private static final int COLUMNAS = 5;
    
    private Map<Long, Proceso> procesosBloqueadosPorPID;
    
    public ProcesosBloqueadosTableModel(Map<Long, Proceso> procesosBloqueadosPorPID) {
        super();
        this.procesosBloqueadosPorPID = procesosBloqueadosPorPID;
        LinkedList<Proceso> procesosBloqueados = new LinkedList<>(this.procesosBloqueadosPorPID.values());
        Collections.sort(procesosBloqueados);
        this.procesos = procesosBloqueados.toArray(this.procesos);

        new Thread(() -> {
           while(true) {
                try {
                    Thread.sleep(TIEMPO_REFRESCO);
                    LinkedList<Proceso> procesos = new LinkedList<>(this.procesosBloqueadosPorPID.values());
                    
                    Collections.sort(procesos, Collections.reverseOrder());
                    this.procesos = procesos.toArray(this.procesos);
                    this.fireTableDataChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    @Override
    public int getColumnCount() {
        return COLUMNAS;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "PID";
            case 1: return "Prioridad";
            case 2: return "Restante";
            case 3: return "Espera";
            case 4: return "Motivo";
        }
        
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Proceso p = this.procesos[rowIndex];
        if (p != null) {
            switch(columnIndex) {
                case 0: return p.getPID();
                case 1: return String.valueOf(p.getPrioridad());
                case 2: return String.valueOf(p.getTiempoRestanteEnCPU());
                case 3: return String.valueOf(p.getEsperaESRestante());
                case 4: return p.getMotivoBloqueo();
            }
        }
        
        return null;
    }
}
