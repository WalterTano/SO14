/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

/**
 *
 * @author Walter
 */
public class TimerEnvejecimiento {

    private static final int TICK_ENVEJECIMIENTO = 1000;
    public static final int EDAD_ENVEJECIMIENTO = 3;

    private Proceso proceso;
    private Thread hilo;

    public TimerEnvejecimiento(Proceso proceso) {
        this.proceso = proceso;
        this.hilo = new Thread(() -> {
            while (this.proceso.getEstado() == Proceso.Estado.LISTO) {
                try {
                    Thread.sleep(TICK_ENVEJECIMIENTO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.proceso.getEstado() == Proceso.Estado.LISTO) {
                    System.out.println("Timer Envejecimiento"
                            + "\nPID: " + this.proceso.getPID() + "- Info Proceso"
                            + "\nPID: " + this.proceso.getPID() + "-" + " Edad: " + this.proceso.getEdad()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Estado: " + this.proceso.getEstado().toString()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Prioridad: " + this.proceso.getPrioridad()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Tiempo restante CPU: " + this.proceso.getTiempoRestanteEnCPU()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Espera restante ES: " + this.proceso.getEsperaESRestante()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Periodo restante hasta ES: " + this.proceso.getPeriodoESRestante()
                    );
                    this.envejecimiento();
                }
            }
        });
    }

    public void iniciar() {
        if (!this.hilo.isAlive()) {
            this.hilo.start();
        }
    }

    private void envejecimiento() {
        this.proceso.incrementarEdad();
        if (this.proceso.getEdad() == EDAD_ENVEJECIMIENTO) {
            this.proceso.envejecer();
        }
    }
}
