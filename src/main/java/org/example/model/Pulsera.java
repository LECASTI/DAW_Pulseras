// src/main/java/org/example/model/Pulsera.java
package org.example.model;

import java.io.Serializable;
import java.util.List;
import org.bson.types.ObjectId;

public class Pulsera implements Serializable {
    private ObjectId id;
    private String nombre;
    private String descripcion;
    private String ruta_imagen;
    private List<String> imagenes_adicionales;
    private List<String> colores_predominantes;
    private List<ObjectId> materiales_usados;
    private Double longitud_cm;
    private Double precio;
    private String codigo_builder;
    private Boolean listada;
    private Integer favorited_by_count;

    public Pulsera() {
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

    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }

    public List<String> getImagenes_adicionales() {
        return imagenes_adicionales;
    }

    public void setImagenes_adicionales(List<String> imagenes_adicionales) {
        this.imagenes_adicionales = imagenes_adicionales;
    }

    public List<String> getColores_predominantes() {
        return colores_predominantes;
    }

    public void setColores_predominantes(List<String> colores_predominantes) {
        this.colores_predominantes = colores_predominantes;
    }

    public List<ObjectId> getMateriales_usados() {
        return materiales_usados;
    }

    public void setMateriales_usados(List<ObjectId> materiales_usados) {
        this.materiales_usados = materiales_usados;
    }

    public Double getLongitud_cm() {
        return longitud_cm;
    }

    public void setLongitud_cm(Double longitud_cm) {
        this.longitud_cm = longitud_cm;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCodigo_builder() {
        return codigo_builder;
    }

    public void setCodigo_builder(String codigo_builder) {
        this.codigo_builder = codigo_builder;
    }

    public Boolean getListada() {
        return listada;
    }

    public void setListada(Boolean listada) {
        this.listada = listada;
    }

    public Integer getFavorited_by_count() {
        return favorited_by_count;
    }

    public void setFavorited_by_count(Integer favorited_by_count) {
        this.favorited_by_count = favorited_by_count;
    }
}