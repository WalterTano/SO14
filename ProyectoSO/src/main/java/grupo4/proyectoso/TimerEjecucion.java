/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

public class TimerEjecucion {

    private static final int TICK_PROCESO = 100;

    private Proceso proceso;
    private Thread hilo;

    public TimerEjecucion(Proceso proceso) {
        this.proceso = proceso;
        this.hilo = new Thread(() -> {
            while (this.proceso.getEstado() == Proceso.Estado.EN_EJECUCION
                    && this.proceso.getEstado() == Proceso.Estado.BLOQUEADO) {
                try {
                    Thread.sleep(TICK_PROCESO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.proceso.getEstado() == Proceso.Estado.EN_EJECUCION
                        && this.proceso.getEstado() == Proceso.Estado.BLOQUEADO) {
                    System.out.println("Timer Ejecución"
                            + "\nPID: " + this.proceso.getPID() + "- Info Proceso"
                            + "\nPID: " + this.proceso.getPID() + "-" + " Edad: " + this.proceso.getEdad()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Estado: " + this.proceso.getEstado().toString()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Prioridad: " + this.proceso.getPrioridad()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Tiempo restante CPU: " + this.proceso.getTiempoRestanteEnCPU()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Espera restante ES: " + this.proceso.getEsperaESRestante()
                            + "\nPID: " + this.proceso.getPID() + "-" + " Periodo restante hasta ES: " + this.proceso.getPeriodoESRestante()
                    );
                    this.gestionarEstados();
                }

            }
        });
    }

    public void iniciar() {
        if (!this.hilo.isAlive()) {
            this.hilo.start();
        }
    }

    private void gestionarEstados() {
        if (this.proceso.getEstado() == Proceso.Estado.EN_EJECUCION) {
            double tiempoRestanteEnCPU = this.proceso.getTiempoRestanteEnCPU() - TICK_PROCESO;
            this.proceso.setTiempoRestanteEnCPU(tiempoRestanteEnCPU);

            if (tiempoRestanteEnCPU == 0) {
                this.proceso.finalizar();
            }

            double periodoESRestante = this.proceso.getPeriodoESRestante() - TICK_PROCESO;
            this.proceso.setPeriodoESRestante(periodoESRestante);

            if (periodoESRestante == 0) {
                this.proceso.bloquear();
            }
        }

        if (this.proceso.getEstado() == Proceso.Estado.BLOQUEADO) {
            double esperaESRestante = this.proceso.getEsperaESRestante() - TICK_PROCESO;
            this.proceso.setEsperaESRestante(esperaESRestante);

            if (esperaESRestante == 0) {
                this.proceso.desbloquear();
            }
        }

    }

}
