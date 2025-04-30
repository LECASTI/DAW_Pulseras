// src/main/java/org/example/model/Ventas.java
package org.example.model;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;

//===== Bean Ventas =====//
public class Ventas implements Serializable {
    private ObjectId id;
    private ObjectId usuarioId;    // Obligatorio (solo usuarios registrados)
    private ObjectId pulseraId;
    
    public Ventas() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public ObjectId getUsuarioId() { return usuarioId; }
    public void setUsuarioId(ObjectId usuarioId) { this.usuarioId = usuarioId; }
    public ObjectId getPulseraId() { return this.pulseraId; }
    public void setPulseraId(ObjectId id) { this.pulseraId = id; }
}
//===== Fin Ventas =====//
