/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1201709112;
import java.util.GregorianCalendar;
/**
 *
 * @author Josh
 */

   public class Prestamo {
        private static int Contador = 0;
        private int ID;
        private GregorianCalendar fechaPrestamo, fechaLimite;
        private Bibliografia bibliografia;
        private Usuario usuario;

        public Prestamo(Bibliografia bibliografia){
            this.bibliografia = bibliografia;
            this.usuario = Logica.usuarioConectado;
            this.ID = Contador++;
            this.fechaPrestamo = new GregorianCalendar();
            this.fechaLimite = new GregorianCalendar();
            this.fechaLimite.add(GregorianCalendar.DATE, 7);
        }

        public int getID(){
            return ID;
        }

        public String getAutor(){
            return bibliografia.getAutor();
        }

        public String getTitulo(){
            return bibliografia.getTitulo();
        }

        public String getNombre(){
            return usuario.getNombre();
        }

        public String getApellido(){
            return usuario.getApellido();
        }

        public String getFechaPrestamo(){
            String fecha = fechaPrestamo.getTime().getDate() + "/" + (fechaPrestamo.getTime().getMonth() + 1) + "/" + (fechaPrestamo.getTime().getYear() + 1900);
            return fecha;
        }
        public String getFechaLimite(){
            String fecha = fechaLimite.getTime().getDate() + "/" + (fechaLimite.getTime().getMonth() + 1) + "/" + (fechaLimite.getTime().getYear() + 1900);
            return fecha;
        }

        public String getHoraPrestamo(){
            String cero = "";
            if(!(fechaPrestamo.getTime().getMinutes() > 9)){
                cero = "0";
            }
            String hora = fechaPrestamo.getTime().getHours() + ":" + cero + fechaPrestamo.getTime().getMinutes();
            return hora;
        }

        public GregorianCalendar getFechaPrestamoCalendar(){
            return fechaPrestamo;
        }

        public GregorianCalendar getFechaLimiteCalendar(){
            return fechaLimite;
        }

        public Bibliografia getBibliografia(){
            return this.bibliografia;
        }
    }
