package org.example.model;

import org.bson.types.ObjectId;
import java.io.Serializable;

//===== Bean Color =====//
public class Color implements Serializable {
    private ObjectId id;
    private String nombre;
    private String codigoHex;

    public Color() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCodigoHex() { return codigoHex; }
    public void setCodigoHex(String codigoHex) { this.codigoHex = codigoHex; }
}
//===== Fin Color =====//