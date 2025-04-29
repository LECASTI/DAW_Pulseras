// src/main/java/org/example/model/Material.java
package org.example.model;

import java.io.Serializable;
import org.bson.types.ObjectId;

//===== Bean Material =====//
public class Material implements Serializable {
    private ObjectId id;
    private String nombre;
    private String descripcion;
    private String tipo;

    public Material() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
}
//===== Fin Material =====//