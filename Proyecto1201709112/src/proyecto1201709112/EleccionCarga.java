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
public class EleccionCarga extends JFrame implements ActionListener {
    
    public EleccionCarga(){
        super("Carga de bibliografías");
        this.setSize(360,160);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 20, 20));
        JButton aceptarBoton = new JButton("Individual"), salirBoton = new JButton("Masiva");
        aceptarBoton.addActionListener(this);
        salirBoton.addActionListener(this);
        panelBotones.add(aceptarBoton);
        panelBotones.add(salirBoton);
        panelBotones.setBounds(20,50, 300,50);
        getContentPane().add(panelBotones);
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
        switch(e.getActionCommand()){
            case "Individual":
                CargaIndividual cargaIndividual = new CargaIndividual();
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = cargaIndividual;
                this.setVisible(false);
                break;
            case "Masiva":
                CargaMasiva cargaMasiva = new CargaMasiva();
                Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = cargaMasiva;
                this.setVisible(false);
                break;
        }
    }
    
}
