package es.jllopezalvarez.sakila.api.saxhandlers;

import es.jllopezalvarez.sakila.api.dto.CityImportInfo;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CitiesHandler extends DefaultHandler {

    private List<CityImportInfo> citiesInfo = new ArrayList<>();

    int position = 0;
    private boolean inCity;
    private CityImportInfo cityInfo;
    private boolean inName;
    private boolean inCountryName;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName.toLowerCase()) {
            case "city":
                this.position++;
                this.inCity = true;
                this.cityInfo = new CityImportInfo();
                this.cityInfo.setPosition(this.position);
                break;
            case "name":
                this.inName = true;
                break;
            case "countryname":
                this.inCountryName = true;
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName.toLowerCase()) {
            case "city":
                this.citiesInfo.add(this.cityInfo);
                this.inCity = false;
                break;
            case "name":
                this.inName = false;
                break;
            case "countryname":
                this.inCountryName = false;
                break;

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(inCity && inName){
            String part = new String(ch, start, length);
            this.cityInfo.setCityName(this.cityInfo.getCityName() + part);
        }

        if(inCity && inCountryName){
            String part = new String(ch, start, length);
            this.cityInfo.setCountryName(this.cityInfo.getCountryName() + part);
        }

    }

    public List<CityImportInfo> getCitiesInfo() {
        return citiesInfo;
    }
}
