/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

import java.util.Collection;
import java.util.HashMap;

public class Planificador {

    private short cantProcesadoresTotal;
    private short cantProcesadoresLibres;
    private double quantum;                 //cantidad de tiempo durante el que el planificador le da CPU a un proceso en RR
    private long ultimoPID;
    ColaMultiNivel<Long, Proceso> multiNivelListos;
    HashMap<Long, Proceso> procesosBloqueadosPorPID;
    HashMap<Long, Proceso> procesosEnEjecucionPorPID;

    public Planificador(short pID, double quantum) {
        this.cantProcesadoresTotal = pID;
        this.cantProcesadoresLibres = pID;
        this.quantum = quantum;
        this.multiNivelListos = new ColaMultiNivel<Long, Proceso>(Proceso.PRIORIDAD_MINIMA);
        this.procesosBloqueadosPorPID = new HashMap<Long, Proceso>();
        this.procesosEnEjecucionPorPID = new HashMap<Long, Proceso>();
    }
    
    public boolean tieneProcesadoresLibres() {
        return this.cantProcesadoresLibres > 0;
    }
    
    private void actualizar() {
        
        if (this.tieneProcesadoresLibres()) {
            Proceso proceso = this.multiNivelListos.siguiente();
            while (this.cantProcesadoresLibres > 0 && proceso != null) {
                this.ejecutarProceso(proceso);
                proceso = this.multiNivelListos.siguiente();
            }
        }
    }
    
    private void ejecutarProceso(Proceso proceso) {
        this.multiNivelListos.remover(proceso.getPID(), proceso.getPrioridad());
        this.procesosEnEjecucionPorPID.put(proceso.getPID(), proceso);
        proceso.ejecutar();
    }

    public boolean bloquearProceso(Long pID) {
        if (this.procesosEnEjecucionPorPID.containsKey(pID)) {
            this.procesosBloqueadosPorPID.put(pID,
                    this.procesosEnEjecucionPorPID.remove(pID));
            this.actualizar();
            return true;
        }

        Proceso proc = this.multiNivelListos.remover(pID);
        if (proc != null) {
            this.procesosBloqueadosPorPID.put(pID, proc);
            this.actualizar();
            return true;
        }
        
        return false;
    }

    public boolean bloquearProceso(Proceso proc) {
        if (this.procesosEnEjecucionPorPID.containsKey(proc.getPID())) {
            this.procesosBloqueadosPorPID.put(proc.getPID(),
                    this.procesosEnEjecucionPorPID.remove(proc.getPID()));
            this.actualizar();
            return true;
        }

        Proceso procResult = this.multiNivelListos.remover(proc.getPID(), proc.getPrioridad());
        if (procResult != null) {
            this.procesosBloqueadosPorPID.put(procResult.getPID(), proc);
            this.actualizar();
            return true;
        }

        return false;
    }

    public boolean desbloquearProceso(Long pID) {
        if (this.procesosBloqueadosPorPID.containsKey(pID)) {
            Proceso proc = this.procesosBloqueadosPorPID.remove(pID);
            this.multiNivelListos.agregar(pID, proc, proc.getPrioridad());
            this.actualizar();
            return true;
        }

        return false;
    }
    
    public void suspenderProceso(Proceso proceso) {
        if (this.procesosEnEjecucionPorPID.containsKey(proceso.getPID())) {
            this.procesosEnEjecucionPorPID.remove(proceso.getPID());
            proceso.setEstado(Proceso.Estado.LISTO);
            this.multiNivelListos.agregar(proceso.getPID(), proceso, proceso.getPrioridad());
            this.actualizar();
        }
    }

    public boolean finalizarProceso(Long pID) {
        if (this.procesosEnEjecucionPorPID.containsKey(pID)) {
            Proceso proc = this.procesosEnEjecucionPorPID.remove(pID);
            this.actualizar();
            return true;
        }
        return false;
    }

    public boolean insertarProceso(double tiempoEnCPU, double periodoES, double esperaES, short prioridad, Proceso.Tipo tipo) {
        Proceso nuevoProc = new Proceso(++this.ultimoPID, tiempoEnCPU, periodoES, esperaES, prioridad, tipo, this);
        boolean resultado = this.multiNivelListos.agregar(nuevoProc.getPID(), nuevoProc, prioridad) != null;
        if (resultado) {
            this.actualizar();
        }
        return resultado;
    }

    public boolean insertarProceso(Proceso proceso) {
        proceso.setPID(++this.ultimoPID);
        boolean resultado = this.multiNivelListos.agregar(proceso.getPID(), proceso, proceso.getPrioridad()) != null;
        if (resultado) {
            this.actualizar();
        }
        return resultado;
    }

    public boolean insertarProcesos(Collection<Proceso> procesos) {
        for (Proceso proc : procesos) {
            if (!this.insertarProceso(proc)) {
                return false;
            }
        }
        return true;
    }

    public boolean setPrioridadProceso(Proceso proc, short prioridad) {
        Proceso proceso = this.multiNivelListos.remover(proc.getPID(), proc.getPrioridad());
        if (proceso != null) {
            proceso.setPrioridad(prioridad);
            return this.multiNivelListos.agregar(proceso.getPID(), proceso, proceso.getPrioridad()) != null;
        }

        return false;
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

    public ColaMultiNivel<Long, Proceso> getMultiNivelListos() {
        return this.multiNivelListos;
    }

    public HashMap<Long, Proceso> getProcesosBloqueadosPorPID() {
        return procesosBloqueadosPorPID;
    }

    public HashMap<Long, Proceso> getProcesosEnEjecucionPorPID() {
        return procesosEnEjecucionPorPID;
    }

    public void setCantProcesadoresTotal(short cantProcesadoresTotal) {
        this.cantProcesadoresTotal = cantProcesadoresTotal;
    }

    public void setQuantum(double quantum) {
        this.quantum = quantum;
    }
}
