/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicios;
import Datos.*;
import java.util.*;
import java.io.*;
import java.nio.file.AccessDeniedException;
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
    public void iniciarSesion(RedSocial r) throws IOException{
        ServiciosRedSocial s = new ServiciosRedSocial();
        Scanner lectura = new Scanner(System.in);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader read = new BufferedReader (isr);
        System.out.print("Ingrese su correo electronico: ");
        String correo = lectura.next();
        System.out.print("Ingrese su clave: ");
        String clave = lectura.next();
        for (UsuarioRegistrado u : r.getUsuarios()) {
            if (correo.equals(u.getCorreo()) && clave.equals(u.getClaveAcceso())){
                int opcion;
                String continuar = "";
                do {
                    System.out.println("\n\tOPCIONES\n\n\t1.Hacer Comentarios.\n\t2.Publicar Fotografias.\n\t3.Buscar Usuarios.");
                    System.out.print("\n\tSeleccione una opcion: ");
                    opcion = lectura.nextInt();
                    switch (opcion) {
                        case 1:
                            //Hacer Comentarios
                            s.comentar(r,u);
                            break;
                        case 2 :
                            //Publicar Fotografias
                            s.publicarFotos(r, u);
                            break;
                        case 3:
                            s.buscarUsuarios(r);
                            break;
                        default:
                            System.out.println("OPCION INVALIDA");
                    }
                    System.out.print("Desea cerrar sesion(N) o desea continuar(S):");
                    continuar = lectura.next();
                } while (continuar.equals("S")); 
            }else if (correo.equals(u.getCorreo()) && clave.equals(u.getClaveAcceso()) == false) {
                System.out.println("\tCLAVE INCORRECTA");
            }else if (clave.equals(u.getClaveAcceso()) && correo.equals(u.getCorreo()) == false) {
                System.out.println("\tCORREO INCORRECTO");
            }else if(correo.equals(u.getCorreo()) == false && clave.equals(u.getClaveAcceso()) == false && u == r.getUsuarios().get(r.getUsuarios().size()-1)){
                throw new AccessDeniedException("Usted no se encuentra registrado en "+r.getNombre());
            }
            
        }
    }
    public void comentar(RedSocial r, UsuarioRegistrado u) throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader read = new BufferedReader (isr);
        System.out.print("\n\tIngrese su comentario: ");
        String texto = read.readLine();
        if (texto.length() > 200) {
            throw new IndexOutOfBoundsException("Los comentarios no pueden superar los 200 caracteres");
        }
        Date fechaDeCreacion = new Date();
        Comentario c = new Comentario(texto, fechaDeCreacion);
        u.setComentarios(c);
        System.out.println("El comentario se ha publicado correctamente en "+fechaDeCreacion);
    }
    
    public void publicarFotos(RedSocial r, UsuarioRegistrado u) throws IOException{
        Scanner lectura = new Scanner (System.in);
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader read = new BufferedReader (isr);
        String respuesta, descripcion;
        int numero;
        UsuarioRegistrado autor = u;
        System.out.println("\n\tNOMBRE DE LA FOTOGRAFIA: ");
        String nombre = read.readLine();
        Fotografia f = new Fotografia(nombre, autor);
        System.out.println("Desea agregar una descripcion de la fotografia(S/N)");
        respuesta = lectura.next();
        if (respuesta.equals("S")) {
            System.out.print("\n\tIngrese la descripcion de la fotografia: ");
            descripcion = read.readLine();
            if (descripcion.length() > 200) {
                throw new IndexOutOfBoundsException("La descripcion no puede superar los 200 caracteres");
            }
            f.setDescripcion(descripcion);
        }
        System.out.println("A cuantos usuarios no registrados desea etiquetar: ");
        numero = lectura.nextInt();
        if (numero < 1) {
            throw new IndexOutOfBoundsException("Las fotos publicads deben tener minimo un usuario no registrado.");
        }
        for (int i = 0; i < numero; i++) {
            System.out.println("Ingrese el primer nombre del etiquetado " + (i + 1) + ":");
            String nombre1 = lectura.next();
            System.out.println("Ingrese el segundo nombre del etiquetado " + (i + 1) + ":");
            String nombre2 = lectura.next();
            System.out.println("Ingrese el primer apellido del etiquetado " + (i + 1) + ":");
            String apellido1 = lectura.next();
            System.out.println("Ingrese el segundo apellido del etiquetado " + (i + 1) + ":");
            String apellido2 = lectura.next();
            FullName nombreCom = new FullName(nombre1, nombre2, apellido1, apellido2);
            UsuarioNoRegistrado etiquetado = new UsuarioNoRegistrado(nombreCom);
            System.out.println("Ingrese la coordenada X de la posicion de " + etiquetado.getNombreCompleto().getNombre1() + " " + etiquetado.getNombreCompleto().getNombre2());
            double coorX = lectura.nextDouble();
            System.out.println("Ingrese la coordenada Y de la posicion de " + etiquetado.getNombreCompleto().getNombre1() + " " + etiquetado.getNombreCompleto().getNombre2());
            double coorY = lectura.nextDouble();
            UsuarioNoRegistradoEtiquetado etiquetadoNoRegistrado = new UsuarioNoRegistradoEtiquetado(etiquetado, coorX, coorY);
            f.setEtiquetadosNoRegistrados(etiquetadoNoRegistrado);
        }
        
        System.out.println("A cuantos usuarios registrados desea etiquetar: ");
        numero = lectura.nextInt();
        if (numero < 2 || numero > 5) {
            throw new IndexOutOfBoundsException("Las fotos publicads deben tener minimo dos y maximo cinco usuarios registrados.");
        }
        for (int i = 0; i < numero; i++) {
            System.out.println("Ingrese el nick del etiquetado " + (i + 1) + ":");
            String nick = lectura.next();
            UsuarioRegistrado etiquetado;
            for (UsuarioRegistrado p : r.getUsuarios()) {
                if (p.getNick().equals(nick)) {
                    etiquetado = p;
                    System.out.println("Ingrese la coordenada X de la posicion de " + etiquetado.getNombreCompleto().getNombre1() + " " + etiquetado.getNombreCompleto().getNombre2());
                    double coorX = lectura.nextDouble();
                    System.out.println("Ingrese la coordenada Y de la posicion de " + etiquetado.getNombreCompleto().getNombre1() + " " + etiquetado.getNombreCompleto().getNombre2());
                    double coorY = lectura.nextDouble();
                    UsuarioRegistradoEtiquetado etiquetadoRegistrado = new UsuarioRegistradoEtiquetado(etiquetado, coorX, coorY);
                    f.setEtiquetadosRegistrados(etiquetadoRegistrado, i);
                    p.setFotosEtiquetado(f);
                } else if (p == r.getUsuarios().get(r.getUsuarios().size() - 1) && p.getNick().equals(nick) == false) {
                    throw new IllegalArgumentException("No existe ningun usuario con ese nick");
                }
            }

        }
        
        u.setFotosPublicadas(f);
        System.out.println("La fotografia "+f.getNombre()+" se publico correctamente.");
    }
    
    public void buscarUsuarios(RedSocial r) throws InvalidObjectException{
        ServiciosRedSocial s = new ServiciosRedSocial();
        Scanner lectura = new Scanner (System.in);
        String nombre1, nombre2, apellido1, apellido2, correo;
        String respuesta = "";
        int opcion;
        do {
            System.out.println("\n\tCual de los siguientes parametros de busqueda desea utilizar: ");
            System.out.println("\n\t1.Nombre Completo.\n\t2. Correo Electronico.");
            System.out.print("\tSeleccione una opcion:");
            opcion = lectura.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("\n\tIngrese su primer nombre:");
                    nombre1 = lectura.next();
                    System.out.print("\n\tIngrese su segundo nombre:");
                    nombre2 = lectura.next();
                    System.out.print("\n\tIngrese su primer apellido:");
                    apellido1 = lectura.next();
                    System.out.print("\n\tIngrese su segundo apellido:");
                    apellido2 = lectura.next();
                    for (UsuarioRegistrado us : r.getUsuarios()) {
                        if (us.getNombreCompleto().getNombre1().equals(nombre1) && us.getNombreCompleto().getNombre2().equals(nombre2) && us.getNombreCompleto().getApellido1().equals(apellido1) && us.getNombreCompleto().getApellido2().equals(apellido2)) {
                            System.out.print("\n\n\t" + us.getNombreCompleto().getNombre1() + " " + us.getNombreCompleto().getNombre2() + " " + us.getNombreCompleto().getApellido1() + " " + us.getNombreCompleto().getApellido2() + " se encuentra registrado en " + r.getNombre());
                            System.out.println("\n\tNICK: " + us.getNick() + "\n\tCORREO:" + us.getCorreo());
                            s.mostrarOpcionesUsuarios(r, us);
                        }else if (us.getNombreCompleto().getNombre1().equals(nombre1) == false && us.getNombreCompleto().getNombre2().equals(nombre2) == false && us.getNombreCompleto().getApellido1().equals(apellido1) == false && us.getNombreCompleto().getApellido2().equals(apellido2) == false && us == r.getUsuarios().get(r.getUsuarios().size()-1)){
                            throw new InvalidObjectException("El nombre ingresado no se encuentra registrado.");
                        }
                    }
                    break;
                case 2:
                    System.out.print("\n\tIngrese su correo:");
                    correo = lectura.next();
                    for (UsuarioRegistrado us : r.getUsuarios()) {
                        if (us.getCorreo().equals(correo)) {
                            System.out.print("\n\n\t" + us.getNombreCompleto().getNombre1() + " " + us.getNombreCompleto().getNombre2() + " " + us.getNombreCompleto().getApellido1() + " " + us.getNombreCompleto().getApellido2() + " se encuentra registrado en " + r.getNombre());
                            System.out.println("\n\tNICK: " + us.getNick() + "\n\tCORREO:" + us.getCorreo());
                            s.mostrarOpcionesUsuarios(r, us);
                        }else if (us.getCorreo().equals(correo) == false && us == r.getUsuarios().get(r.getUsuarios().size()-1)){
                            throw new InvalidObjectException("El correo ingresaddo no se encuentra registrado.");
                        }
                    }
                default:
                    System.out.println("OPCION INVALIDA");
            }

        } while (respuesta.equals("S"));
    }
    public void mostrarOpcionesUsuarios(RedSocial r, UsuarioRegistrado u){
        ServiciosRedSocial s = new ServiciosRedSocial();
        Scanner lectura = new Scanner (System.in);
        String respuesta;
        int opcion;
        do {
            System.out.println("\n\tOPCIONES DE USUARIO\n\n\t1.Listar sus comentarios.\n\t2. Listar Fotos en las que se encuentra etiquetado.");
            System.out.print("\tSeleccione una opcion: ");
            opcion = lectura.nextInt();
            switch (opcion) {
                case 1:
                    s.listarComentarios (u);
                    break;
                case 2:
                    s.listarFotosEtiquetado(u);
                default:
                    throw new AssertionError();
            }
            System.out.print("Desea continuar (S/N):");
            respuesta = lectura.next();
        } while (respuesta.equals("S"));
        
    }
    public void listarComentarios (UsuarioRegistrado u){
        if (u.getComentarios().isEmpty()) {
            System.out.println(u.getNombreCompleto().getNombre1() + " no ha publicado ningun comentario");
        } else {
            for (Comentario c : u.getComentarios()) {
                System.out.print("\n\tPublicado en: " + c.getFechaDeCreacion());
                System.out.println("\n" + c.getTexto());
            }
        }
    }
    public void listarFotosEtiquetado(UsuarioRegistrado u){
        if (u.getFotosEtiquetado().isEmpty()) {
            System.out.println(u.getNombreCompleto().getNombre1() + " no ha sido etiquetado en ninguna foto.");
        } else {
            for (Fotografia f : u.getFotosEtiquetado()) {
                System.out.print("\n\tFoto: \" "+f.getNombre()+" \"" );
                System.out.print("\n\tDescripcion: "+f.getDescripcion());
                System.out.print("\n\tAutor: "+f.getAutor().getNombreCompleto().getNombre1()+" "+f.getAutor().getNombreCompleto().getApellido1()+".");
                System.out.print("\n\tEtiquetados: ");
                for (UsuarioRegistradoEtiquetado p : f.getEtiquetadosRegistrados())System.out.println("\n\t\t"+p.getEtiquetado().getNombreCompleto().getNombre1()+" "+p.getEtiquetado().getNombreCompleto().getApellido1()+".");
            }
        }
    }
    
}
