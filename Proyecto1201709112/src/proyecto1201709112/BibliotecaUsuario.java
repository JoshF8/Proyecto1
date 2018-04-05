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
public class BibliotecaUsuario extends JFrame implements ActionListener{

    JTextField cuadrosTexto[] = new JTextField[1];
    private JTable tabla;
    private TableRowSorter<TableModel> Sorter;
    JComboBox<String> cuadroSeleccion = new JComboBox<String>();
    private int columnaOrdenar = 11, item = 0;
    
    
    public BibliotecaUsuario(){
        super("Mis favoritos");
        this.setSize(1300,400);
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
        Sorter.toggleSortOrder(11);
        Sorter.toggleSortOrder(11);
        JScrollPane tablaPanel = new JScrollPane();
        tablaPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tablaPanel.setViewportView(tabla);
        tablaPanel.setBounds(20,110, 1250,220);
        JButton buscarBoton = new JButton("Buscar"), prestamosBoton = new JButton("Mis préstamos");
        cuadrosTexto[0] = new JTextField();
        TextPrompt holder0 = new TextPrompt("Palabras clave", cuadrosTexto[0]);
        holder0.changeAlpha(0.8f);
        buscarBoton.addActionListener(this);
        prestamosBoton.addActionListener(this);
        buscarBoton.setBounds(640, 30, 150, 30);
        cuadrosTexto[0].setBounds(350,30,200,30);
        prestamosBoton.setBounds(1100,30,150,30);
        getContentPane().add(tablaPanel);
        getContentPane().add(buscarBoton);
        getContentPane().add(cuadrosTexto[0]);
        columnaOrdenar = 11;
        cuadroSeleccion.addItem("Copias (Mayor a menor)");
        cuadroSeleccion.addItem("Disponibles (Mayor a menor)");
        cuadroSeleccion.addItem("Tipo (Ascendente)");
        cuadroSeleccion.addItem("Tipo (Descendente)");
        cuadroSeleccion.setActionCommand("CambioOrdenMostrarBibliografía");
        cuadroSeleccion.addActionListener(this);
        cuadroSeleccion.setBounds(100, 30, 200, 30);
        JLabel texto = new JLabel("Ordenar por:");
        texto.setBounds(20, 30, 200,30);
        getContentPane().add(cuadroSeleccion);
        getContentPane().add(texto);
        getContentPane().add(prestamosBoton);
        this.setVisible(true);
    }
    
    public void llenarTabla(){
    String columnas[] = {"ID","Tipo", "Autor", "Título", "Descripción", "Palabras Clave", "Edición", "Temas", "Frecuencia actual", "Ejemplares", "Área", "Copias", "Disponibles", "Agregar"};
        Bibliografia bibliografias[] = Logica.usuarioConectado.favoritos;
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
            String texto = "Prestar";
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
        tabla = new JTable(modeloTabla);
        tabla.setDefaultRenderer(Object.class, render);
        tabla.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int fila = tabla.rowAtPoint(e.getPoint());
                int columna = tabla.columnAtPoint(e.getPoint());
                if(modeloTabla.getColumnClass(columna).equals(JButton.class)){
                    JButton boton = (JButton)modeloTabla.getValueAt(fila, 13);
                    Number numero = (Number)tabla.getValueAt(fila, 0);
                    accion(numero.intValue(), fila);
                }
            }
        });
    }
    
    private void accion(int ID, int fila){
        Bibliografia bibliografia = null;
        for(Bibliografia bibliografia1 : Logica.bibliografias){
            if(bibliografia1 == null){
                break;
            }
            if(bibliografia1.getID() == ID){
                bibliografia = bibliografia1;
            }
        }
        if(bibliografia.getDisponibles() > 0){
            bibliografia.prestar();
            tabla.setValueAt(Integer.valueOf(tabla.getValueAt(fila, 12).toString()) - 1, fila, 12);
        }
    }
    
    private void ordenarMostrarBiblio(){
        if(cuadroSeleccion.getSelectedIndex() != item){
            switch(cuadroSeleccion.getSelectedIndex()){
                case 0:
                    tabla.getRowSorter().toggleSortOrder(11);
                    tabla.getRowSorter().toggleSortOrder(11);
                    break;
                case 1:
                    tabla.getRowSorter().toggleSortOrder(12);
                    tabla.getRowSorter().toggleSortOrder(12);
                    break;
                case 2:
                    tabla.getRowSorter().toggleSortOrder(1);
                    break;
                case 3:
                    if(item != 2){
                        tabla.getRowSorter().toggleSortOrder(1);
                    }
                    tabla.getRowSorter().toggleSortOrder(1);
                    break;
            }
            item = cuadroSeleccion.getSelectedIndex();
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
            case "CambioOrdenMostrarBibliografía":
                ordenarMostrarBiblio();
                break;
            case "Buscar":
                RowFilter filtrador = new RowFilter() {
                    @Override
                    public boolean include(RowFilter.Entry entry) {
                        String palabrasClaveBuscar[] = cuadrosTexto[0].getText().split(","), palabrasClave[] = entry.getStringValue(5).split(",");
                        boolean palabraClaveExistente = false;
                        if(!cuadrosTexto[0].getText().trim().equals("")){
                            for(int i = 0; i < palabrasClaveBuscar.length; i++){
                                for(int j = 0; j < palabrasClave.length; j++){
                                    if(palabrasClave[j].trim().toUpperCase().contains(palabrasClaveBuscar[i].trim().toUpperCase())){
                                        palabraClaveExistente = true;
                                    }
                                }
                            }
                        }else{
                            palabraClaveExistente = true;
                        }
                        return palabraClaveExistente;
                    }
                    
                };
                Sorter.setRowFilter(filtrador);
                break;
            case "Mis préstamos":
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = new PrestamosUsuario();
                this.setVisible(false);
                break;
            case "Salir":
                dispose();
                break;
        }
    }
    
}
