/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1201709112;

/**
 *
 * @author Josh
 */
public class Proyecto1201709112 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        Logica.usuarios[0] = new Usuario("admin", "administrador", "admin", "password");
        Logica.usuarios[1] = new Usuario("dkfj", "abcd", "abcd", "123", "Estudiante", "kdkf");
        VentanaInicio ventana = new VentanaInicio();
        Logica.ventanas[0] = ventana;
    }
    
}
