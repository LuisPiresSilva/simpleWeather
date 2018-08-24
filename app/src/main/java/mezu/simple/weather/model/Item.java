package mezu.simple.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class Item {

    private String title;
    private String lat;
    @SerializedName("long") //for json parser of GSON
    private String longtitude;
    private String pubDate;
    private Condition condition;

    //i dismissed the forecasts


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Item{\n" +
                "title='" + title + '\'' +
                ", lat='" + lat + '\'' +
                ", longtitude='" + longtitude + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", condition=" + condition +
                "\n}";
    }
}
