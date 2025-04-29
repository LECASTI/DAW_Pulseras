package org.example.model;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;

//===== Bean Chat =====//
public class Chat implements Serializable {
    private ObjectId id;
    private String sessionId;  // Siempre presente (vinculado a Sesion)
    private Boolean activo;
    private Date fechaUltimoMensaje;

    public Chat() {}

    // Getters y setters
    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
    public Date getFechaUltimoMensaje() { return fechaUltimoMensaje; }
    public void setFechaUltimoMensaje(Date fechaUltimoMensaje) { this.fechaUltimoMensaje = fechaUltimoMensaje; }
}
//===== Fin Chat =====//