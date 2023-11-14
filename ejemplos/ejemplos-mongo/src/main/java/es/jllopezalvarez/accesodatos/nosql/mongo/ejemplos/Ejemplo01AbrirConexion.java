package es.jllopezalvarez.accesodatos.nosql.mongo.ejemplos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class Ejemplo01AbrirConexion {

    public static final String MONGO_DB_URI = "mongodb://localhost:27017";

    public static void main(String[] args ) {
        // Cadena de conexión
        try (MongoClient mongoClient = MongoClients.create(MONGO_DB_URI)) {
            MongoDatabase database = mongoClient.getDatabase("prueba_mongo_lunes");
            MongoCollection<Document> collection = database.getCollection("pruebas");

            collection.insertOne(new Document().append("nombre", "José Luis").append("apellidos", "López Álvarez"));

            Document doc = collection.find(eq("nombre", "José Luis")).first();
            if (doc != null) {
                System.out.println(doc.toJson());
            } else {
                System.out.println("No se ha encontrado el documento.");
            }
        }
    }
}