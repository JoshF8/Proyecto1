/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1201709112;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;

/**
 *
 * @author Josh
 */
public class CargaIndividual extends JFrame implements ActionListener{
    
    JTextField cuadrosTexto[] = new JTextField[11];
    TextPrompt holders[] = new TextPrompt[11];
    JComboBox<String> cuadroSeleccion = new JComboBox<>();
    private String textoLabels[] = {"Autor", "Título", "Descripción", "Palabras Clave", "Edición", "Temas", "Frecuencia actual", "Ejemplares", "Área", "Copias", "Disponibles"};
    private boolean nuevo = true;
    int index= -1;
    
    public CargaIndividual(){
        super("Carga de bibliografías");
        this.setSize(800,660);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        cuadroSeleccion.addItem("Libro");
        cuadroSeleccion.addItem("Revista");
        cuadroSeleccion.addItem("Tesis");
        cuadroSeleccion.setActionCommand("CambioTipo");
        cuadroSeleccion.setSelectedIndex(0);
        cuadroSeleccion.addActionListener(this);
        JPanel panelTextos = new JPanel(new GridLayout(6, 2, 20, 40)), panelBotones = new JPanel(new GridLayout(1, 2, 20, 20));
        for(int i = 0; i < 11; i++){
            cuadrosTexto[i] = new JTextField();
            holders[i] = new TextPrompt(textoLabels[i], cuadrosTexto[i]);
            holders[i].changeAlpha(0.8f);
            panelTextos.add(cuadrosTexto[i]);
        }
        cambiarTipo(0);
        JButton guardarBoton = new JButton("Guardar"), salirBoton = new JButton("Salir");
        guardarBoton.addActionListener(this);
        salirBoton.addActionListener(this);
        panelBotones.add(guardarBoton);
        panelBotones.add(salirBoton);
        cuadroSeleccion.setBounds(20,20,390,30);
        panelTextos.setBounds(20,80,750,400);
        panelBotones.setBounds(245,550, 300, 40);
        getContentPane().add(cuadroSeleccion);
        getContentPane().add(panelTextos);
        getContentPane().add(panelBotones);
        this.setVisible(true);
    }
    
    public void llenarTextos(int index){
        Bibliografia bibliografia = Logica.bibliografias[index];
        cuadroSeleccion.setSelectedIndex(bibliografia.getTipo());
        cuadrosTexto[0].setText(bibliografia.getAutor());
        cuadrosTexto[1].setText(bibliografia.getTitulo());
        cuadrosTexto[2].setText(bibliografia.getDescripcion());
        cuadrosTexto[3].setText(bibliografia.getPalabrasClaveTexto());
        cuadrosTexto[4].setText(String.valueOf(bibliografia.getEdicion()));
        cuadrosTexto[5].setText(bibliografia.getTemasTexto());
        cuadrosTexto[6].setText(bibliografia.getFrecuenciaActual());
        cuadrosTexto[7].setText(String.valueOf(bibliografia.getEjemplares()));
        cuadrosTexto[8].setText(bibliografia.getArea());
        cuadrosTexto[9].setText(String.valueOf(bibliografia.getCopias()));
        cuadrosTexto[10].setText(String.valueOf(bibliografia.getDisponibles()));
        nuevo = false;
        this.index = index;
    }
    
    private void cambiarTipo(int tipo){
        switch(tipo){
            case 0:
                cuadrosTexto[6].setText("");
                cuadrosTexto[6].setEnabled(false);
                cuadrosTexto[7].setText("");
                cuadrosTexto[7].setEnabled(false);
                cuadrosTexto[8].setText("");
                cuadrosTexto[8].setEnabled(false);
                break;
            case 1:
                cuadrosTexto[6].setEnabled(true);
                cuadrosTexto[7].setEnabled(true);
                cuadrosTexto[8].setText("");
                cuadrosTexto[8].setEnabled(false);
                break;
            case 2:
                cuadrosTexto[6].setText("");
                cuadrosTexto[6].setEnabled(false);
                cuadrosTexto[7].setText("");
                cuadrosTexto[7].setEnabled(false);
                cuadrosTexto[8].setEnabled(true);
                break;
        }
        getContentPane().validate();
        getContentPane().repaint();
    }
    
