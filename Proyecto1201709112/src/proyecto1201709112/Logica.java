/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1201709112;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Josh
 */
public class Logica {
    
    static Usuario usuarios[] = new Usuario[500];
    static Usuario usuarioConectado;
    
    public static boolean login(String nick, String password){
        for(int i = 0; i < usuarios.length; i++){
            if(usuarios[i] == null){
                JOptionPane.showMessageDialog(null,"No existe un usuario con ese nombre, contacte con el administrador.","Login", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
            if(usuarios[i].getNickName().equals(nick)){
                if(usuarios[i].getContra().equals(password)){
                    usuarioConectado = usuarios[i];
                    return true;
                }else{
                    JOptionPane.showMessageDialog(null,"La contraseña es incorrecta.","Login", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }
        return false;
    }
    
    public static boolean comprobarLlenado(JTextField[] cuadrosTexto){
        for(int i = 0; i < cuadrosTexto.length; i++){
            if(cuadrosTexto[i].getText().trim().equals("")){
                return false;
            }
        }
        return true;
    }
    
    public static void borrarTextos(JTextField[] cuadrosTexto){
        for(JTextField cuadro : cuadrosTexto){
            cuadro.setText("");
        }
    }
}
