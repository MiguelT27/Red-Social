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
public class UsuarioRegistradoEtiquetado {
    private UsuarioRegistrado etiquetado;
    private double coorX;
    private double coorY;

    public UsuarioRegistradoEtiquetado(UsuarioRegistrado etiquetado, double coorX, double coorY) {
        this.etiquetado = etiquetado;
        this.coorX = coorX;
        this.coorY = coorY;
    }

    public UsuarioRegistrado getEtiquetado() {
        return etiquetado;
    }
    


    
}
