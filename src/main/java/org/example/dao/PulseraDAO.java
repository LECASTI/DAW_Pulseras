// src/main/java/org/example/dao/PulseraDAO.java
package org.example.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.model.Pulsera;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class PulseraDAO {
    private final MongoCollection<Document> pulserasCollection;
    private final MongoClient mongoClient;

    public PulseraDAO(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        MongoDatabase database = mongoClient.getDatabase("pulseras_emilio"); // Reemplaza con tu nombre de base de datos si es diferente
        this.pulserasCollection = database.getCollection("pulseras");
    }

    public Pulsera getPulsera(ObjectId id) {
        Document doc = pulserasCollection.find(eq("_id", id)).first();
        return documentToPulsera(doc);
    }

    public List<Pulsera> getAllPulseras() {
        List<Pulsera> pulseras = new ArrayList<>();
        for (Document doc : pulserasCollection.find()) {
            pulseras.add(documentToPulsera(doc));
        }
        return pulseras;
    }

    // Implementar métodos para agregar, actualizar y eliminar pulseras
    // public void addPulsera(Pulsera pulsera) { ... }
    // public void updatePulsera(Pulsera pulsera) { ... }
    // public void deletePulsera(ObjectId id) { ... }

    private Pulsera documentToPulsera(Document doc) {
        if (doc == null) {
            return null;
        }
        Pulsera pulsera = new Pulsera();
        pulsera.setId(doc.getObjectId("_id"));
        pulsera.setNombre(doc.getString("nombre"));
        pulsera.setDescripcion(doc.getString("descripcion"));
        pulsera.setRuta_imagen(doc.getString("ruta_imagen"));
        pulsera.setImagenes_adicionales((List<String>) doc.get("imagenes_adicionales"));
        pulsera.setColores_predominantes((List<String>) doc.get("colores_predominantes"));
        pulsera.setMateriales_usados((List<ObjectId>) doc.get("materiales_usados"));
        pulsera.setLongitud_cm(doc.getDouble("longitud_cm"));
        pulsera.setPrecio(doc.getDouble("precio"));
        pulsera.setCodigo_builder(doc.getString("codigo_builder"));
        pulsera.setListada(doc.getBoolean("listada"));
        pulsera.setFavorited_by_count(doc.getInteger("favorited_by_count"));
        return pulsera;
    }
}