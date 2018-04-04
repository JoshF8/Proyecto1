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
import java.awt.event.KeyListener;


/**
 *
 * @author Josh
 */
public class VentanaInicio extends JFrame implements ActionListener{
    
    private JTextField cuadrosTextos[] = new JTextField[2];
    
    public VentanaInicio(){
        super("Inicio");
        this.setSize(360,260);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panelTextos = new JPanel(new GridLayout(2,1, 20,20)), panelBotones = new JPanel(new GridLayout(1,2,20,20));
        JTextField nickTexto = new JTextField();
        JPasswordField contraTexto = new JPasswordField();
        TextPrompt nickPrompt = new TextPrompt("Nick name", nickTexto), contraPrompt = new TextPrompt("Password", contraTexto);
        nickPrompt.changeAlpha(0.75f);
        contraPrompt.changeAlpha(0.75f);
        cuadrosTextos[0] = nickTexto;
        cuadrosTextos[1] = (JTextField)contraTexto;
        panelTextos.add(nickTexto);
        panelTextos.add(contraTexto);
        JButton loginBoton = new JButton("Login"), aboutBoton = new JButton("About");
        loginBoton.addActionListener(this);
        aboutBoton.addActionListener(this);
        panelBotones.add(loginBoton);
        panelBotones.add(aboutBoton);
        panelTextos.setBounds(20,20,310,100);
        panelBotones.setBounds(20,140,310,50);
        getContentPane().add(panelTextos);
        getContentPane().add(panelBotones);
        this.setVisible(true);
    }
    
    @Override
    public void dispose(){
        System.exit(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Login":
                if(Logica.comprobarLlenado(cuadrosTextos)){
                    if(Logica.login(cuadrosTextos[0].getText().trim(), cuadrosTextos[1].getText().trim())){
                        if(Logica.usuarioConectado.getTipo().equals("Admin")){
                            VentanaAdmin ventana = new VentanaAdmin();
                            Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = ventana;
                            Logica.borrarTextos(cuadrosTextos);
                            this.setVisible(false);
                        }else{
                            //Ventana cliente
                            System.out.println("a");
                        }
                    }
                }
                break;
        }
    }
    
}
