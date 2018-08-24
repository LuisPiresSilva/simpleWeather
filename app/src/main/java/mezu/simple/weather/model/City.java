package mezu.simple.weather.model;

import com.google.gson.annotations.SerializedName;

public class City {

    private String city;
    private String country;
    private String region;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "City{\n" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                "\n}";
    }
}
