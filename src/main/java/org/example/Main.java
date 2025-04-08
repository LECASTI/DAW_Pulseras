package org.example;


import java.io.ByteArrayOutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSUploadOptions;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import spark.Request;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import static spark.Spark.*;

/**
 * Clase principal del servidor que implementa un sistema gestor de inventario
 * para pulseras, con endpoints para la visualización pública y un panel de administración.
 */
public class Main {

    // Conexión a MongoDB y configuración de colecciones
    private static MongoClient mongoClient = null;
    private static final String DB_NAME = "pulserasDB";
    private static final String COLLECTION_NAME_PULSERAS = "pulseras";

    // ANSI colors para depuración en consola
    private static final String ERROR = "\033[91m";
    private static final String SUCCESS = "\033[92m";
    private static final String INFO = "\033[93m";
    private static final String RESET = "\033[0m";
    private static final String VARIABLE = "\033[94m";

    public static void main(String[] args) {

        // Configuramos el puerto de Spark
        port(4567);
        System.out.println(INFO + "Iniciando servidor en el puerto 4567..." + RESET);

        try {
            System.out.println(INFO + "Intentando conectar a MongoDB Atlas..." + RESET);
            String connectionString = "mongodb+srv://emiliocastillon8:b1bV10jueIac55QE@cluster0.ngkrl.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
            ConnectionString connString = new ConnectionString(connectionString);
            ServerApi serverApi = ServerApi.builder().version(ServerApiVersion.V1).build();
            mongoClient = MongoClients.create(
                    com.mongodb.MongoClientSettings.builder()
                            .applyConnectionString(connString)
                            .serverApi(serverApi)
                            .build()
            );
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            database.runCommand(new Document("ping", 1));
            System.out.println(SUCCESS + "¡Conexión exitosa a MongoDB Atlas!" + RESET);
            try (DatagramSocket socket = new DatagramSocket()) {
                socket.connect(InetAddress.getByName("8.8.8.8"), 80);
                String ip = socket.getLocalAddress().getHostAddress();
                System.out.println("ip:" + ip + ":4567/");
            } catch (Exception e) {
                System.out.println("ip:127.0.0.1:4567/");
            }
        } catch (MongoException e) {
            System.out.println(ERROR + "Error conectando a MongoDB Atlas..." + RESET);
            System.err.println(ERROR + "Detalles del error: " + e.getMessage() + RESET);

            // Ruta de error que muestra un mensaje en HTML
            get("/*", (req, res) -> {
                res.type("text/html");
                return "<html><head><title>Error de BD</title></head><body>" +
                        "<div style='display: flex; align-items: center; padding:20px;'>" +
                        "<div style='font-size: 100px; margin-right: 20px;'>:(</div>" +
                        "<div><h1>Error: Hubo un error al conectar con la BD</h1></div>" +
                        "</div></body></html>";
            });
            return;
        }

        // Servir archivos estáticos ubicados en resources/public
        staticFileLocation("/public");

        // Ruta raíz: sirve el archivo index.html
        get("/", (req, res) -> {
            res.type("text/html");
            return renderFile();
        });

        // ============================
        // ENDPOINTS PARA CLIENTES
        // ============================

        /**
         * Endpoint público para obtener las pulseras que no estén deslistadas.
         */
        get("/api/pulseras", (req, res) -> {
            res.type("application/json");
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_PULSERAS);
            List<Document> pulserasList = new ArrayList<>();
            collection.find(Filters.eq("delisted", false)).into(pulserasList);
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < pulserasList.size(); i++) {
                json.append(pulserasList.get(i).toJson());
                if (i < pulserasList.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");
            System.out.println(INFO+"Se sirvieron pulseras a un usuario"+RESET);
            return json.toString();
        });
        get("/api/pulseras/imagen/:id", (req, res) -> {
            String imageId = req.params(":id");
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            GridFSBucket gridFSBucket = GridFSBuckets.create(database, "images");

            try {
                GridFSDownloadStream downloadStream = gridFSBucket.openDownloadStream(new ObjectId(imageId));
                res.type(downloadStream.getGridFSFile().getMetadata().getString("type"));

                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = downloadStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                downloadStream.close();

                System.out.println(INFO + "Se sirvió una imagen con ID: " + imageId + RESET);
                return outputStream.toByteArray();
            } catch (Exception e) {
                System.out.println(ERROR + "Error al obtener la imagen: " + e.getMessage() + RESET);
                res.status(404);
                return "Imagen no encontrada";
            }
        });

        // ============================
        // ENDPOINTS PARA ADMINISTRADOR
        // ============================

        /**
         * Endpoint para login del administrador.
         * Las credenciales requeridas son: usuario "admin" y contraseña "admin".
         */
        post("/admin/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            if ("admin".equals(username) && "admin".equals(password)) {
                req.session().attribute("admin", true);
                System.out.println(SUCCESS+"Un administrador inicio sesion"+RESET);
                return "success";
            }
            System.out.println(ERROR+"Un usuario intento iniciar sesion como administrador"+RESET);
            return "failure";
        });

        /**
         * Endpoint para el logout del administrador.
         */
        get("/admin/logout", (req, res) -> {
            req.session().removeAttribute("admin");
            System.out.println(INFO+"Un administrador cerro su sesion"+RESET);
            return "logged out";
        });

        /**
         * Endpoint para que el administrador obtenga la lista completa de pulseras,
         * incluyendo las que estén deslistadas.
         */
        get("/api/admin/pulseras", (req, res) -> {
            // Verificar que el usuario esté autenticado como admin
            if (isNotAdmin(req)) {
                res.status(401);
                return "Unauthorized";
            }
            res.type("application/json");
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_PULSERAS);
            List<Document> pulserasList = new ArrayList<>();
            collection.find().into(pulserasList);
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < pulserasList.size(); i++) {
                json.append(pulserasList.get(i).toJson());
                if (i < pulserasList.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");
            System.out.println(INFO+"Se sirvieron pulseras a un administrador"+RESET);
            return json.toString();
        });

        get("/api/admin/pulseras/imagen/:id", (req, res) -> {
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            GridFSBucket gridFSBucket = GridFSBuckets.create(database, "images");

            String imageId = req.params("id");
            res.type("image/jpeg");

            try (InputStream stream = gridFSBucket.openDownloadStream(new ObjectId(imageId))) {
                byte[] imageBytes = stream.readAllBytes();
                res.raw().getOutputStream().write(imageBytes);
            } catch (Exception e) {
                res.status(404);
                return "Imagen no encontrada";
            }

            return res.raw();
        });


        /**
         * Endpoint para agregar una nueva pulsera.
         * Se reciben los siguientes parámetros:
         * - nombre (String)
         * - precio (double)
         * - circunferencia (double)
         * - materiales (String; separados por comas)
         * - colores (String; separados por comas)
         * - separadores (String; separados por comas)
         * Se inicializa "repairCount" en 0 y "delisted" en false.
         */
        post("/admin/pulseras", (req, res) -> {
            if (isNotAdmin(req)) {
                res.status(401);
                System.out.println(ERROR+"Un usuario intento subir una pulsera"+RESET);
                return "Unauthorized";
            }

            System.out.println(INFO+"Un admin esta intentando subir una pulsera"+RESET);
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_PULSERAS);
            GridFSBucket gridFSBucket = GridFSBuckets.create(database, "images"); // GridFS para imágenes

            System.out.println(INFO+"DEBUG 1: paso lo de mongodb"+RESET);

            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));

            String nombre = req.raw().getParameter("nombre");
            String precioStr = req.raw().getParameter("precio");
            String circunferenciaStr = req.raw().getParameter("circunferencia");
            String materialesParam = req.raw().getParameter("materiales");
            String coloresParam = req.raw().getParameter("colores");
            String separadoresParam = req.raw().getParameter("separadores");

            System.out.println(INFO+"DEBUG 1.5"+RESET);
            System.out.println(INFO+"Nombre: "+VARIABLE+nombre+RESET);
            System.out.println(INFO+"Precio: "+VARIABLE+precioStr+RESET);
            System.out.println(INFO+"Circunferencia: "+VARIABLE+circunferenciaStr+RESET);
            System.out.println(INFO+"Materiales: "+VARIABLE+materialesParam+RESET);
            System.out.println(INFO+"Colores: "+VARIABLE+coloresParam+RESET);
            System.out.println(INFO+"Separadores: "+VARIABLE+separadoresParam+RESET);
            System.out.println(INFO+"DEBUG 2: paso la extraccion de parametros de forms"+RESET);

            double precio, circunferencia;
            try {
                precio = Double.parseDouble(precioStr);
                circunferencia = Double.parseDouble(circunferenciaStr);
            } catch (NumberFormatException e) {
                res.status(400);
                System.out.println(ERROR+"Error de precio o circunferencia"+RESET);
                return "Precio o circunferencia inválidos";
            }
            System.out.println(INFO+"DEBUG 3: Paso validacion de numeros"+RESET);
            List<String> materiales = materialesParam != null ? Arrays.asList(materialesParam.split(",")) : new ArrayList<>();
            List<String> colores = coloresParam != null ? Arrays.asList(coloresParam.split(",")) : new ArrayList<>();
            List<String> separadores = separadoresParam != null ? Arrays.asList(separadoresParam.split(",")) : new ArrayList<>();

            ObjectId imageId = null; // ID de la imagen en GridFS
            Part imagePart = req.raw().getPart("imagen"); // Obtener archivo de la petición

            if (imagePart != null && imagePart.getSize() > 0) {
                try (InputStream imageStream = imagePart.getInputStream()) {
                    GridFSUploadOptions options = new GridFSUploadOptions()
                            .chunkSizeBytes(255 * 1024)
                            .metadata(new Document("type", imagePart.getContentType()));

                    imageId = gridFSBucket.uploadFromStream(nombre + "-img", imageStream, options);
                }
            }
            System.out.println(INFO+"DEBUG 4: Paso separacion de parametros"+RESET);

            Document nuevaPulsera = new Document("nombre", nombre)
                    .append("precio", precio)
                    .append("circunferencia", circunferencia)
                    .append("materiales", materiales)
                    .append("colores", colores)
                    .append("separadores", separadores)
                    .append("repairCount", 0)
                    .append("delisted", false);

            if (imageId != null) {
                nuevaPulsera.append("imagenId", imageId.toHexString()); // Guardar referencia a la imagen
            }

            System.out.println(INFO+"DEBUG 5: paso creacion de nueva pulsera"+RESET);
            collection.insertOne(nuevaPulsera);
            System.out.println(SUCCESS+"Un administrador subio una nueva pulsera con exito"+RESET);
            return "Pulsera agregada exitosamente";
        });

        /**
         * Endpoint para actualizar el contador de reparaciones de una pulsera.
         * Se espera el parámetro "action" con valor "increment" o "decrement".
         * Se valida que no se supere el máximo de 2 reparaciones ni que sea menor a 0.
         */
        post("/admin/pulseras/:id/repair", (req, res) -> {
            if (isNotAdmin(req)) {
                res.status(401);
                return "Unauthorized";
            }
            String id = req.params("id");
            String action = req.queryParams("action");
            System.out.println(INFO+"DEBUG"+RESET);
            System.out.println(VARIABLE+id+RESET);
            System.out.println(VARIABLE+action+RESET);

            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_PULSERAS);
            Document pulsera = collection.find(Filters.eq("_id", new ObjectId(id))).first();
            if (pulsera == null) {
                res.status(404);
                return "Pulsera no encontrada";
            }
            int repairCount = pulsera.getInteger("repairCount", 0);
            if ("increment".equals(action)) {
                if (repairCount >= 2) {
                    return "No se pueden incrementar reparaciones, límite alcanzado";
                } else {
                    collection.updateOne(Filters.eq("_id", new ObjectId(id)),
                            new Document("$inc", new Document("repairCount", 1)));
                    return "Reparación incrementada";
                }
            } else if ("decrement".equals(action)) {
                if (repairCount <= 0) {
                    return "No se pueden decrementar reparaciones, valor mínimo alcanzado";
                } else {
                    collection.updateOne(Filters.eq("_id", new ObjectId(id)),
                            new Document("$inc", new Document("repairCount", -1)));
                    return "Reparación decrementada";
                }
            } else {
                res.status(400);
                return "Acción no reconocida";
            }
        });

        /**
         * Endpoint para eliminar una pulsera dado su id.
         */
        delete("/admin/pulseras/:id", (req, res) -> {
            if (isNotAdmin(req)) {
                res.status(401);
                return "Unauthorized";
            }
            String id = req.params("id");
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_PULSERAS);
            collection.deleteOne(Filters.eq("_id", new ObjectId(id)));
            return "Pulsera eliminada";
        });

        /**
         * Endpoint para actualizar el estado "delisted" de una pulsera.
         * Se espera el parámetro "status" con valor "true" o "false".
         */
        post("/admin/pulseras/:id/delist", (req, res) -> {
            if (isNotAdmin(req)) {
                res.status(401);
                return "Unauthorized";
            }
            String id = req.params("id");
            String statusParam = req.queryParams("status");
            boolean status = Boolean.parseBoolean(statusParam);
            MongoDatabase database = mongoClient.getDatabase(DB_NAME);
            MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME_PULSERAS);
            collection.updateOne(Filters.eq("_id", new ObjectId(id)),
                    new Document("$set", new Document("delisted", status)));
            return "Estado actualizado a " + status;
        });
    }

    /**
     * Método auxiliar para verificar si la sesión corresponde a un administrador.
     *
     * @param req Request actual
     * @return true si el usuario es admin, false de lo contrario
     */
    private static boolean isNotAdmin(Request req) {
        Boolean admin = req.session().attribute("admin");
        return admin == null || !admin;
    }

    /**
     * Método auxiliar para leer el contenido de un archivo ubicado en resources/public.
     *
     * @return Contenido del archivo en forma de String
     */
    private static String renderFile() {
        InputStream is = Main.class.getResourceAsStream("/public/" + "index.html");
        if (is == null)
            return "";
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
