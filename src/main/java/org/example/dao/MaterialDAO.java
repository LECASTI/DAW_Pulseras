// src/main/java/org/example/dao/MaterialDAO.java
package org.example.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.example.model.Material;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MaterialDAO {
    private final MongoCollection<Document> materialesCollection;
    private final MongoClient mongoClient;

    public MaterialDAO(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
        MongoDatabase database = mongoClient.getDatabase("pulseras_emilio"); // Reemplaza con tu nombre de base de datos si es diferente
        this.materialesCollection = database.getCollection("materiales");
    }

    public Material getMaterial(ObjectId id) {
        Document doc = materialesCollection.find(eq("_id", id)).first();
        return documentToMaterial(doc);
    }

    public List<Material> getAllMateriales() {
        List<Material> materiales = new ArrayList<>();
        for (Document doc : materialesCollection.find()) {
            materiales.add(documentToMaterial(doc));
        }
        return materiales;
    }

    // Implementar métodos para agregar, actualizar y eliminar materiales
    // public void addMaterial(Material material) { ... }
    // public void updateMaterial(Material material) { ... }
    // public void deleteMaterial(ObjectId id) { ... }

    private Material documentToMaterial(Document doc) {
        if (doc == null) {
            return null;
        }
        Material material = new Material();
        material.setId(doc.getObjectId("_id"));
        material.setNombre(doc.getString("nombre"));
        material.setDescripcion(doc.getString("descripcion"));
        material.setTipo(doc.getString("tipo"));
        material.setColor(doc.getString("color"));
        material.setMaterial(doc.getString("material"));
        material.setTamaño_mm(doc.getDouble("tamaño_mm"));
        material.setRuta_imagen(doc.getString("ruta_imagen"));
        material.setCantidad_disponible(doc.getInteger("cantidad_disponible"));
        material.setCaracteristicas_espirituales(doc.getString("caracteristicas_espirituales"));
        return material;
    }
}