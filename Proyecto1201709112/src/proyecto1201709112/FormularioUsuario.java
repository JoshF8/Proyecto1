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

/**
 *
 * @author Josh
 */
public class FormularioUsuario extends JFrame implements ActionListener{
    
    JTextField cuadrosTexto[] = new JTextField[6];
    JComboBox<String> cuadroSeleccion = new JComboBox<>();
    boolean nuevo = true;
    
    public FormularioUsuario(){
        super("Formulario usuario");
        this.setSize(400,600);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel infoPanel = new JPanel(new GridLayout(7, 1, 20, 30));
        TextPrompt holders[] = new TextPrompt[6];
        String textos[] = {"CUI", "Nombre", "Apellido", "Nick", "Contraseña", "Repite la contraseña"};
        for(int i = 0; i < 7; i++){
            if(i != 4){
                cuadrosTexto[(i<4)?i:i-1] = new JTextField();
                holders[(i<4)?i:i-1] = new TextPrompt(textos[(i<4)?i:i-1], cuadrosTexto[(i<4)?i:i-1]);
                holders[(i<4)?i:i-1].changeAlpha(0.8f);
                infoPanel.add(cuadrosTexto[(i<4)?i:i-1]);
            }else{
                cuadroSeleccion.addItem("Rol del usuario");
                cuadroSeleccion.addItem("Estudiante");
                cuadroSeleccion.addItem("Catedrático");
                infoPanel.add(cuadroSeleccion);
            }
        }
        JPanel botonesPanel = new JPanel(new GridLayout(1,2,20,30));
        JButton guardarBoton = new JButton("Guardar"), salirBoton = new JButton("Salir");
        guardarBoton.addActionListener(this);
        salirBoton.addActionListener(this);
        botonesPanel.add(guardarBoton);
        botonesPanel.add(salirBoton);
        infoPanel.setBounds(20, 20, 350, 440);
        botonesPanel.setBounds(20,500, 350, 40);
        getContentPane().add(infoPanel);
        getContentPane().add(botonesPanel);
        this.setVisible(true);
    }
    
    private void Nuevo(){
        for(int i = 0; i < Logica.buscarUltimoIndex(Logica.usuarios); i++){
            if(Logica.usuarios[i].getID().equals(cuadrosTexto[0].getText().trim()) || Logica.usuarios[i].getNickName().equals(cuadrosTexto[3].getText().trim())){
                JOptionPane.showMessageDialog(this, "Ya existe un usuario con ese CUI o Nickname.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        String rol = (cuadroSeleccion.getSelectedIndex() == 1) ? "Estudiante":"Catedrático";
        Usuario usuario = new Usuario(cuadrosTexto[0].getText().trim(), cuadrosTexto[1].getText().trim(), cuadrosTexto[3].getText().trim(), cuadrosTexto[4].getText().trim(), rol, cuadrosTexto[2].getText().trim());
        Logica.usuarios[Logica.buscarUltimoIndex(Logica.usuarios)] = usuario;
        JOptionPane.showMessageDialog(this, "Usuario ingresado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private boolean comprobarErrores(){
        if(cuadrosTexto[0].getText().trim().length() == 13 ){
            if(cuadrosTexto[4].getText().trim().equals(cuadrosTexto[5].getText().trim())){
                try {
                    Number numero = Long.valueOf(cuadrosTexto[0].getText().trim());
                    return true;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "El CUI debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(this, "Las contraseñas deben ser iguales.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "El CUI no es correcto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    
    @Override
    public void dispose(){
        Logica.ventanas[2].setVisible(true);
        Logica.ventanas[3] = null;
        super.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Guardar":
                if(Logica.comprobarLlenado(cuadrosTexto) && cuadroSeleccion.getSelectedIndex() != 0 && comprobarErrores()){
                    if(nuevo){
                        Nuevo();
                    }else{
                        
                    }
                }else{
                    JOptionPane.showMessageDialog(this, "No ha ingresado los datos correctamente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case "Salir":
                dispose();
                break;
        }
    }
    
}
