package grupo4.proyectoso;

public class TimerProcesador {
        
    private static final int TICK_PROCESO = 100;

    private Procesador procesador;
    private double quantumRestante;
    private Thread hilo;

    public TimerProcesador(Procesador procesador) {
        this.procesador = procesador;
        this.quantumRestante = procesador.getQuantum();
        this.hilo = new Thread(() -> {
            while(true) {
                try {
                    Thread.sleep(TICK_PROCESO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (this.procesador.getProceso() != null){
                    this.quantumRestante -= TICK_PROCESO;
                    //System.out.println("Timer Procesador " + this.procesador.getId() + " <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<".toUpperCase() + this.procesador.getProceso().imprimir());
                    this.tick(this.procesador.getProceso());
                    if (this.quantumRestante <= 0) {
                        this.quantumRestante = this.procesador.getQuantum();
                        //System.out.println("============== QUANTUM ==============");
                        this.procesador.getPlanificador().suspenderProceso(this.procesador);
                    }
                }
            }
        });
    }

    private void tick(Proceso proceso) {
        double tiempoRestanteEnCPU = proceso.getTiempoRestanteEnCPU() - TICK_PROCESO;
        proceso.setTiempoRestanteEnCPU(tiempoRestanteEnCPU);

        if (tiempoRestanteEnCPU <= 0) {
            proceso.finalizar();
            this.quantumRestante = this.procesador.getQuantum();
        }

        double periodoESRestante = proceso.getPeriodoESRestante() - TICK_PROCESO;
        proceso.setPeriodoESRestante(periodoESRestante);

        if (periodoESRestante <= 0) {
            proceso.setMotivoBloqueo("E/S");
            proceso.bloquear();
            this.quantumRestante = this.procesador.getQuantum();
        }
    }

    public void ejecutar() {
        if (!this.hilo.isAlive()) {
            this.hilo.start();
        }
    }
}
