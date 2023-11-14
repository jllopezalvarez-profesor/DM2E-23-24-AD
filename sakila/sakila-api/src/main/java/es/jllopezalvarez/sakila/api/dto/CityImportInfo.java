package es.jllopezalvarez.sakila.api.dto;

public class CityImportInfo {
    private int position;
    private String cityName;
    private String countryName;

    public CityImportInfo() {

        this.position = 1;
        this.cityName = "";
        this.countryName = "";

    }

    public CityImportInfo(int position, String cityName, String countryName) {
        this.position = position;
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CityImportInfo{");
        sb.append("position=").append(position);
        sb.append(", cityName='").append(cityName).append('\'');
        sb.append(", countryName='").append(countryName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
