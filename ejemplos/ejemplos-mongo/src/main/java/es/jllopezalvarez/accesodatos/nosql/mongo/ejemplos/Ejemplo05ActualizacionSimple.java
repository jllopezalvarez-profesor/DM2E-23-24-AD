package es.jllopezalvarez.accesodatos.nosql.mongo.ejemplos;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;


public class Ejemplo05ActualizacionSimple {

    // Cadena de conexión de Mongo. Ojo, no es JDBC.
    private static final String MONGO_DB_URI = "mongodb://localhost:27017";

    public static void main(String[] args) {

        // Preguntamos al usuario el id (campo idNumber, no _id) del usuario que queremos borrar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, dime el id (idNumber) del usuario que quieres actualizar: ");
        String idNumber = scanner.nextLine();
        System.out.print("Ahora dime su nuevo nombre: ");
        String newFirstName = scanner.nextLine();
        System.out.print("Ahora dime su nuevo apellido: ");
        String newLastName = scanner.nextLine();

        // Abrimos conexión con Mongo
        try (MongoClient mongoClient = MongoClients.create(MONGO_DB_URI)) {
            // Obtenemos la base de datos
            MongoDatabase database = mongoClient.getDatabase("java_mongo_tests");

            // Actualizamos el usuario
            updateUser(database, idNumber, newFirstName, newLastName);
        }
    }

    private static void updateUser(MongoDatabase database, String idNumber, String newFirstName, String newLastName) {
        // Obtenemos la colección de usuarios
        MongoCollection<Document> collection = database.getCollection("users");

        // Creamos un documento sólo con los elementos que queremos actualizar
        Bson updates = Updates.combine(
                Updates.set("name.firstName", newFirstName),
                Updates.set("name.lastName", newLastName));

        // Si queremos insertar el documento si no existe previamente (upsert) tenemos que indicarlo con opciones:
        // UpdateOptions options = new UpdateOptions().upsert(true);

        // Creamos el filtro para encontrar el documento
        Bson query = Filters.eq("idNumber", idNumber);

        // Intentamos actualizar
        UpdateResult result = collection.updateOne(query, updates);
        // Si queremos pasar opciones:
        // UpdateResult result = collection.updateOne(query, updates, options);

        // Comprobamos resultado
        System.out.println(result);
    }
}