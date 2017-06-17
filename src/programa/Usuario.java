/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

/**
 *
 * @author Matias
 */
public class Usuario {
    private String usuario;
    private String nombre;
    private String apellido;
    private String password;
    private String perfil;
    private String especialidad;
    private String nom;

    public Usuario(String usuario, String nombre, String apellido, String nom) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nom = nom;
    }

   
    
    public Usuario(String usuario, String nombre, String apellido, String password, String perfil, String especialidad) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.perfil = perfil;
        this.especialidad = especialidad;
    }

    public String getUsuario() {
        return usuario;
    }    
    
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getPassword() {
        return password;
    }

    public String getPerfil() {
        return perfil;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    
    
}