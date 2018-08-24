package mezu.simple.weather.networking.yahoo_weather_response_model;

import mezu.simple.weather.model.Channel;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class Results {

    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return "Results{\n" +
                "channel=" + channel +
                "\n}";
    }
}
