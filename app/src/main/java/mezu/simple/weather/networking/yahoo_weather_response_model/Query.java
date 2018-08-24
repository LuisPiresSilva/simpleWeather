package mezu.simple.weather.networking.yahoo_weather_response_model;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class Query {

    private int count;
    private Results results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Query{\n" +
                "count=" + count +
                ", results=" + results +
                "\n}";
    }
}
