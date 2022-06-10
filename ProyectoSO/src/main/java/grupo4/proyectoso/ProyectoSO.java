/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package grupo4.proyectoso;

/**
 *
 * @author TanoW
 */
public class ProyectoSO {

    public static void main(String[] args) throws InterruptedException {
        double tiempoTotalEnCPU = 30000.0;
        double periodoES = 30000.0;
        double esperaES = 30000.0;
        short prioridad = 50;
        short poronga = 4;
        Planificador planif = new Planificador(poronga, 500.0);
        planif.insertarProceso(tiempoTotalEnCPU, periodoES, esperaES, prioridad, Proceso.Tipo.USUARIO);
        Thread.sleep(500);
        planif.insertarProceso(tiempoTotalEnCPU, periodoES, esperaES, prioridad, Proceso.Tipo.USUARIO);
        Thread.sleep(500);
        planif.insertarProceso(tiempoTotalEnCPU, periodoES, esperaES, prioridad, Proceso.Tipo.USUARIO);
        Thread.sleep(500);
        planif.insertarProceso(tiempoTotalEnCPU, periodoES, esperaES, prioridad, Proceso.Tipo.USUARIO);
    }
}
