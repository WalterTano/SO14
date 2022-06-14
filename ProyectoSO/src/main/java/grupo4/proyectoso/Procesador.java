/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

public class Procesador {

    private double quantum;
    private short id;
    private Proceso proceso;
    private Planificador planificador;
    private TimerProcesador timerProcesador;

    public Procesador(short id, double quantum, Planificador planificador) {
        this.id = id;
        this.quantum = quantum;
        this.planificador = planificador;
        this.timerProcesador = new TimerProcesador(this);
    }
 
    public void agregarProceso(Proceso proceso) {
        this.proceso = proceso;
        this.timerProcesador.ejecutar();
    }
    
    public Proceso getProceso() {
        return this.proceso;
    }
    
    public Proceso removerProceso() {
        Proceso proc = this.proceso;
        this.proceso = null;
        return proc;
    }

    public double getQuantum() {
        return this.quantum;
    }
    
    public Planificador getPlanificador() {
        return this.planificador;
    }
    
    public short getId() {
        return this.id;
    }
}
