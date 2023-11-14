package es.jllopezalvarez.sakila.api.services;

import es.jllopezalvarez.sakila.api.dto.CityImportInfo;
import es.jllopezalvarez.sakila.api.dto.ImportCitiesResult;
import es.jllopezalvarez.sakila.api.saxhandlers.CitiesHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

@Service
public class ImportService {
    public ImportCitiesResult importCities(MultipartFile citiesXmlFile) {
        try {
            // Obtenemos el objeto SAXParserFactory y creamos con él un objeto SaxParser.
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            // Creamos el handler
            CitiesHandler citiesHandler = new CitiesHandler();

            // Usamos el método parse para leer el fichero XML.
            parser.parse(citiesXmlFile.getInputStream(), citiesHandler);

            System.out.println(citiesHandler.getCitiesInfo());

            // TODO: procesar la lista de ciudaddes y países,
            // generando errores si procede



            return new ImportCitiesResult(false);

        } catch (ParserConfigurationException | SAXException e) {
            throw new RuntimeException("Error al procesar el fichero XML", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
