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
    private ObjectId color_id;
    private int tamano_mm;
    private int cantidad_inventario;
    private String ruta_imagen; // Esto puede ser ruta relativa, directa o URL

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
    public ObjectId getColorId() { return color_id; }
    public void setColorId(ObjectId id) { this.color_id = id; }
    public int getTamano() { return this.tamano_mm; }
    public void setTamano(int tamano) { this.tamano_mm = tamano; }
    public int getCantidad() { return this.cantidad_inventario; }
    public void setCantidad(int stock) {this.cantidad_inventario = stock; }
    public String getRutaImagen() { return this.ruta_imagen; }
    public void setRutaImagen(String ruta) { this.ruta_imagen = ruta; }
//===== Fin Material =====//
