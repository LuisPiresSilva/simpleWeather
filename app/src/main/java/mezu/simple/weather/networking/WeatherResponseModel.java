package mezu.simple.weather.networking;

import mezu.simple.weather.networking.yahoo_weather_response_model.Query;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class WeatherResponseModel {

    private Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
