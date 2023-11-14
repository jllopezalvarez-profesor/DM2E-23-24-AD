package es.jllopezalvarez.accesodatos.nosql.mongo.ejemplos;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import net.datafaker.Faker;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.*;



public class Ejemplo02Insercion {

    // Cadena de conexión de Mongo. Ojo, no es JDBC.
    private static final String MONGO_DB_URI = "mongodb://localhost:27017";
    public static final int NUM_ROLES = 2;
    public static final int MIN_AGE = 20;
    public static final int MAX_AGE = 30;

    // Objetos Faker y Random para generar datos al azar
    private static final Faker faker = new Faker(new Locale("es"));
    private static final Random rnd = new Random();

    public static void main(String[] args) {

        // Preguntamos al usuario cuantos elementos quiere crear
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Cuántos usuarios quieres crear? ");
        int numUsuarios = scanner.nextInt();

        // Abrimos conexión con Mongo
        try (MongoClient mongoClient = MongoClients.create(MONGO_DB_URI)) {
            // Obtenemos la base de datos
            MongoDatabase database = mongoClient.getDatabase("java_mongo_tests");

            while (numUsuarios > 0) {
                insertNewUser(database);
                numUsuarios--;
            }
        }
    }

    private static void insertNewUser(MongoDatabase database) {
        // Creamos un objeto de tipo "Document" (paquete org.bson)
        // Para añadir propiedades usamos el método append.
        Document newUser = new Document();

        // Asignamos un id de documento. En Mongo la clave del documento es el campo _id
        // Usamos el constructor de org.bson.types.ObjectId, porque en Mongo (y NoSql en general)
        // es mejor no usar autoincrementales. Este tipo de BBDD está orientado a arquitecturas
        // distribuidas y escalado horizontal, y usar autonuméricos puede ser un problema en caso
        // de pérdidas de conexión o caídas de uno de los nodos.
        newUser.append("_id", new ObjectId());

        // Ahora podemos añadir el resto de propiedades. Las vamos a generar aleatoriamente con DataFaker
        newUser.append("idNumber", faker.idNumber().valid());
        // El método createNameDocument crea un documento con tres valores, nombre, segundo nombre y apellidos
        // Podemos usar el modo "fluent", porque append devuelve el propio documento sobre el que añadimos
        newUser.append("name", createNameDocument())
                .append("birthDate", faker.date().birthday(MIN_AGE, MAX_AGE).toString());
        // Podemos añadir listas de propiedades
        newUser.append("roles", getRandomRoles(NUM_ROLES));

        // Obtenemos la colección de usuarios
        MongoCollection<Document> collection = database.getCollection("users");

        // Insertamos el usuario
        InsertOneResult result = collection.insertOne(newUser);

        // Comprobamos resultado
        System.out.println(result);

    }

    private static List<Document> getRandomRoles(int numRoles) {
        // Generamos roles aleatorios, tantos como se indican, sin repetirse.
        // Usamos un conjunto para que no haya repetidos.
        Set<Rol> roles = new HashSet<>();
        while (roles.size() < numRoles) {
            roles.add(Rol.values()[rnd.nextInt(numRoles)]);
        }
        // Convertimos con la stream API los roles en lista de documentos BSon
        return roles.stream().map(r -> new Document("rol", r.name())).toList();
    }

    private static Document createNameDocument() {
        // Podemos crear el documento y usar la API "fluent" para encadenar llamadas.
        return new Document("firstName", faker.name().firstName())
                .append("middleName", faker.name().firstName())
                .append("lastName", faker.name().lastName());
    }
}