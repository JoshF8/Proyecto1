/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1201709112;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Josh
 */
public class PrestamosUsuario extends JFrame{
     private JTable tabla;
    private TableRowSorter<TableModel> Sorter;
    
    public PrestamosUsuario(){
        super("Bibliografías");
        this.setSize(1000,400);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        llenarTabla();
        tabla.setEnabled(true);
        tabla.setRowHeight(30);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.getTableHeader().setEnabled(false);
        Sorter = new TableRowSorter<TableModel>(tabla.getModel());
        tabla.setRowSorter(Sorter);
        JScrollPane tablaPanel = new JScrollPane();
        tablaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tablaPanel.setViewportView(tabla);
        tablaPanel.setBounds(20,110, 960,220);
        getContentPane().add(tablaPanel);
        this.setVisible(true);
    }
    
    public void llenarTabla(){
    String columnas[] = {"ID","Título", "Autor", "Fecha préstamo", "Hora préstamo", "Fecha límite", "Nombre usuario", "Apellido usuario","Regresar"};
        Object datosTabla[][] = new Object[Logica.buscarUltimoIndex(Logica.usuarioConectado.prestamos)][columnas.length];
        JButton botones[] = new JButton[Logica.buscarUltimoIndex(Logica.usuarioConectado.prestamos)];
        Prestamo prestamo[] = Logica.usuarioConectado.prestamos;
        for(int i = 0; i < Logica.buscarUltimoIndex(prestamo); i++){
            datosTabla[i][0] = prestamo[i].getID();
            datosTabla[i][1] = prestamo[i].getTitulo();
            datosTabla[i][2] = prestamo[i].getAutor();
            datosTabla[i][3] = prestamo[i].getFechaPrestamo();
            datosTabla[i][4] = prestamo[i].getHoraPrestamo();
            datosTabla[i][5] = prestamo[i].getFechaLimite();
            datosTabla[i][6] = prestamo[i].getNombre();
            datosTabla[i][7] = prestamo[i].getApellido();
            botones[i] = new JButton("Regresar");
            datosTabla[i][8] = botones[i];
        }
        
      DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 0:
                        return Long.class;
                    case 8:
                        return JButton.class;
                    default:
                        return String.class;
                }
            }
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        DefaultTableCellRenderer render = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                if(value instanceof JButton){
                    JButton boton = (JButton)value;
                    return boton;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        tabla = new JTable(modeloTabla);
        tabla.setDefaultRenderer(Object.class, render);
        tabla.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());
                if(modeloTabla.getColumnClass(columna).equals(JButton.class)){
                    Number numero = (Number)tabla.getValueAt(fila, 0);
                    accion(numero.intValue());
                }
            }
        });
    }
    
    private void accion(int ID){
        if(Logica.comprobarMensaje("¿Desea regresar éste título?", "")){
            for(int i = 0; i < Logica.buscarUltimoIndex(Logica.usuarioConectado.prestamos); i++){
                if(Logica.usuarioConectado.prestamos[i].getID() == ID){
                    Logica.usuarioConectado.prestamos[i].getBibliografia().devolver(Logica.usuarioConectado.prestamos[i]);
                    dispose();
                }
            }
        }
    }

    @Override
    public void dispose(){
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 2].setVisible(true);
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 1] = null;
        super.dispose();
        
    }
}
