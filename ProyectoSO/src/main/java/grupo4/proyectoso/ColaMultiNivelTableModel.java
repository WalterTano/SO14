/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

import java.util.LinkedList;
import java.util.LinkedHashMap;

/**
 *
 * @author Walter
 */
public class ColaMultiNivelTableModel extends ProcesosTableModel{
    // This is also a test
    private static final short TIEMPO_REFRESCO = 500;

    private ColaMultiNivel colaProcesos;
    
    public ColaMultiNivelTableModel(ColaMultiNivel colaProcesos) {
        super();
        this.colaProcesos = colaProcesos;
        LinkedList<Proceso> procesos = new LinkedList<Proceso>();
        for (LinkedHashMap<Long, Proceso> nivel : this.colaProcesos.getColaMultiNivel()) {
            for (Proceso proceso : nivel.values()) {
                procesos.add(proceso);
            }
        }
        this.procesos = procesos.toArray(this.procesos);
        
        new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(TIEMPO_REFRESCO);
                    LinkedList<Proceso> procesosOrdenados = new LinkedList<Proceso>();
                    for (LinkedHashMap<Long, Proceso> nivel : this.colaProcesos.getColaMultiNivel()) {
                        for (Proceso proceso : nivel.values()) {
                            procesosOrdenados.add(proceso);
                        }
                    }
                    this.procesos = procesosOrdenados.toArray(this.procesos);
                    this.fireTableDataChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }
        }).start(); 
    }
}
