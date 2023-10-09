package es.jllopezalvarez.accesodatos.ejemplosjaxb.ejemplo01marshalunmarshal;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.StringReader;

public class Ejemplo04XmlToList {

    private static final String PEOPLE_XML = """
            <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
            <people>
                    <person firstName="Alisha">
                    <id>288-74-4563</id>
                    <lastName>Boyer</lastName>
                </person>
                    <person firstName="Rico">
                    <id>512-97-5095</id>
                    <lastName>Metz</lastName>
                </person>
                    <person firstName="Kristyn">
                    <id>529-21-0904</id>
                    <lastName>Reilly</lastName>
                </person>
            </people>
            """;

    public static void main(String[] args) throws JAXBException {
        // Creamos un Stream para leer la cadena con el XML
        try (StringReader reader = new StringReader(PEOPLE_XML)) {
            // Creamos el contexto JAXB y el unmarshaller
            JAXBContext context = JAXBContext.newInstance(People.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            // Hacemos la conversión de la lista de personas
            // Hay que hacer cast, al igual que en una
            // lectura de objetos con ObjectInputStream
            People people = (People) unmarshaller.unmarshal(reader);

            System.out.println(people);


        }

    }
}