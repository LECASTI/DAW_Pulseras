// src/main/java/org/example/model/Favoritos.java
package org.example.model;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;

//===== Bean Favoritos =====//
public class Favoritos implements Serializable {
    private ObjectId id;
    private List<ObjectId> pulserasIds;

    public Favoritos() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public List<ObjectId> getPulserasIds() { return pulserasIds; }
    public void setPulserasIds(List<ObjectId> pulserasIds) { this.pulserasIds = pulserasIds; }
}
//===== Fin Favoritos =====//
