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
public class Fotografia {
    private String nombre;
    private String descripcion;
    private UsuarioRegistrado autor;
    private UsuarioRegistradoEtiquetado[] etiquetadosRegistrados;
    private ArrayList<UsuarioNoRegistradoEtiquetado> etiquetadosNoRegistrados;

    public Fotografia(String nombre, UsuarioRegistrado autor) {
        this.nombre = nombre;
        this.descripcion = "No tiene descripcion.";
        this.autor = autor;
        this.etiquetadosRegistrados = new UsuarioRegistradoEtiquetado[5];
        this.etiquetadosNoRegistrados = new ArrayList<>();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public void setEtiquetadosRegistrados(UsuarioRegistradoEtiquetado etiquetadoRegistrado, int posicion) {
        this.etiquetadosRegistrados[posicion] = etiquetadoRegistrado;
    }

    public void setEtiquetadosNoRegistrados(UsuarioNoRegistradoEtiquetado etiquetadoNoRegistrado) {
        this.etiquetadosNoRegistrados.add(etiquetadoNoRegistrado); 
    }
    
    
}
