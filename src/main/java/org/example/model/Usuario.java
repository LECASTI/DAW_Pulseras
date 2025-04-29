// src/main/java/org/example/model/Usuario.java
package org.example.model;

import java.io.Serializable;
import org.bson.types.ObjectId;

//===== Bean Usuario =====//
public class Usuario implements Serializable {
    private ObjectId id;
    private String nombreUsuario;
    private String hashContrasena;
    private String correo;
    private String rol; // "admin" o "usuario"
    private ObjectId favoritosId;

    public Usuario() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getHashContrasena() { return hashContrasena; }
    public void setHashContrasena(String hashContrasena) { this.hashContrasena = hashContrasena; }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
    public ObjectId getFavoritosId() { return favoritosId; }
    public void setFavoritosId(ObjectId favoritosId) { this.favoritosId = favoritosId; }
}
//===== Fin Usuario =====//