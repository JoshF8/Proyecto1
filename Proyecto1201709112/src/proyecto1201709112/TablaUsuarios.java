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
public class TablaUsuarios extends JFrame implements ActionListener{
    private JTable tabla;
    private TableRowSorter<TableModel> elQueOrdena;
    JComboBox<String> ordenar = new JComboBox<String>();
    private int itemSeleccionado;
    public TablaUsuarios(){
        super("Usuarios");
        this.setSize(1000,600);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        llenarTabla(Logica.usuarios);
        tabla.setEnabled(true);
        tabla.setRowHeight(30);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.getTableHeader().setEnabled(false);
        elQueOrdena = new TableRowSorter<TableModel>(tabla.getModel());
        tabla.setRowSorter(elQueOrdena);
        elQueOrdena.toggleSortOrder(0);
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
    
    public void llenarTabla(Usuario usuarios[]){
    String columnas[] = {"ID","Nombre", "Apellido", "Nick", "Rol", "Password"};
        Object datosTabla[][] = new Object[Logica.buscarUltimoIndex(Logica.usuarios) - 1][columnas.length];
        Usuario user;
        for(int i = 1; i < Logica.buscarUltimoIndex(Logica.usuarios); i++){
            user = Logica.usuarios[i];
            datosTabla[i - 1][0] = user.getID();
            datosTabla[i - 1][1] = user.getNombre();
            datosTabla[i - 1][2] = user.getApellido();
            datosTabla[i - 1][3] = user.getNickName();
            datosTabla[i - 1][4] = user.getRol();
            datosTabla[i - 1][5] = user.getContra();
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }
            
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        
        
        tabla = new JTable(modeloTabla);
        ordenar.addItem("ID(Ascendente)");
        ordenar.addItem("ID(Descendente)");
        ordenar.setActionCommand("CambioOrdenMostrarUsuario");
        ordenar.addActionListener(this);
        ordenar.setBounds(100, 30, 300, 30);
        JLabel texto = new JLabel("Ordenar por:");
        texto.setBounds(20, 30, 200,30);
        getContentPane().add(ordenar);
        getContentPane().add(texto);
    }
    
    private void ordenarMostrarUsuario(){
        if(ordenar.getSelectedIndex() != itemSeleccionado){
            tabla.getRowSorter().toggleSortOrder(0);
            itemSeleccionado = ordenar.getSelectedIndex();
        }
    }
    
    
    @Override
    public void dispose(){
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 2].setVisible(true);
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 1] = null;
        super.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "CambioOrdenMostrarUsuario":
                ordenarMostrarUsuario();
                break;
            case "Salir":
                dispose();
                break;
        }
    }
    
}
