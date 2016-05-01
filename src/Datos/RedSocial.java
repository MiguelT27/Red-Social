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
public class RedSocial {
    private String nombre;
    private ArrayList<UsuarioRegistrado> usuarios;

    public RedSocial(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<UsuarioRegistrado> getUsuarios() {
        return usuarios;
    }
    
    
}
