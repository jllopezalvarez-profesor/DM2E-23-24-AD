package es.jllopezalvarez.accesodatos.nosql.mongo.ejemplos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.*;


public class Ejemplo03BorradoSimple {

    // Cadena de conexión de Mongo. Ojo, no es JDBC.
    private static final String MONGO_DB_URI = "mongodb://localhost:27017";

    public static void main(String[] args) {

        // Preguntamos al usuario el id (campo idNumber, no _id) del usuario que queremos borrar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, dime el id (idNumber) del usuario que quieres borrar ");
        String idNumber = scanner.nextLine();

        // Abrimos conexión con Mongo
        try (MongoClient mongoClient = MongoClients.create(MONGO_DB_URI)) {
            // Obtenemos la base de datos
            MongoDatabase database = mongoClient.getDatabase("java_mongo_tests");

            // Borramos el usuario
            deleteUser(database, idNumber);
        }
    }

    private static void deleteUser(MongoDatabase database, String idNumber) {
        // Obtenemos la colección de usuarios
        MongoCollection<Document> collection = database.getCollection("users");

        // Creamos un filtro para localizar el usuario
        Bson query =  Filters.eq("idNumber", idNumber);

        // Usamos el método deleteOne con el filtro
        DeleteResult result = collection.deleteOne(query);

        // Comprobamos resultado
        System.out.println(result);
    }
}