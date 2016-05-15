/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import java.util.Date;

/**
 *
 * @author MiguelAngel
 */
public class Comentario {
    private String texto;
    private Date fechaDeCreacion;

    public Comentario(String texto, Date fechaDeCreacion) {
        this.texto = texto;
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public String getTexto() {
        return texto;
    }

    public Date getFechaDeCreacion() {
        return fechaDeCreacion;
    }
    
}
