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
public class VentanaAdmin extends JFrame implements ActionListener{

    public VentanaAdmin(){
        super("Administrador");
        this.setSize(520,200);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel botonesPanel = new JPanel(new GridLayout(1, 3, 20, 20));
        JButton usuarioBoton = new JButton("Menú usuarios"), bibliografiaBoton = new JButton("Menú Bibliografías"), salirBoton = new JButton("Salir");
        usuarioBoton.addActionListener(this);
        bibliografiaBoton.addActionListener(this);
        salirBoton.addActionListener(this);
        botonesPanel.add(usuarioBoton);
        botonesPanel.add(bibliografiaBoton);
        botonesPanel.add(salirBoton);
        botonesPanel.setBounds(30,50,460,50);
        getContentPane().add(botonesPanel);
        this.setVisible(true);
    }
    
    @Override
    public void dispose(){
        if(Logica.comprobarMensaje("¿Desea cerrar sesión?", "Salir")){
            Logica.ventanas[0].setVisible(true);
            Logica.ventanas[1] = null;
            super.dispose();
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Menus ventana;
        switch(e.getActionCommand()){
            case "Menú usuarios":
                ventana = new Menus("Usuarios");
                Logica.ventanas[2] = ventana;
                this.setVisible(false);
                break;
            case "Menú Bibliografías":
                ventana = new Menus("Bibliografías");
                Logica.ventanas[2] = ventana;
                this.setVisible(false);
                break;
            case "Salir":
                dispose();
                break;
        }
    }
    
}
