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
    Procesador[] procesadores;

    public Planificador(short cantProcesadores, double quantum) {
        this.cantProcesadoresTotal = cantProcesadores;
        this.cantProcesadoresLibres = cantProcesadores;
        this.quantum = quantum;
        this.multiNivelListos = new ColaMultiNivel<Long, Proceso>(Proceso.PRIORIDAD_MINIMA);
        this.procesosBloqueadosPorPID = new HashMap<Long, Proceso>();
        this.procesadores = new Procesador[cantProcesadores];
        for (int i = 0; i < cantProcesadores; i++) {
            this.procesadores[i] = new Procesador(quantum, this);
        }
    }
    
    public boolean tieneProcesadoresLibres() {
        return this.cantProcesadoresLibres > 0;
    }
    
    private void actualizar() {
        if (this.tieneProcesadoresLibres()) {
            Proceso proceso = this.multiNivelListos.siguiente();
            
            while (this.cantProcesadoresLibres > 0 && proceso != null) {
                this.despacharProceso(proceso);
                proceso = this.multiNivelListos.siguiente();
            }
        }
    }
    
    private void despacharProceso(Proceso proceso) {
        for (Procesador procesador : this.procesadores) {
            if (procesador.getProceso() == null) {
                procesador.agregarProceso(proceso);
                break;
            }
        }
        this.cantProcesadoresLibres--;
        proceso.ejecutar();
    }

    public boolean bloquearProceso(Long pID) {
        for (Procesador procesador : this.procesadores) {
            if (procesador.getProceso() == null) {
                continue;
            }
            
            if (procesador.getProceso().getPID() == pID) {
                this.procesosBloqueadosPorPID.put(pID, procesador.removerProceso());
                this.cantProcesadoresLibres++;
                this.actualizar();
                return true;
            }
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
        for (Procesador procesador : this.procesadores) {
            if (procesador.getProceso() == null) {
                continue;
            }
            
            if (procesador.getProceso().equals(proc)) {
                this.procesosBloqueadosPorPID.put(proc.getPID(), procesador.removerProceso());
                this.cantProcesadoresLibres++;
                this.actualizar();
                return true;
            }
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
    
    public void suspenderProceso(Procesador procesador) {
        Proceso proc = procesador.getProceso();
        if (proc != null && proc.getEstado() == Proceso.Estado.EN_EJECUCION) {
            Proceso proceso = procesador.removerProceso();
            proceso.setEstado(Proceso.Estado.LISTO);
            this.cantProcesadoresLibres++;
            this.multiNivelListos.agregar(proceso.getPID(), proceso, proceso.getPrioridad());
            proceso.suspender();
            this.actualizar();
        }
    }

    public boolean finalizarProceso(Proceso proceso) {
        for (Procesador procesador : this.procesadores) {
            if (procesador.getProceso() == null) {
                continue;
            }
            
            if (procesador.getProceso().equals(proceso)) {
                Proceso proc = procesador.removerProceso();
                this.cantProcesadoresLibres++;
                this.actualizar();
                return true;
            }
        }
        return false;
    }

    public boolean insertarProceso(double tiempoEnCPU, double periodoES, double esperaES, short prioridad, Proceso.Tipo tipo) {
        Proceso nuevoProc = new Proceso(++this.ultimoPID, tiempoEnCPU, periodoES, esperaES, prioridad, tipo, this);
        boolean resultado = this.multiNivelListos.agregar(nuevoProc.getPID(), nuevoProc, prioridad) != null;
        if (resultado) {
            nuevoProc.iniciarEnvejecimiento();
            this.actualizar();
        }
        return resultado;
    }

    public boolean insertarProceso(Proceso proceso) {
        proceso.setPID(++this.ultimoPID);
        boolean resultado = this.multiNivelListos.agregar(proceso.getPID(), proceso, proceso.getPrioridad()) != null;
        if (resultado) {
            proceso.iniciarEnvejecimiento();
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
            if (this.multiNivelListos.agregar(proceso.getPID(), proceso, proceso.getPrioridad()) != null) {
                proceso.iniciarEnvejecimiento();
                return true;
            }
        }

        return false;
    }

    public short getCantProcesadoresTotal() {
        return this.cantProcesadoresTotal;
    }

    public short getCantProcesadoresLibres() {
        return this.cantProcesadoresLibres;
    }

    public double getQuantum() {
        return this.quantum;
    }

    public ColaMultiNivel<Long, Proceso> getMultiNivelListos() {
        return this.multiNivelListos;
    }

    public HashMap<Long, Proceso> getProcesosBloqueadosPorPID() {
        return this.procesosBloqueadosPorPID;
    }

    public Procesador[] getProcesosEnEjecucionPorPID() {
        return this.procesadores;
    }

    public void setCantProcesadoresTotal(short cantProcesadoresTotal) {
        this.cantProcesadoresTotal = cantProcesadoresTotal;
    }

    public void setQuantum(double quantum) {
        this.quantum = quantum;
    }
}
