package org.example.model;


import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.Date;

//===== Bean Mensaje =====//
public class Mensaje implements Serializable {
    private ObjectId id;
    private String contenido;
    private Date fecha;
    private String sessionId;      // Sesión desde donde se envió
    private ObjectId remitenteId;  // Null si es invitado, userId si es usuario loggeado
    private boolean esAdmin;       // True si lo envía un admin

    public Mensaje() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public ObjectId getRemitenteId() { return remitenteId; }
    public void setRemitenteId(ObjectId remitenteId) { this.remitenteId = remitenteId; }
    public boolean isEsAdmin() { return esAdmin; }
    public void setEsAdmin(boolean esAdmin) { this.esAdmin = esAdmin; }
}
//===== Fin Mensaje =====//