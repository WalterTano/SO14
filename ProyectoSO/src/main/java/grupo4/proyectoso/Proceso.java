/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

/**
 *
 * @author TanoW
 */
public class Proceso {
    
    public static final int PRIORIDAD_MINIMA = 99;
    public static final int PRIORIDAD_MAXIMA = 0;

    
    private long pID;
    private int edad;
    private Planificador planificador;
    private double tiempoTotalEnCPU;
    private double periodoES;
    private double esperaES;
    private double esperaESRestante;
    private short prioridad;
    private Estado estado;
    
    public enum Estado {
        LISTO,
        EN_EJECUCION,
        BLOQUEADO,
        FINALIZADO
    }

    public Proceso(long pID, double tiempoTotalEnCPU, double periodoES, double esperaES, short prioridad) {
        this.pID = pID;
        this.tiempoTotalEnCPU = tiempoTotalEnCPU;
        this.periodoES = periodoES;
        this.esperaES = esperaES;
        this.prioridad = prioridad;
        this.edad = 0;
        this.esperaESRestante = esperaES;
        this.estado = Estado.LISTO;
    }
    
    public Proceso(int pID) {
        this.pID = pID;
    }
    
    @Override
    public boolean equals(Object proc) {
        return this.pID == ((Proceso) proc).getpID();
    }

    public long getpID() {
        return this.pID;
    }

    public int getEdad() {
        return edad;
    }

    public double getTiempoTotalEnCPU() {
        return tiempoTotalEnCPU;
    }

    public double getPeriodoES() {
        return periodoES;
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

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEsperaES(double esperaES) {
        this.esperaES = esperaES;
    }

    public void setEsperaESRestante(double esperaESRestante) {
        this.esperaESRestante = esperaESRestante;
    }

    public void setPrioridad(short prioridad) {
        if (prioridad >= Proceso.PRIORIDAD_MAXIMA &&
                prioridad <= Proceso.PRIORIDAD_MINIMA) {
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
    
}
