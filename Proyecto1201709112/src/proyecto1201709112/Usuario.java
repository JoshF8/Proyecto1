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
public class Usuario {
    String ID, Nombre, NickName, Contra, Rol, Apellido, Tipo;
    
    public Usuario(String id, String nombre, String nickName, String contra){
        this.ID = id;
        this.Nombre = nombre;
        this.NickName = nickName;
        this.Contra = contra;
        this.Tipo = "Admin";
    }
    
    public Usuario(String id, String nombre, String nickName, String contra, String rol, String apellido){
        this.ID = id;
        this.Nombre = nombre;
        this.NickName = nickName;
        this.Contra = contra;
        this.Rol = rol;
        this.Apellido = apellido;
        this.Tipo = "Usuario";
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setNickName(String NickName) {
        this.NickName = NickName;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setContra(String Contra) {
        this.Contra = Contra;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getContra() {
        return Contra;
    }

    public String getID() {
        return ID;
    }

    public String getNickName() {
        return NickName;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getRol() {
        return Rol;
    }

    public String getTipo() {
        return Tipo;
    }
    
    
    
}
