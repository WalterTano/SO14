/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

public class TimerProceso {

    private static final int TICK_PROCESO = 100;

    private Proceso proceso;
    private Thread hilo;

    public TimerProceso(Proceso proceso) {
        this.hilo = new Thread(() -> {
            while (this.proceso.getEstado() == Proceso.Estado.EN_EJECUCION
                    && this.proceso.getEstado() == Proceso.Estado.BLOQUEADO) {
                try {
                    Thread.sleep(TICK_PROCESO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.envejecimiento();
            }
        });
    }
    
    public void iniciar() {
        this.hilo.start();
    }

    private void envejecimiento() {
        this.proceso.incrementarEdad();
        if (this.proceso.getEdad() == Proceso.EDAD_ENVEJECIMIENTO) {
            this.proceso.envejecer();
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

    public Thread getHilo() {
        return hilo;
    }
}
