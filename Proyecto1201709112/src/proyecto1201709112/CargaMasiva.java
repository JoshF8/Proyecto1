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
public class CargaMasiva extends JFrame implements ActionListener{

    JTextArea cuadroTexto = new JTextArea("", 10 , 10);
    
    public CargaMasiva(){
        super("Carga masiva");
        this.setSize(900,500);
        getContentPane().setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        TextPrompt holder = new TextPrompt("Tipo; Autor; Título; Descripción; Palabras Clave; Edición; Temas; Frecuencia Actual; Ejemplares; Área;\n" +
"Copias; Disponibles", cuadroTexto);
        holder.changeAlpha(0.8f);
        holder.setVerticalAlignment(TextPrompt.TOP);
        cuadroTexto.setLineWrap(true);
        JButton guardarBoton = new JButton("Guardar"), salirBoton = new JButton("Salir");
        JPanel panelBotones = new JPanel(new GridLayout(1, 2, 20, 20));
        guardarBoton.addActionListener(this);
        salirBoton.addActionListener(this);
        panelBotones.add(guardarBoton);
        panelBotones.add(salirBoton);
        cuadroTexto.setBounds(20,20,850,370);
        panelBotones.setBounds(550,400, 300, 30);
        getContentPane().add(cuadroTexto);
        getContentPane().add(panelBotones);
        this.setVisible(true);
    }
    
    private void comprobarGuardado(){
        String texto = cuadroTexto.getText(), textoError = "", mensaje = "";
        int contador = 1;
        String lineas[] = texto.split("\n"), campos[];
        for(String linea : lineas){
            if(linea.split(";").length%12 == 0){
                try{
                    campos = linea.split(";");
                    for(int i = 0; i < campos.length; i++){
                        campos[i] = campos[i].trim();
                    }
                    Integer.parseInt(campos[0]);
                    Integer.parseInt(campos[5]);
                    Integer.parseInt(campos[10]);
                    Integer.parseInt(campos[11]);
                    if(Integer.valueOf(campos[0]) == 1){
                        Integer.parseInt(campos[8]);
                    }
                    if(Integer.valueOf(campos[0]) > 2 || Integer.valueOf(campos[0]) < 0){
                        textoError += linea + "\n";
                        mensaje += "La línea no." + contador++ + " contiene un tipo que no existe.\n";
                        continue;
                    }
                    if(campos[1].equals("") || campos[2].equals("") || campos[3].equals("") || campos[4].equals("")|| campos[6].equals("")){
                        textoError += linea + "\n";
                        mensaje += "La línea no." + contador++ + " tiene campos necesarios vacíos.\n";
                        continue;
                    }
                    if(Integer.valueOf(campos[0]) == 0){
                        if(!(campos[7].equals("") && campos[8].equals("") && campos[9].equals(""))){
                           textoError += linea + "\n";
                           mensaje += "La línea no." + contador++ + " contiene campos de más.\n";
                           continue;
                        }
                    }
                    if(Integer.valueOf(campos[0]) == 1){
                        if(!(campos[9].equals(""))){
                            textoError += linea + "\n";
                            mensaje += "La línea no." + contador++ + " contiene campos de más.\n";
                            continue;
                        }
                        if(campos[7].equals("")|| campos[8].equals("")){
                            textoError += linea + "\n";
                            mensaje += "La línea no." + contador++ + " tiene campos necesarios vacíos.\n";
                            continue;
                        }
                    }
                    if(Integer.valueOf(campos[0]) == 2){
                        if(!(campos[7].equals("") && campos[8].equals(""))){
                            textoError += linea + "\n";
                            mensaje += "La línea no." + contador++ + " contiene campos de más.\n";
                            continue;
                        }
                        if(campos[9].equals("")){
                            textoError += linea + "\n";
                            mensaje += "La línea no." + contador++ + " tiene campos necesarios vacíos.\n";
                            continue;
                        }
                    }
                    
                    Bibliografia bibliografia = new Bibliografia(Integer.valueOf(campos[0]), campos[1], campos[2], campos[3], campos[4].split(","), Integer.valueOf(campos[5]), campos[6].split(","), campos[7], (Integer.valueOf(campos[0]) == 1)? Integer.valueOf(campos[8]):0, campos[9], Integer.valueOf(campos[10]), Integer.valueOf(campos[11]));
                    Logica.bibliografias[Logica.buscarUltimoIndex(Logica.bibliografias)] = bibliografia;
                    mensaje += "La línea no." + contador++ + " se almacenó con éxito.\n";
                    continue;
                }catch(Exception e){
                    textoError += linea + "\n";
                    mensaje += "La línea no." + contador++ + " no cumple los requisitos de los campos con número.\n";
                    continue;
                }
            }else{
                textoError += linea + "\n";
                mensaje += "La línea no." + contador++ + " no tiene los campos necesarios.\n";
                continue;
            }
        }
        if(!mensaje.equals("")){
            JOptionPane.showMessageDialog(this, mensaje, "", JOptionPane.INFORMATION_MESSAGE);
            cuadroTexto.setText(textoError);
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
            case "Guardar":
                comprobarGuardado();
                break;
            case "Salir":
                dispose();
                break;
        }
    }
    
}
