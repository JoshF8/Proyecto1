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
public class CuadroTextoBibliografia extends JFrame implements ActionListener{
    JTextField cuadrosTexto[] = new JTextField[1];
    public CuadroTextoBibliografia(String tipo){
        super(tipo + " bibliografía");
        this.setSize(360,200);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        cuadrosTexto[0] = new JTextField();
        TextPrompt holder = new TextPrompt("ID bibliografía", cuadrosTexto[0]);
        holder.changeAlpha(0.8f);
        JButton buscarBoton = new JButton(tipo);
        buscarBoton.addActionListener(this);
        cuadrosTexto[0].setBounds(20,20,310,40);
        buscarBoton.setBounds(130,80,100,40);
        getContentPane().add(cuadrosTexto[0]);
        getContentPane().add(buscarBoton);
        this.setVisible(true);
    }
    
    private int existeBibliografia(){
        for(int i = 0; i < Logica.buscarUltimoIndex(Logica.bibliografias); i++){
            if(Integer.valueOf(cuadrosTexto[0].getText().trim()) == Logica.bibliografias[i].getID()){
                return i;
            }
        }
        return -1;
    }
    
    private void eliminarBibliografia(){
        int valor = 0;
        for(int i = 0; i < Logica.buscarUltimoIndex(Logica.bibliografias) + 1; i++){
            if(i != existeBibliografia()){
                Logica.bibliografias[i] = Logica.bibliografias[i - valor];
            }else{
                valor = 1;
            }
        }
        JOptionPane.showMessageDialog(this, "Bibliografía eliminada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }
    
    @Override
    public void dispose(){
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 2].setVisible(true);
        Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas) - 1] = null;
        super.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(Logica.comprobarLlenado(cuadrosTexto)){
            try {
                Integer.parseInt(cuadrosTexto[0].getText().trim());
                if(existeBibliografia()!= -1){
                    switch(e.getActionCommand()){
                        case "Eliminar":
                            if(Logica.comprobarMensaje("¿Desea eliminar esta bibliografía?", "")){
                                eliminarBibliografia();
                            }
                            break;
                        case "Modificar":
                            CargaIndividual ventana = new CargaIndividual();
                            Logica.ventanas[Logica.buscarUltimoIndex(Logica.ventanas)] = ventana;
                            ventana.llenarTextos(existeBibliografia());
                            this.setVisible(false);
                            break;
                    }
                    Logica.borrarTextos(cuadrosTexto);
                }else{
                    JOptionPane.showMessageDialog(this, "La bibliografía no existe existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(this, "Tiene que llenar el cuadro de texto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
