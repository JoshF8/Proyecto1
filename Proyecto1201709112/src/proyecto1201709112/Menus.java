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
public class Menus extends JFrame implements ActionListener{
    
    public Menus(String tipoMenu){
        super("Menú " + tipoMenu);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);
        JButton botones[] = new JButton[4];
        String textosBotones[] = {"Crear", "Modificar", "Eliminar", "Mostrar"};
        JPanel botonesPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        for(int i = 0; i < 4; i++){
            botones[i] = new JButton(textosBotones[i]);
            botones[i].setActionCommand(textosBotones[i] + tipoMenu);
            botones[i].addActionListener(this);
            botonesPanel.add(botones[i]);
        }
        botonesPanel.setBounds(20,20,340,170);
        getContentPane().add(botonesPanel);
        this.setVisible(true);
    }
    
    @Override
    public void dispose(){
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 2].setVisible(true);
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 1] = null;
        super.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CuadroTextoUsuario ventanaCuadroTextoUsuario;
        switch(e.getActionCommand()){
            case "CrearUsuarios":
                FormularioUsuario ventana = new FormularioUsuario();
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = ventana;
                this.setVisible(false);
                break;
            case "ModificarUsuarios":
                ventanaCuadroTextoUsuario = new CuadroTextoUsuario("Modificar");
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = ventanaCuadroTextoUsuario;
                this.setVisible(false);
                break;
            case "EliminarUsuarios":
                ventanaCuadroTextoUsuario = new CuadroTextoUsuario("Eliminar");
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = ventanaCuadroTextoUsuario;
                this.setVisible(false);
                break;
            case "MostrarUsuarios":
                TablaUsuarios tabla = new TablaUsuarios();
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = tabla;
                this.setVisible(false);
                break;
        }
    }
}
