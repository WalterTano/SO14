/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

public class Procesador {

    private double quantum;
    private Proceso proceso;
    private Planificador planificador;

    public Procesador(double quantum, Planificador planificador) {
        this.quantum = quantum;
        this.planificador = planificador;
    }

    public void agregarProceso(Proceso proceso) {
        this.proceso = proceso;
        this.iniciarQuantum();
    }

    private void iniciarQuantum() {
        new Thread(() -> {
            try {
                Thread.sleep((long) this.quantum);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.planificador.suspenderProceso(this.proceso);
        }).start();
    }
}