    private boolean comprobarErrores(){
        try {
            Integer.parseInt(cuadrosTexto[4].getText().trim());
            Integer.parseInt(cuadrosTexto[9].getText().trim());
            Integer.parseInt(cuadrosTexto[10].getText().trim());
            if(cuadroSeleccion.getSelectedIndex() == 1){
                Integer.parseInt(cuadrosTexto[7].getText().trim());
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Está poniendo texto donde deben ir números.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    private void Nuevo(){
        switch(cuadroSeleccion.getSelectedIndex()){
            case 0:
            case 2:
                cuadrosTexto[7].setText("0");
                break;
        }
        Bibliografia bibliografia = new Bibliografia(cuadroSeleccion.getSelectedIndex(), cuadrosTexto[0].getText().trim(), cuadrosTexto[1].getText().trim(), cuadrosTexto[2].getText().trim(), cuadrosTexto[3].getText().trim().split(","), Integer.valueOf(cuadrosTexto[4].getText().trim()), cuadrosTexto[5].getText().trim().split(","), cuadrosTexto[6].getText().trim(), Integer.valueOf(cuadrosTexto[7].getText().trim()), cuadrosTexto[8].getText().trim(), Integer.valueOf(cuadrosTexto[9].getText().trim()), Integer.valueOf(cuadrosTexto[10].getText().trim()));
        Logica.bibliografias[Logica.buscarUltimoIndex(Logica.bibliografias)] = bibliografia;
        JOptionPane.showMessageDialog(this, "Bibliografía guardada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        dispose();
    }
    
    private void Editar(){
        Logica.bibliografias[index].editarDatos(cuadroSeleccion.getSelectedIndex(), cuadrosTexto[0].getText().trim(), cuadrosTexto[1].getText().trim(), cuadrosTexto[2].getText().trim(), cuadrosTexto[3].getText().trim().split(","), Integer.valueOf(cuadrosTexto[4].getText().trim()), cuadrosTexto[5].getText().trim().split(","), cuadrosTexto[6].getText().trim(), Integer.valueOf(cuadrosTexto[7].getText().trim()), cuadrosTexto[8].getText().trim(), Integer.valueOf(cuadrosTexto[9].getText().trim()), Integer.valueOf(cuadrosTexto[10].getText().trim()));
        JOptionPane.showMessageDialog(this, "Bibliografía editada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        Logica.borrarTextos(cuadrosTexto);
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
            case "Guardar":
                JTextField cuadrosNuevos[] = new JTextField[0];
                switch(cuadroSeleccion.getSelectedIndex()){
                    case 0:
                        cuadrosNuevos = new JTextField[8];
                        cuadrosNuevos[0] = cuadrosTexto[0];
                        cuadrosNuevos[1] = cuadrosTexto[1];
                        cuadrosNuevos[2] = cuadrosTexto[2];
                        cuadrosNuevos[3] = cuadrosTexto[3];
                        cuadrosNuevos[4] = cuadrosTexto[4];
                        cuadrosNuevos[5] = cuadrosTexto[5];
                        cuadrosNuevos[6] = cuadrosTexto[9];
                        cuadrosNuevos[7] = cuadrosTexto[10];
                        break;
                    case 1:
                        cuadrosNuevos = new JTextField[10];
                        cuadrosNuevos[0] = cuadrosTexto[0];
                        cuadrosNuevos[1] = cuadrosTexto[1];
                        cuadrosNuevos[2] = cuadrosTexto[2];
                        cuadrosNuevos[3] = cuadrosTexto[3];
                        cuadrosNuevos[4] = cuadrosTexto[4];
                        cuadrosNuevos[5] = cuadrosTexto[5];
                        cuadrosNuevos[6] = cuadrosTexto[6];
                        cuadrosNuevos[7] = cuadrosTexto[7];
                        cuadrosNuevos[8] = cuadrosTexto[9];
                        cuadrosNuevos[9] = cuadrosTexto[10];
                        break;
                    case 2:
                        cuadrosNuevos = new JTextField[9];
                        cuadrosNuevos[0] = cuadrosTexto[0];
                        cuadrosNuevos[1] = cuadrosTexto[1];
                        cuadrosNuevos[2] = cuadrosTexto[2];
                        cuadrosNuevos[3] = cuadrosTexto[3];
                        cuadrosNuevos[4] = cuadrosTexto[4];
                        cuadrosNuevos[5] = cuadrosTexto[5];
                        cuadrosNuevos[6] = cuadrosTexto[8];
                        cuadrosNuevos[7] = cuadrosTexto[9];
                        cuadrosNuevos[8] = cuadrosTexto[10];
                        break;
                }
                if(Logica.comprobarLlenado(cuadrosNuevos) &&  comprobarErrores()){
                    if(nuevo){
                        Nuevo();
                    }else{
                        Editar();
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "No ha ingresado los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Salir":
                dispose();
                break;
            case "CambioTipo":
                cambiarTipo(cuadroSeleccion.getSelectedIndex());
                break;
        }
    }
    
}
