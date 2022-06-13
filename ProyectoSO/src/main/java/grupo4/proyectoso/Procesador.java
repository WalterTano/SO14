/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

public class Procesador {

    private double quantum;
    private Proceso proceso;
    private Planificador planificador;
    private Thread hilo;

    public Procesador(double quantum, Planificador planificador) {
        this.quantum = quantum;
        this.planificador = planificador;
        this.hilo = new Thread(() -> {
            try {
                Thread.sleep((long) this.quantum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.planificador.suspenderProceso(this);
        });
    }
 
    public void agregarProceso(Proceso proceso) {
        this.proceso = proceso;
        this.iniciarQuantum();
    }
    
    public Proceso getProceso() {
        return this.proceso;
    }
    
    public Proceso removerProceso() {
        Proceso proc = this.proceso;
        this.proceso = null;
        return proc;
    }

    private void iniciarQuantum() {
        if (!this.hilo.isAlive()) {
            this.hilo.start();
        }
    }
}
