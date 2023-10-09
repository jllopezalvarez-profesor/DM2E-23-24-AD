package es.jllopezalvarez.accesodatos.ejemplosjaxb.ejemplo01marshalunmarshal;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class Ejemplo03XmlToObject {

    private static final String PERSON_XML = """
            <person firstName="Vance">
                <id>727-26-8188</id>
                <lastName>Corwin</lastName>
            </person>
            """;

    public static void main(String[] args) throws JAXBException {
        // Creamos un Stream para leer la cadena con el XML
        try (StringReader reader = new StringReader(PERSON_XML)) {
            // Creamos el contexto JAXB y el unmarshaller
            JAXBContext context = JAXBContext.newInstance(Person.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // Hacemos la conversión de la persona
            // Hay que hacer cast, al igual que en una
            // lectura de objetos con ObjectInputStream
            Person person = (Person) unmarshaller.unmarshal(reader);

            System.out.println(person);


        }

    }
}
