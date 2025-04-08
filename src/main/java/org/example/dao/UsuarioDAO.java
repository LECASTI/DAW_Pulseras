// src/main/java/org/example/dao/UsuarioDAO.java
package org.example.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.model.Usuario;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class UsuarioDAO {
    private final MongoCollection<Document> usuariosCollection;
    private final MongoClient mongoClient;

    public UsuarioDAO(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        MongoDatabase database = mongoClient.getDatabase("pulseras_emilio"); // Reemplaza con tu nombre de base de datos si es diferente
        this.usuariosCollection = database.getCollection("usuarios");
    }

    public Usuario getUsuario(ObjectId id) {
        Document doc = usuariosCollection.find(eq("_id", id)).first();
        return documentToUsuario(doc);
    }

    public Usuario getUsuarioPorNombreUsuario(String nombreUsuario) {
        Document doc = usuariosCollection.find(eq("nombre_usuario", nombreUsuario)).first();
        return documentToUsuario(doc);
    }

    public List<Usuario> getAllUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        for (Document doc : usuariosCollection.find()) {
            usuarios.add(documentToUsuario(doc));
        }
        return usuarios;
    }

    // Implementar métodos para agregar, actualizar y eliminar usuarios
    // public void addUsuario(Usuario usuario) { ... }
    // public void updateUsuario(Usuario usuario) { ... }
    // public void deleteUsuario(ObjectId id) { ... }

    private Usuario documentToUsuario(Document doc) {
        if (doc == null) {
            return null;
        }
        Usuario usuario = new Usuario();
        usuario.setId(doc.getObjectId("_id"));
        usuario.setNombre_usuario(doc.getString("nombre_usuario"));
        usuario.setCorreo_electronico(doc.getString("correo_electronico"));
        usuario.setContrasena(doc.getString("contrasena"));
        usuario.setRol(doc.getString("rol"));
        usuario.setNombre_completo(doc.getString("nombre_completo"));
        usuario.setDireccion(doc.getString("direccion"));
        usuario.setTelefono(doc.getString("telefono"));
        usuario.setCarrito_id(doc.getObjectId("carrito_id"));
        usuario.setLista_deseos_id(doc.getObjectId("lista_deseos_id"));
        return usuario;
    }
}