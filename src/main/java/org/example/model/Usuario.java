// src/main/java/org/example/model/Usuario.java
package org.example.model;

import java.io.Serializable;
import org.bson.types.ObjectId;

public class Usuario implements Serializable {
    private ObjectId id;
    private String nombre_usuario;
    private String correo_electronico;
    private String contrasena;
    private String rol; // "usuario" o "admin"
    private String nombre_completo;
    private String direccion;
    private String telefono;
    private ObjectId carrito_id; // Referencia a la colección 'carritos'
    private ObjectId lista_deseos_id; // Referencia a la colección 'listas_deseos'

    public Usuario() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronico(String correo_electronico) {
        this.correo_electronico = correo_electronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public ObjectId getCarrito_id() {
        return carrito_id;
    }

    public void setCarrito_id(ObjectId carrito_id) {
        this.carrito_id = carrito_id;
    }

    public ObjectId getLista_deseos_id() {
        return lista_deseos_id;
    }

    public void setLista_deseos_id(ObjectId lista_deseos_id) {
        this.lista_deseos_id = lista_deseos_id;
    }
}