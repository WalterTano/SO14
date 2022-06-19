/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package grupo4.proyectoso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class ProyectoSO {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Integer> coso = new ArrayList<Integer>();
        coso.add(1);
        System.out.println(coso instanceof List<Integer>);
        System.out.println(coso instanceof ArrayList<Integer>);
        System.out.println(coso instanceof Collection<Integer>);  
    }
}
