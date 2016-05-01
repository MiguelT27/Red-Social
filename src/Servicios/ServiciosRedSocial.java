/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;
import Datos.*;
import java.util.*;
/**
 *
 * @author MiguelAngel
 */
public class ServiciosRedSocial {
    private ArrayList<RedSocial> redSoc;

    public ServiciosRedSocial() {
        this.redSoc = new ArrayList<>();
    }

    public ServiciosRedSocial(RedSocial red) {
        this.redSoc.add(red);
    }

    public ArrayList<RedSocial> getRedSoc() {
        return redSoc;
    }
    public void crearRedSocial() {
        Scanner lectura = new Scanner(System.in);
        String nombre;
        System.out.print("Ingrese el nombre de la red social: ");
        nombre = lectura.next();
        RedSocial red = new RedSocial(nombre);
        this.redSoc.add(red);
        System.out.println("Se ha creado la red social "+nombre);
    }
    public void registrarUsuario(RedSocial r){
        Scanner lectura = new Scanner (System.in);
        boolean bandera = false;
        String nombre1, nombre2, apellido1, apellido2, nick, clave, correo;
        int edad;
        do{
            System.out.print("\n\tIngrese su primer nombre:");
            nombre1 = lectura.next();
            System.out.print("\n\tIngrese su segundo nombre:");
            nombre2 = lectura.next();
            System.out.print("\n\tIngrese su primer apellido:");
            apellido1 = lectura.next();
            System.out.print("\n\tIngrese su segundo apellido:");
            apellido2 = lectura.next();
            if(nombre1.length()+nombre2.length()+apellido1.length()+apellido2.length() >= 100){
                bandera = true;
                throw new IndexOutOfBoundsException("Su nombre completo no puede contener mas de 100 caracteres.");
            }
        }while(bandera == true);
        FullName nomCom = new FullName(nombre1, nombre2, apellido1, apellido2);
        bandera = false;
        do{
            System.out.print("\n\tIngrese su nick de usuario:");
            nick = lectura.next();
            for (RedSocial red : this.redSoc) {
                if(red.getNombre().equals(r.getNombre())){
                    for (UsuarioRegistrado u : red.getUsuarios()) {
                        if(u.getNick().equals(nick)){
                            bandera = true;
                            throw new AssertionError("El nick ingresado ya esta en uso.");
                        }
                    }
                }
            }
            
        }while(bandera == true);
        System.out.print("\n\tIngrese su edad:");
        edad = lectura.nextInt();
        if(edad < 18){
            throw new IllegalArgumentException("Debe ser mayor de edad para registrarse en cualquier red social.");
        }
        bandera = false;
        do{
            System.out.print("\n\tIngrese su clave de acceso:");
            clave = lectura.next();
            if(clave.equals("123456")){
                bandera = true;
                throw new IllegalArgumentException("La clave no puede ser '123456'.");
            }
        }while(bandera == true);
        bandera = false;
        do{
            System.out.print("\n\tIngrese su correo electronico:");
            correo = lectura.next();
            for (int i = 0; i < correo.length(); i++) {
                char c = correo.charAt(i);
                if((int)c != 64){
                    if(i == correo.length()-1){
                        bandera = true;
                        throw new IllegalArgumentException("El formato de correo no es el correcto.");
                    }
                }else{
                    break;
                }
                
            }
        }while(bandera == true);
        UsuarioRegistrado u = new UsuarioRegistrado(nick, edad, clave, correo, nomCom);
        r.getUsuarios().add(u);
        System.out.println(u.getNombreCompleto().getNombre1()+" "+u.getNombreCompleto().getNombre2()+" fue registrado correctamente en "+r.getNombre());
    }
    public void iniciarSesion(RedSocial r){
        Scanner lectura = new Scanner (System.in);
        System.out.print("Ingrese su correo electronico: ");
        String correo = lectura.next();
        System.out.print("Ingrese su clave: ");
        String clave = lectura.next();
        for (UsuarioRegistrado u : r.getUsuarios()) {
            if (correo.equals(u.getCorreo()) && clave.equals(u.getClaveAcceso())){
                int opcion;
                String continuar = "";
                do {
                    System.out.println("\n\tOPCIONES\n\n\t1.Hacer Comentarios.\n\t2.Publicar Fotografias.");
                    System.out.print("\n\tSeleccione una opcion: ");
                    opcion = lectura.nextInt();
                    switch (opcion) {
                        case 1:
                            //Hacer Comentarios
                            String texto = lectura.next();
                            if(texto.length() > 200){
                                throw new IndexOutOfBoundsException("Los comentarios no pueden superar los 200 caracteres");
                            }
                            Date fechaDeCreacion = new Date();
                            Comentario c = new Comentario(texto, fechaDeCreacion);
                            u.setComentarios(c); 
                            break;
                        case 2 :
                            //Publicar Fotografias
                            String respuesta = "", nombre, descripcion;
                            int numero;
                            UsuarioRegistrado autor = u;
                            
                            System.out.println("\n\tNOMBRE DE LA FOTOGRAFIA: ");
                            nombre =lectura.nextLine();
                            System.out.println("Desea agregar una descripcion de la fotografia(S/N)");
                            respuesta = lectura.next();
                            if(respuesta.equals("S")){
                                descripcion = lectura.nextLine();
                                if(descripcion.length() > 200){
                                    throw new IndexOutOfBoundsException("La descripcion no puede superar los 200 caracteres");
                                }
                            }
                            Fotografia f = new Fotografia(nombre, autor);
                            System.out.println("A cuantos usuarios registrados desea etiquetar: ");
                            numero = lectura.nextInt();
                            if(numero < 2 || numero > 5){
                                throw new IndexOutOfBoundsException("Las fotos publicads deben tener minimo dos y maximo cinco usuarios registrados.");
                            }
                            for (int i = 0; i < numero; i++) {
                                System.out.println("Ingrese el nick del etiquetado "+(i+1)+":");
                                String nick = lectura.next();
                                UsuarioRegistrado etiquetado;
                                for (UsuarioRegistrado p: r.getUsuarios()) {
                                    if(p.getNick().equals(nick)){
                                        etiquetado = p;
                                        System.out.println("Ingrese la coordenada X de la posicion de "+etiquetado.getNombreCompleto().getNombre1()+" "+etiquetado.getNombreCompleto().getNombre2());
                                        double coorX = lectura.nextDouble();
                                        System.out.println("Ingrese la coordenada Y de la posicion de "+etiquetado.getNombreCompleto().getNombre1()+" "+etiquetado.getNombreCompleto().getNombre2());
                                        double coorY = lectura.nextDouble();
                                        UsuarioRegistradoEtiquetado etiquetadoRegistrado = new UsuarioRegistradoEtiquetado(etiquetado, coorX, coorY);
                                        f.setEtiquetadosRegistrados(etiquetadoRegistrado, i);
                                    }
                                }
                                
                            }
                            System.out.println("A cuantos usuarios no registrados desea etiquetar: ");
                            numero = lectura.nextInt();
                            if(numero < 1){
                                throw new IndexOutOfBoundsException("Las fotos publicads deben tener minimo un usuario no registrado.");
                            }
                            for (int i = 0; i < numero; i++) {
                                System.out.println("Ingrese el primer nombre del etiquetado "+(i+1)+":");
                                String nombre1 = lectura.next();
                                System.out.println("Ingrese el segundo nombre del etiquetado "+(i+1)+":");
                                String nombre2 = lectura.next();
                                System.out.println("Ingrese el primer apellido del etiquetado "+(i+1)+":");
                                String apellido1 = lectura.next();
                                System.out.println("Ingrese el segundo apellido del etiquetado "+(i+1)+":");
                                String apellido2 = lectura.next();
                                FullName nombreCom = new FullName(nombre1, nombre2, apellido1, apellido2);
                                UsuarioNoRegistrado etiquetado = new UsuarioNoRegistrado(nombreCom);
                                System.out.println("Ingrese la coordenada X de la posicion de "+etiquetado.getNombreCompleto().getNombre1()+" "+etiquetado.getNombreCompleto().getNombre2());
                                double coorX = lectura.nextDouble();
                                System.out.println("Ingrese la coordenada Y de la posicion de "+etiquetado.getNombreCompleto().getNombre1()+" "+etiquetado.getNombreCompleto().getNombre2());
                                double coorY = lectura.nextDouble();
                                UsuarioNoRegistradoEtiquetado etiquetadoNoRegistrado = new UsuarioNoRegistradoEtiquetado(etiquetado, coorX, coorY);
                                f.setEtiquetadosNoRegistrados(etiquetadoNoRegistrado);
                            }
                            break;
                        default:
                            System.out.println("OPCION INVALIDA");
                    }
                    System.out.print("Desea cerrar sesion(N) o desea continuar(S):");
                    continuar = lectura.next();
                } while (continuar.equals("S")); 
            }else if (correo.equals(u.getCorreo())) {
                System.out.println("\tCLAVE INCORRECTA");
            }else if (clave.equals(u.getClaveAcceso())) {
                System.out.println("\tCORREO INCORRECTO");
            }
        }
    }
}
