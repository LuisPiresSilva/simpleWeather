package mezu.simple.weather.model;

import android.net.Uri;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class Condition {

    private int code;
    private String date;
    private int temp;
    private String text;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getConditionURL(){
        return "http://l.yimg.com/a/i/us/we/52/" + code +".gif";
    }

    @Override
    public String toString() {
        return "Condition{\n" +
                "code=" + code +
                ", date='" + date + '\'' +
                ", temp=" + temp +
                ", text='" + text + '\'' +
                "\n}";
    }
}
