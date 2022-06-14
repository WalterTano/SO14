/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package grupo4.proyectoso;


public class ProyectoSO {

    public static void main(String[] args) throws InterruptedException {
        double tiempoTotalEnCPU = 5000.0;
        double periodoES = 2000.0;
        double esperaES = 1500.0;
        short prioridad = 50;
        short cantProcesadores = 4;
        Planificador planif = new Planificador(cantProcesadores, 1000.0);
        for (int i = 0; i < 500; i++) {
            planif.insertarProceso(tiempoTotalEnCPU, periodoES, esperaES, prioridad, Proceso.Tipo.USUARIO);
            if (i % 5 == 0){
                planif.insertarProceso(tiempoTotalEnCPU, periodoES, esperaES, prioridad, Proceso.Tipo.KERNEL);
            }
        }
    }
}
