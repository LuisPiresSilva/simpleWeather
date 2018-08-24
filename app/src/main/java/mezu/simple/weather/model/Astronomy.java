package mezu.simple.weather.model;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class Astronomy {

    private String sunrise;
    private String sunset;

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }


    @Override
    public String toString() {
        return "Astronomy{\n" +
                "sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                "\n}";
    }
}
