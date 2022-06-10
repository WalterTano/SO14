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
    public static final int PRIORIDAD_SO = 15;
    public static final int EDAD_ENVEJECIMIENTO = 5;
    public static final int ESPERA_ENVEJECIMIENTO = 1000;
    
    private long pID;
    private short edad;
    private Planificador planificador;
    private double tiempoTotalEnCPU;
    private double periodoES;
    private double esperaES;
    private double esperaESRestante;
    private short prioridad;
    private Estado estado;
    private Tipo tipo;
    
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

    public Proceso(long pID, double tiempoTotalEnCPU, double periodoES, double esperaES, short prioridad, Tipo tipo, Planificador planif) {
        this.pID = pID;
        this.tiempoTotalEnCPU = tiempoTotalEnCPU;
        this.periodoES = periodoES;
        this.esperaES = esperaES;
        this.esperaESRestante = esperaES;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.edad = 0;
        this.estado = Estado.LISTO;
        this.planificador = planif;
        this.iniciarEnvejecimiento();
    }

    public Proceso(double tiempoTotalEnCPU, double periodoES, double esperaES, short prioridad, Tipo tipo, Planificador planif) {
        this.tiempoTotalEnCPU = tiempoTotalEnCPU;
        this.periodoES = periodoES;
        this.esperaES = esperaES;
        this.esperaESRestante = esperaES;
        this.prioridad = prioridad;
        this.tipo = tipo;
        this.edad = 0;
        this.estado = Estado.LISTO;
        this.planificador = planif;
        this.iniciarEnvejecimiento();
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

    protected void setPID(long pID) {
        this.pID = pID;
    }

    public void setEdad(short edad) {
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

    private void iniciarEnvejecimiento() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(ESPERA_ENVEJECIMIENTO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.edad++;
                if (this.edad == EDAD_ENVEJECIMIENTO) {
                    this.envejecer();
                    this.edad = 0;
                }
            }
        }).start();
    }

    private void envejecer() {
        if (this.prioridad > 0) {
            if (this.tipo == Tipo.USUARIO && this.prioridad > Proceso.PRIORIDAD_SO) {
                this.prioridad--;
                this.planificador.setPrioridadProceso(this, this.prioridad);
            } else if(this.tipo == Tipo.KERNEL) {
                this.prioridad--;
                this.planificador.setPrioridadProceso(this, this.prioridad);
            }
        }
        System.out.println(this.prioridad);
        System.out.println(this.pID);
    }
}
