// src/main/java/org/example/model/Material.java
package org.example.model;

import java.io.Serializable;
import org.bson.types.ObjectId;

public class Material implements Serializable {
    private ObjectId id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String color;
    private String material;
    private Double tamaño_mm;
    private String ruta_imagen;
    private Integer cantidad_disponible;
    private String caracteristicas_espirituales;

    public Material() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getTamaño_mm() {
        return tamaño_mm;
    }

    public void setTamaño_mm(Double tamaño_mm) {
        this.tamaño_mm = tamaño_mm;
    }

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public Integer getCantidad_disponible() {
        return cantidad_disponible;
    }

    public void setCantidad_disponible(Integer cantidad_disponible) {
        this.cantidad_disponible = cantidad_disponible;
    }

    public String getCaracteristicas_espirituales() {
        return caracteristicas_espirituales;
    }

    public void setCaracteristicas_espirituales(String caracteristicas_espirituales) {
        this.caracteristicas_espirituales = caracteristicas_espirituales;
    }
}