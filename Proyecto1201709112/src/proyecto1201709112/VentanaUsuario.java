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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author Josh
 */
public class VentanaUsuario extends JFrame implements ActionListener{
    JTextField cuadrosTexto[] = new JTextField[2];
    public JTable tabla = new JTable();
    private TableRowSorter<TableModel> Sorter;
    
    public VentanaUsuario(){
        super("Bibliografías");
        this.setSize(1000,400);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        llenarTabla();
        JScrollPane tablaPanel = new JScrollPane();
        tablaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tablaPanel.setViewportView(tabla);
        tablaPanel.setBounds(20,110, 960,220);
        JButton buscarBoton = new JButton("Buscar"), bibliotecaBoton = new JButton("Mi biblioteca"),salirBoton = new JButton("Salir");
        cuadrosTexto[0] = new JTextField();
        cuadrosTexto[1] = new JTextField();
        TextPrompt holder0 = new TextPrompt("Título", cuadrosTexto[0]), holder1 = new TextPrompt("Autor", cuadrosTexto[1]);
        holder0.changeAlpha(0.8f);
        holder1.changeAlpha(0.8f);
        buscarBoton.addActionListener(this);
        bibliotecaBoton.addActionListener(this);
        salirBoton.addActionListener(this);
        buscarBoton.setBounds(20, 30, 150, 30);
        cuadrosTexto[0].setBounds(200,30,200,30);
        cuadrosTexto[1].setBounds(430,30,200,30);
        bibliotecaBoton.setBounds(650,30,150,30);
        salirBoton.setBounds(830, 30, 150, 30);
        getContentPane().add(tablaPanel);
        getContentPane().add(buscarBoton);
        getContentPane().add(cuadrosTexto[0]);
        getContentPane().add(cuadrosTexto[1]);
        getContentPane().add(bibliotecaBoton);
        getContentPane().add(salirBoton);
        this.setVisible(true);
    }
    
    public void llenarTabla(){
    String columnas[] = {"ID","Tipo", "Autor", "Título", "Descripción", "Palabras Clave", "Edición", "Temas", "Frecuencia actual", "Ejemplares", "Área", "Copias", "Disponibles", "Agregar"};
        Bibliografia bibliografias[] = Logica.bibliografias;
        Object datosTabla[][] = new Object[Logica.buscarUltimoIndex(bibliografias)][columnas.length];
        JButton botones[] = new JButton[Logica.buscarUltimoIndex(bibliografias)];
        for(int i = 0; i < Logica.buscarUltimoIndex(bibliografias); i++){
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
            String texto = "+";
            for(Bibliografia favorito:Logica.usuarioConectado.favoritos){
                if(favorito == null){
                    break;
                }
                if(favorito.getID() == bibliografias[i].getID()){
                    texto = "-";
                }
            }
            botones[i] = new JButton(texto);
            datosTabla[i][13] = botones[i];
        }
        DefaultTableModel modeloTabla = new DefaultTableModel(datosTabla, columnas){
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch(columnIndex){
                    case 0:
                    case 1:
                    case 6:
                    case 9:
                    case 11:
                    case 12:
                        return Integer.class;
                    case 13:
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
        tabla.setModel(modeloTabla);
        tabla.setDefaultRenderer(Object.class, render);
        tabla.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());
                if(modeloTabla.getColumnClass(columna).equals(JButton.class)){
                    JButton boton = (JButton)modeloTabla.getValueAt(fila, 13);
                    Number numero = (Number)tabla.getValueAt(fila, 0);
                    accion(boton.getText(), numero.intValue(), fila);
                }
            }
        });
        tabla.setEnabled(true);
        tabla.setRowHeight(30);
        tabla.setRowSelectionAllowed(true);
        tabla.setColumnSelectionAllowed(false);
        tabla.getTableHeader().setEnabled(false);
        Sorter = new TableRowSorter<TableModel>(tabla.getModel());
        tabla.setRowSorter(Sorter);
        Sorter.toggleSortOrder(0);
    }
    
    private void accion(String textoBoton, int ID, int fila){
        Bibliografia bibliografia = null;
        for(Bibliografia bibliografia1 : Logica.bibliografias){
            if(bibliografia1 == null){
                break;
            }
            if(bibliografia1.getID() == ID){
                bibliografia = bibliografia1;
            }
        }
        if(bibliografia != null){
            JButton boton;
            switch(textoBoton){
                case "+":
                    boton = new JButton("-");
                    Logica.usuarioConectado.favoritos[Logica.buscarUltimoIndex(Logica.usuarioConectado.favoritos)] = bibliografia;
                    tabla.setValueAt(boton, fila, 13);
                    break;
                case "-":
                    boton = new JButton("+");
                    Logica.usuarioConectado.eliminarFavorito(ID);
                    tabla.setValueAt(boton, fila, 13);
                    break;
            }
        }
    }

    @Override
    public void dispose(){
        if(Logica.comprobarMensaje("¿Desea cerrar sesión?", "Salir")){
            Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 2].setVisible(true);
            Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 1] = null;
            super.dispose();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Buscar":
                RowFilter filtrador = new RowFilter() {
                    @Override
                    public boolean include(RowFilter.Entry entry) {
                        return (entry.getValue(3).toString().toUpperCase().contains(cuadrosTexto[0].getText().trim().toUpperCase()) && entry.getValue(2).toString().toUpperCase().contains(cuadrosTexto[1].getText().trim().toUpperCase()));
                    }
                };
                Sorter.setRowFilter(filtrador);
                break;
            case "Mi biblioteca":
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = new BibliotecaUsuario();
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 1].setVisible(true);
                this.setVisible(false);
                break;
            case "Salir":
                dispose();
                break;
        }
    }
}
