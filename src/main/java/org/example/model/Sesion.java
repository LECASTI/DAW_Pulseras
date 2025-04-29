package org.example.model;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;

//===== Bean Sesion =====//
public class Sesion implements Serializable {
    private String sessionId;  // ID único de la sesión (generado al iniciar)
    private ObjectId usuarioId; // Null si es invitado, asignado al loggearse
    private Date fechaCreacion;
    private Date ultimaActividad;
    private ObjectId chatId;   // Chat asociado a esta sesión

    public Sesion() {}

    // Getters y setters
    public String getSessionId() { return sessionId; }
    public void setSessionId(String sessionId) { this.sessionId = sessionId; }
    public ObjectId getUsuarioId() { return usuarioId; }
    public void setUsuarioId(ObjectId usuarioId) { this.usuarioId = usuarioId; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Date fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Date getUltimaActividad() { return ultimaActividad; }
    public void setUltimaActividad(Date ultimaActividad) { this.ultimaActividad = ultimaActividad; }
    public ObjectId getChatId() { return chatId; }
    public void setChatId(ObjectId chatId) { this.chatId = chatId; }

    // Método helper para verificar si es sesión de invitado
    public boolean esInvitado() {
        return usuarioId == null;
    }
}
//===== Fin Sesion =====//