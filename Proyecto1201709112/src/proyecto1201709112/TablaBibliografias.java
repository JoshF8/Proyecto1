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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Josh
 */
public class TablaBibliografias extends JFrame implements ActionListener{
    
    private JTable tabla;
    private TableRowSorter<TableModel> Sorter;
    
    public TablaBibliografias(){
        super("Bibliografías");
        this.setSize(1000,600);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        llenarTabla(Logica.bibliografias);
        tabla.setEnabled(true);
        tabla.setRowHeight(30);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.getTableHeader().setEnabled(false);
        Sorter = new TableRowSorter<TableModel>(tabla.getModel());
        tabla.setRowSorter(Sorter);
        Sorter.toggleSortOrder(0);
        JScrollPane tablaPanel = new JScrollPane();
        tablaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tablaPanel.setViewportView(tabla);
        tablaPanel.setBounds(20,100, 960,420);
        JButton botonSalir = new JButton("Salir");
        botonSalir.addActionListener(this);
        botonSalir.setBounds(830, 30, 150, 30);
        getContentPane().add(tablaPanel);
        getContentPane().add(botonSalir);
        this.setVisible(true);
    }
    
    public void llenarTabla(Bibliografia bibliografias[]){
        String columnas[] = {"ID","Tipo", "Autor", "Título", "Descripción", "Palabras Clave", "Edición", "Temas", "Frecuencia actual", "Ejemplares", "Área", "Copias", "Disponibles"};
        Object datosTabla[][] = new Object[Logica.buscarUltimoIndex(Logica.bibliografias)][columnas.length];
        for(int i = 0; i < Logica.buscarUltimoIndex(Logica.bibliografias); i++){
            datosTabla[i][0] = bibliografias[i].getID();
            datosTabla[i][1] = bibliografias[i].getTipo();
            datosTabla[i][2] = bibliografias[i].getAutor();
            datosTabla[i][3] = bibliografias[i].getTitulo();
            datosTabla[i][4] = bibliografias[i].getDescripcion();
            datosTabla[i][5] = bibliografias[i].getPalabrasClaveTexto();
            datosTabla[i][6] = bibliografias[i].getEdicion();
            datosTabla[i][7] = bibliografias[i].getTemasTexto();
            datosTabla[i][8] = bibliografias[i].getFrecuenciaActual();
            datosTabla[i][9] = bibliografias[i].getEjemplares();
            datosTabla[i][10] = bibliografias[i].getArea();
            datosTabla[i][11] = bibliografias[i].getCopias();
            datosTabla[i][12] = bibliografias[i].getDisponibles();
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 0:
                        return Long.class;
                    case 1:
                    case 6:
                    case 9:
                    case 11:
                    case 12:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tabla = new JTable(modeloTabla);
    }
    
    @Override
    public void dispose(){
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 2].setVisible(true);
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 1] = null;
        super.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Salir")){
            dispose();
        }
    }
    
}
