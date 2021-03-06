/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

public class Proceso implements Comparable {

    public static final int PRIORIDAD_MINIMA = 98;
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
    private String motivoBloqueo;
    private Planificador planificador;
    private Estado estado;
    private Tipo tipo;

    public enum Estado {
        LISTO,
        EN_EJECUCION,
        BLOQUEADO,
        FINALIZADO
    }

    public enum Tipo {
        SO,
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
        this.motivoBloqueo = "";
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
        this.motivoBloqueo = "";
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
    
    public String getMotivoBloqueo() {
        return this.motivoBloqueo;
    }
    
    public Tipo getTipo() {
        return this.tipo;
    }

    protected void setPID(long pID) {
        this.pID = pID;
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
    
    public void setMotivoBloqueo(String motivo) {
        this.motivoBloqueo = motivo;
    }
    
    public void incrementarEdad() {
        this.edad++;
    }

    public void envejecer() {
        if (this.prioridad > 0) {
            if (this.tipo == Tipo.USUARIO && this.prioridad > Proceso.PRIORIDAD_SO) {
                this.prioridad--;
                this.edad = 0;
                this.planificador.setPrioridadProceso(this, this.prioridad);
            } else if (this.tipo == Tipo.SO) {
                this.prioridad--;
                this.edad = 0;
                this.planificador.setPrioridadProceso(this, this.prioridad);
            }
        }
    }

    public void ejecutar() {
        this.estado = Estado.EN_EJECUCION;
    }

    public void bloquear() {
        this.estado = Estado.BLOQUEADO;
        this.periodoESRestante = this.periodoES;
        this.planificador.bloquearProceso(this);
    }

    public void desbloquear() {
        this.estado = Estado.LISTO;
        this.esperaESRestante = this.esperaES;
        this.planificador.desbloquearProceso(this.pID);
    }

    public void finalizar() {
        this.estado = Estado.FINALIZADO;
        this.planificador.finalizarProceso(this);
    }

    public String imprimir() {
        return "\nPID: " + this.getPID() + "- Info Proceso"
                + "\nPID: " + this.getPID() + "-" + " Edad: " + this.getEdad()
                + "\nPID: " + this.getPID() + "-" + " Estado: " + this.getEstado().toString()
                + "\nPID: " + this.getPID() + "-" + " Prioridad: " + this.getPrioridad()
                + "\nPID: " + this.getPID() + "-" + " Tiempo restante CPU: " + this.getTiempoRestanteEnCPU()
                + "\nPID: " + this.getPID() + "-" + " Espera restante ES: " + this.getEsperaESRestante()
                + "\nPID: " + this.getPID() + "-" + " Periodo restante hasta ES: " + this.getPeriodoESRestante();
    }
    
    @Override
    public int compareTo(Object o) {
        Proceso otroProceso = (Proceso) o;
        if (otroProceso.getEsperaESRestante() == this.esperaESRestante) {
            return 0;
        } else if (otroProceso.getEsperaESRestante() > this.esperaESRestante) {
            return 1;
        } else {
            return -1;
        }
    }
}
