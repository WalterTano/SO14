/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author TanoW
 */
public class Planificador {
       
    private short cantProcesadoresTotal;
    private short cantProcesadoresLibres;
    private double quantum;
    private long ultimoPID;
    Map<Integer, Proceso>[] multiNivelListos;
    Map<Integer, Proceso> procesosBloqueadosPorPID;
    Map<Integer, Proceso> procesosEnEjecucionPorPID;

    public Planificador(short cantProcesadoresTotal, double quantum) {
        this.cantProcesadoresTotal = cantProcesadoresTotal;
        this.cantProcesadoresLibres = cantProcesadoresTotal;
        this.quantum = quantum;
        this.multiNivelListos = new LinkedHashMap[Proceso.PRIORIDAD_MINIMA + 1];
        this.procesosBloqueadosPorPID = new HashMap<Integer, Proceso>();
        this.procesosEnEjecucionPorPID = new HashMap<Integer, Proceso>();
    }
    
    public boolean bloquearProceso(int pID) {
        if (this.procesosEnEjecucionPorPID.containsKey(pID)) {
            this.procesosBloqueadosPorPID.put(pID, 
                    this.procesosEnEjecucionPorPID.remove(pID));
            return true;
        }
        
        Proceso proc = new Proceso(pID);
        // TODO: REVISAR CON LINKEDHASHMAP
        for (LinkedList<Proceso> colaPrioridad : this.multiNivelListos) {
            int indice = colaPrioridad.indexOf(proc);
            if (indice != -1) {
                this.procesosBloqueadosPorPID.put(pID, colaPrioridad.get(indice));
                return colaPrioridad.remove(proc);
            }
        }
        return false;
    }
    
    public boolean desbloquearProceso(int pID) {
        Proceso proc = this.procesosBloqueadosPorPID.remove(pID);
        LinkedList<Proceso> cola = this.multiNivelListos[proc.getPrioridad()];
        cola.add(proc);
        return false;
    }
    
    public boolean insertarProceso(double tiempoEnCPU, double periodoES, double esperaES) {
        this.ultimoPID++;
        // TODO: Asignar prioridad bien y no random.
        short prioridad = (short) ThreadLocalRandom.current()
                .nextInt(Proceso.PRIORIDAD_MAXIMA, Proceso.PRIORIDAD_MINIMA + 1);
        Proceso nuevoProc = new Proceso(this.ultimoPID, tiempoEnCPU, periodoES, esperaES, prioridad);
        return this.multiNivelListos[prioridad].add(nuevoProc);
    }
    
    public boolean insertarProcesos(Collection<Proceso> procesos) {
        for (Proceso proc : procesos) {
            if (!this.insertarProceso(proc.getTiempoTotalEnCPU(), 
                    proc.getPeriodoES(), proc.getEsperaES())) {
                return false;
            }
        }
        return true;
    }

    public short getCantProcesadoresTotal() {
        return cantProcesadoresTotal;
    }

    public short getCantProcesadoresLibres() {
        return cantProcesadoresLibres;
    }

    public double getQuantum() {
        return quantum;
    }

    public LinkedList<Proceso>[] getMultiNivelListos() {
        return multiNivelListos;
    }

    public Map<Integer, Proceso> getProcesosBloqueadosPorPID() {
        return procesosBloqueadosPorPID;
    }

    public Map<Integer, Proceso> getProcesosEnEjecucionPorPID() {
        return procesosEnEjecucionPorPID;
    }

    public void setCantProcesadoresTotal(short cantProcesadoresTotal) {
        this.cantProcesadoresTotal = cantProcesadoresTotal;
    }

    public void setQuantum(double quantum) {
        this.quantum = quantum;
    }
}
