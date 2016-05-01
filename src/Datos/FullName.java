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
public class FullName {
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;

    public FullName(String nombre1, String nombre2, String apellido1, String apellido2) {
        this.nombre1 = nombre1;
        this.nombre2 = nombre2;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }
    
    
    public String getNombre1() {
        return nombre1;
    }

    public String getNombre2() {
        return nombre2;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }
    
    
}
