/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo4.proyectoso;

import java.util.List;

/**
 *
 * @author TanoW
 */
public class ProcesoTableModel extends javax.swing.table.AbstractTableModel{

    
    
    public CustomTableModel(List<Persona> personas) {
        this.personas = personas;
    }
    
    @Override
    public int getRowCount() {
        return this.personas.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }
    
    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: return "Nombre";
            case 1: return "Edad";
        }
        return null;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona p = this.personas.get(rowIndex);
        switch(columnIndex) {
            case 0: return p.getNombre();
            case 1: return p.getEdad();
        }
        
        return null;
    }
    
}
