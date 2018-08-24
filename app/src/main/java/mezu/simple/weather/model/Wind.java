package mezu.simple.weather.model;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class Wind {

    private String chill;
    private String direction;
    private String speed;

    public String getChill() {
        return chill;
    }

    public void setChill(String chill) {
        this.chill = chill;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Wind{\n" +
                "chill='" + chill + '\'' +
                ", direction='" + direction + '\'' +
                ", speed='" + speed + '\'' +
                "\n}";
    }
}
