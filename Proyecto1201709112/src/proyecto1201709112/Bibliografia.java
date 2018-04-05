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
public class Bibliografia {
    private static int contador = 0;
    private int ID, Tipo, Edicion, Ejemplares, Copias, Disponibles, vecesPrestado = 0;
    private String Autor, Titulo, Descripcion, PalabrasClave[], Temas[], FrecuenciaActual, Area;
    public Bibliografia(int tipo, String autor, String titulo, String descripcion, String palabrasClave[],int edicion, String temas[], String frecuenciaActual, int ejemplares, String area, int copias, int disponibles){
        this.Tipo = tipo;
        this.Autor = autor;
        this.Titulo = titulo;
        this.Descripcion = descripcion;
        this.PalabrasClave = palabrasClave;
        this.Temas = temas;
        if(getTipo() == 1){
            this.FrecuenciaActual = frecuenciaActual;
            this.Ejemplares = ejemplares;
        }
        if(getTipo() == 2){
            this.Area = area;
        }
        this.Copias = copias;
        this.Disponibles = disponibles;
        ID = contador++;
    }
    
    public void editarDatos(int tipo, String autor, String titulo, String descripcion, String palabrasClave[],int edicion, String temas[], String frecuenciaActual, int ejemplares, String area, int copias, int disponibles){
        this.Tipo = tipo;
        this.Autor = autor;
        this.Titulo = titulo;
        this.Descripcion = descripcion;
        this.PalabrasClave = palabrasClave;
        this.Temas = temas;
        if(getTipo() == 1){
            this.FrecuenciaActual = frecuenciaActual;
            this.Ejemplares = ejemplares;
        }
        if(getTipo() == 2){
            this.Area = area;
        }
        this.Copias = copias;
        this.Disponibles = disponibles;
    }
    
    public int getTipo() {
        return Tipo;
    }

    public String getArea() {
        return Area;
    }

    public String getAutor() {
        return Autor;
    }

    public static int getContador() {
        return contador;
    }

    public int getCopias() {
        return Copias;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public int getDisponibles() {
        return Disponibles;
    }

    public int getEdicion() {
        return Edicion;
    }

    public int getEjemplares() {
        return Ejemplares;
    }

    public String getFrecuenciaActual() {
        return FrecuenciaActual;
    }

    public int getID() {
        return ID;
    }

    public String[] getPalabrasClave() {
        return PalabrasClave;
    }

    public String[] getTemas() {
        return Temas;
    }
    
    public String getTemasTexto(){
        String texto = "";
        try{
            texto = Temas[0];
            for(int i = 1; i < Temas.length; i++){
                texto += ", " + Temas[i];
            }
        }catch(Exception e){
        
        }
        return texto;
    }
    
    public String getPalabrasClaveTexto(){
        String texto = "";
        try{
            texto = PalabrasClave[0];
            for(int i = 1; i < PalabrasClave.length; i++){
                texto += ", " + PalabrasClave[i];
            }
        }catch(Exception e){
        
        }
        return texto;
    }

    public String getTitulo() {
        return Titulo;
    }

    public int getVecesPrestado() {
        return vecesPrestado;
    }
    
    public void prestar(){
        this.Disponibles--;
        Prestamo prestamo = new Prestamo(this);
        Logica.usuarioConectado.prestamos[Logica.buscarUltimoIndex(Logica.usuarioConectado.prestamos)] = prestamo;
        Logica.prestamos[Logica.buscarUltimoIndex(Logica.prestamos)] = prestamo;
    }
    
    public void devolver(Prestamo prestamo){
        this.Disponibles++;
        int valor = 0;
        for(int i = 0; i < Logica.buscarUltimoIndex(Logica.usuarioConectado.prestamos); i++){
            if(Logica.usuarioConectado.prestamos[i] == prestamo){
                valor = 1;
            }else{
                Logica.usuarioConectado.prestamos[i - valor] = Logica.usuarioConectado.prestamos[i];
            }
            
        }
        Logica.usuarioConectado.prestamos[Logica.buscarUltimoIndex(Logica.usuarioConectado.prestamos) - 1] = null;
    }
}
