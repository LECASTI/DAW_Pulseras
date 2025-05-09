// src/main/java/org/example/model/Builds.java
package org.example.model;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;

//===== Bean Builds =====//
public class Builds implements Serializable {
    private ObjectId id;
    private List<ObjectId> pulserasIds;

    public Builds() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public List<ObjectId> getPulserasIds() { return pulserasIds; }
    public void setPulserasIds(List<ObjectId> pulserasIds) { this.pulserasIds = pulserasIds; }
}
//===== Fin Builds =====//
