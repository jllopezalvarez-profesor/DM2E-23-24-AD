package es.jllopezalvarez.accesodatos.nosql.mongo.ejemplos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;


public class Ejemplo04BorradoMultiple {

    // Cadena de conexión de Mongo. Ojo, no es JDBC.
    private static final String MONGO_DB_URI = "mongodb://localhost:27017";

    public static void main(String[] args) {

        // Preguntamos al usuario el id (campo idNumber, no _id) del usuario que queremos borrar
        Scanner scanner = new Scanner(System.in);
        System.out.print("Por favor, dime el comienzo de los id (idNumber) de los usuarios que quieres borrar: ");
        String idNumber = scanner.nextLine();

        // Abrimos conexión con Mongo
        try (MongoClient mongoClient = MongoClients.create(MONGO_DB_URI)) {
            // Obtenemos la base de datos
            MongoDatabase database = mongoClient.getDatabase("java_mongo_tests");

            // Borramos el usuario
            deleteMultipleUser(database, idNumber);
        }
    }

    private static void deleteMultipleUser(MongoDatabase database, String idNumberPart) {
        // Obtenemos la colección de usuarios
        MongoCollection<Document> collection = database.getCollection("users");

        // Creamos un filtro para localizar usuario como si fuera un "like".
        // En este caso tenemos que usar expresiones regulares.
        String regexValue = "^" + idNumberPart;
        Bson query =  Filters.regex("idNumber", regexValue);

        // Usamos el método deleteOne
        DeleteResult result = collection.deleteMany(query);

        // Comprobamos resultado
        System.out.println(result);
    }
}