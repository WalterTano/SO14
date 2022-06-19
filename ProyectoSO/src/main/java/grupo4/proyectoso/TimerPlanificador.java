/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class TimerPlanificador {

    private static final int EDAD_ENVEJECIMIENTO = 5;
    private static final int TICK_PROCESO = 1000;

    private Planificador planificador;
    private Thread hilo;

    public TimerPlanificador(Planificador planificador) {
        this.planificador = planificador;
        this.hilo = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(TICK_PROCESO);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    LinkedHashMap<Long, Proceso>[] copiaMultiNivel = this.planificador.getMultiNivelListos().getColaMultiNivel().clone();
                    for (LinkedHashMap<Long, Proceso> nivel : copiaMultiNivel) {
                        for (Proceso proceso : nivel.values()) {
                            proceso.incrementarEdad();
                            //System.out.println("Timer Envejecimiento =================================================".toUpperCase()  + proceso.imprimir());
                            if (proceso.getEdad() == EDAD_ENVEJECIMIENTO) {
                                proceso.envejecer();
                            }
                        }
                    }

                    List<Proceso> copiaBloqueados = new ArrayList<Proceso>();
                    copiaBloqueados.addAll(this.planificador.getProcesosBloqueadosPorPID().values());
                    for (Proceso proceso : copiaBloqueados) {
                        proceso.setEsperaESRestante(proceso.getEsperaESRestante() - TICK_PROCESO);
                        //System.out.println("Timer Bloqueados >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>".toUpperCase() + proceso.imprimir());
                        if (proceso.getEsperaESRestante() <= 0) {
                            proceso.desbloquear();
                        }
                    }
                } catch(Exception e) { }
            }
        });
    }

    public void iniciar() {
        if (!this.hilo.isAlive()) {
            this.hilo.start();
        }
    }
    
    
}
