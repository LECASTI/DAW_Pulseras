// src/main/java/org/example/model/Pulsera.java
package org.example.model;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;

//===== Bean Pulsera =====//
public class Pulsera implements Serializable {
    private ObjectId id;
    private Double circunferencia;
    private Double precio;
    private List<ObjectId> materialesIds;
    private List<ObjectId> coloresIds;
    private Integer repairCount;
    private Boolean delisted;

    public Pulsera() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public Double getCircunferencia() { return circunferencia; }
    public void setCircunferencia(Double circunferencia) { this.circunferencia = circunferencia; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public List<ObjectId> getMaterialesIds() { return materialesIds; }
    public void setMaterialesIds(List<ObjectId> materialesIds) { this.materialesIds = materialesIds; }
    public List<ObjectId> getColoresIds() { return coloresIds; }
    public void setColoresIds(List<ObjectId> coloresIds) { this.coloresIds = coloresIds; }
    public Integer getRepairCount() { return repairCount; }
    public void setRepairCount(Integer repairCount) { this.repairCount = repairCount; }
    public Boolean getDelisted() { return delisted; }
    public void setDelisted(Boolean delisted) { this.delisted = delisted; }
}
//===== Fin Pulsera =====//