/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author MiguelAngel
 */
public class UsuarioNoRegistrado extends Persona{

    public UsuarioNoRegistrado(FullName nombreCompleto) {
        super(nombreCompleto);
    }

    public FullName getNombreCompleto() {
        return nombreCompleto;
    }
    
    
}
