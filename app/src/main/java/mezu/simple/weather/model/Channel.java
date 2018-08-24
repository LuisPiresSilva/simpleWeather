package mezu.simple.weather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class Channel {

    private String lastBuildDate;
    @SerializedName("location")
    private City city;
    private Wind wind;
    private Atmosphere atmosphere;
    private Astronomy astronomy;
    private Item item;

    @Expose(serialize = false, deserialize = true)//we should receive the time to live but not send
    private int ttl;

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    @Override
    public String toString() {
        return "Channel{\n" +
                "lastBuildDate='" + lastBuildDate + '\'' +
                ", city=" + city +
                ", wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", item=" + item +
                ", ttl=" + ttl +
                "\n}";
    }
}
