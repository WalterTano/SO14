/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

public class Proceso {

    public static final int PRIORIDAD_MINIMA = 99;
    public static final int PRIORIDAD_MAXIMA = 0;
    public static final int PRIORIDAD_SO = 15;

    private long pID;
    private short edad;
    private double tiempoTotalEnCPU;
    private double tiempoRestanteEnCPU;
    private double periodoES;
    private double periodoESRestante;
    private double esperaES;
    private double esperaESRestante;
    private short prioridad;
    private Planificador planificador;
    private Estado estado;
    private Tipo tipo;
    private TimerEjecucion timerDeEjecucion;
    private TimerEnvejecimiento timerDeEnvejecimiento;

    public enum Estado {
        LISTO,
        EN_EJECUCION,
        BLOQUEADO,
        FINALIZADO
    }

    public enum Tipo {
        KERNEL,
        USUARIO
    }

    public Proceso(long pID, double tiempoTotalEnCPU, double periodoES, double esperaES, short prioridad, Tipo tipo, Planificador planificador) {
        this.pID = pID;
        this.tiempoTotalEnCPU = tiempoTotalEnCPU;
        this.tiempoRestanteEnCPU = tiempoTotalEnCPU;
        this.periodoES = periodoES;
        this.periodoESRestante = periodoES;
        this.esperaES = esperaES;
        this.esperaESRestante = esperaES;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.edad = 0;
        this.estado = Estado.LISTO;
        this.planificador = planificador;
    }

    public Proceso(double tiempoTotalEnCPU, double periodoES, double esperaES, short prioridad, Tipo tipo, Planificador planif) {
        this.tiempoTotalEnCPU = tiempoTotalEnCPU;
        this.tiempoRestanteEnCPU = tiempoTotalEnCPU;
        this.periodoES = periodoES;
        this.periodoESRestante = periodoES;
        this.esperaES = esperaES;
        this.esperaESRestante = esperaES;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.edad = 0;
        this.estado = Estado.LISTO;
        this.planificador = planif;
    }

    @Override
    public boolean equals(Object proc) {
        return this.pID == ((Proceso) proc).getPID();
    }

    public long getPID() {
        return this.pID;
    }

    public short getEdad() {
        return edad;
    }

    public double getTiempoTotalEnCPU() {
        return tiempoTotalEnCPU;
    }

    public double getTiempoRestanteEnCPU() {
        return tiempoRestanteEnCPU;
    }

    public double getPeriodoES() {
        return periodoES;
    }

    public double getPeriodoESRestante() {
        return periodoESRestante;
    }

    public double getEsperaES() {
        return esperaES;
    }

    public double getEsperaESRestante() {
        return esperaESRestante;
    }

    public short getPrioridad() {
        return prioridad;
    }

    public Estado getEstado() {
        return estado;
    }

    protected void setPID(long pID) {
        this.pID = pID;
    }

    public void incrementarEdad() {
        this.edad++;
    }

    public void setTiempoRestanteEnCPU(double tiempoRestanteEnCPU) {
        this.tiempoRestanteEnCPU = tiempoRestanteEnCPU;
    }

    public void setPeriodoESRestante(double periodoESRestante) {
        this.periodoESRestante = periodoESRestante;
    }

    public void setEsperaESRestante(double esperaESRestante) {
        this.esperaESRestante = esperaESRestante;
    }

    public void setPrioridad(short prioridad) {
        if (prioridad >= Proceso.PRIORIDAD_MAXIMA
                && prioridad <= Proceso.PRIORIDAD_MINIMA) {
            this.prioridad = prioridad;
            return;
        }
        throw new IllegalArgumentException("La prioridad debe ser menor o igual que"
                + " " + Proceso.PRIORIDAD_MINIMA + " y mayor o igual que "
                + Proceso.PRIORIDAD_MAXIMA);
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    public void iniciarEnvejecimiento() {
        if (this.timerDeEnvejecimiento == null) {
            this.timerDeEnvejecimiento = new TimerEnvejecimiento(this);
        }
        this.edad = 0;
        this.timerDeEnvejecimiento.iniciar();
    }

    public void envejecer() {
        if (this.prioridad > 0) {
            if (this.tipo == Tipo.USUARIO && this.prioridad > Proceso.PRIORIDAD_SO) {
                this.prioridad--;
                this.edad = 0;
                this.planificador.setPrioridadProceso(this, this.prioridad);
            } else if (this.tipo == Tipo.KERNEL) {
                this.prioridad--;
                this.edad = 0;
                this.planificador.setPrioridadProceso(this, this.prioridad);
            }
        }
    }
    
    public void ejecutar() {
        if (this.timerDeEjecucion == null) {
            this.timerDeEjecucion = new TimerEjecucion(this);
        }
        this.estado = Estado.EN_EJECUCION;
        this.timerDeEjecucion.iniciar();
    }

    public void bloquear() {
        this.estado = Estado.BLOQUEADO;
        this.periodoESRestante = this.periodoES;
        this.planificador.bloquearProceso(this);
    }
    
    public void suspender() {
        this.estado = Estado.LISTO;
        this.iniciarEnvejecimiento();
    }
    
    public void desbloquear() {
        this.estado = Estado.LISTO;
        this.esperaESRestante = this.esperaES;
        this.planificador.desbloquearProceso(this.pID);
        this.iniciarEnvejecimiento();
    }
    
    public void finalizar() {
        this.estado = Estado.FINALIZADO;
        this.planificador.finalizarProceso(this);
    }
}
