/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.util.ArrayList;

/**
 *
 * @author MiguelAngel
 */
public class UsuarioRegistrado extends Persona{
    private String nick;
    private int edad;
    private String claveAcceso;
    private String correo;
    private ArrayList<Comentario> comentarios;
    private ArrayList<Fotografia> fotosPublicadas;
    private ArrayList<Fotografia> fotosEtiquetado;

    public UsuarioRegistrado(String nick, int edad, String claveAcceso, String correo, FullName nombreCompleto) {
        super(nombreCompleto);
        this.nick = nick;
        this.edad = edad;
        this.claveAcceso = claveAcceso;
        this.correo = correo;
        this.comentarios = new ArrayList<>();
        this.fotosPublicadas = new ArrayList<>();
        this.fotosEtiquetado = new ArrayList<>();
    }
    
    
    public FullName getNombreCompleto() {
        return nombreCompleto;
    }
    public String getNick() {
        return nick;
    }
    public int getEdad() {
        return edad;
    }
    public String getClaveAcceso() {
        return claveAcceso;
    }
    public String getCorreo() {
        return correo;
    }
    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }
    public ArrayList<Fotografia> getFotosPublicadas() {
        return fotosPublicadas;
    }
    public ArrayList<Fotografia> getFotosEtiquetado() {
        return fotosEtiquetado;
    }

    public void setComentarios(Comentario comentario) {
        this.comentarios.add(comentario);
    }

    public void setFotosPublicadas(Fotografia f) {
        this.fotosPublicadas.add(f);
    }

    public void setFotosEtiquetado(Fotografia f) {
        this.fotosEtiquetado.add(f);
    }
    
    
    
    
    
    
    
}
