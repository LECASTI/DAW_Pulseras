// src/main/java/org/example/model/Favoritos.java
package org.example.model;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;

public class Favoritos implements Serializable {
    private ObjectId id;
    private ObjectId usuario_id; // Referencia al usuario
    private List<ObjectId> materiales_ids; // Referencias a la colección 'materiales'

    public Favoritos() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(ObjectId usuario_id) {
        this.usuario_id = usuario_id;
    }

    public List<ObjectId> getMateriales_ids() {
        return materiales_ids;
    }

    public void setMateriales_ids(List<ObjectId> materiales_ids) {
        this.materiales_ids = materiales_ids;
    }
}