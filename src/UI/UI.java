/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;
import java.util.*;
import Servicios.*;
import java.io.IOException;
/**
 *
 * @author MiguelAngel
 */
public class UI {
    
    
    public static void main(String[] args) throws IOException {
        ServiciosRedSocial s = new ServiciosRedSocial();
        Scanner lectura = new Scanner(System.in);
        int opcion;
        String continuar = "";
        do {
            System.out.println("BIENVENIDO\n\n\tOPCIONES\n\n\t1.Crear una red Social.\n\t2.Registrarse.\n\t3.Iniciar Sesion.");
            System.out.print("\n\tSeleccione una opcion: ");
            opcion = lectura.nextInt();
            
            switch (opcion) {
                case 1:
                    s.crearRedSocial();
                    break;
                case 2:
                    try{
                        System.out.println("En cual de las siguientes redes desea registrarse:");
                        for (int i = 1; i <= s.getRedSoc().size(); i++) {
                            System.out.println(i+". "+s.getRedSoc().get(i-1).getNombre());
                        }
                        System.out.println("Seleccione una opcion: ");
                        int op = lectura.nextInt();
                        s.registrarUsuario(s.getRedSoc().get(op-1));
                    }catch(IndexOutOfBoundsException | AssertionError | IllegalArgumentException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("En cual de las siguientes redes desea iniciar sesion:");
                    for (int i = 1; i <= s.getRedSoc().size(); i++) {
                        System.out.println(i+". "+s.getRedSoc().get(i-1).getNombre());
                    }
                    System.out.println("Seleccione una opcion: ");
                    int op = lectura.nextInt();
                    try{ 
                        s.iniciarSesion(s.getRedSoc().get(op-1));
                    }catch(IndexOutOfBoundsException e){
                        System.out.println(e.getMessage());
                    }
                    
                    
                    break;
                default:
                    System.out.println("OPCION INVALIDA");
            }
            System.out.print("Desea Continuar (S/N):");
            continuar = lectura.next();
        } while (continuar.equals("S")); 
        
    }
    public void mostrarOpciones(){
        
    }
}
