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
public abstract class Persona {
    protected FullName nombreCompleto;

    public Persona(FullName nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
}
