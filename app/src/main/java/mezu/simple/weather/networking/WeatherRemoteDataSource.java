package mezu.simple.weather.networking;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class WeatherRemoteDataSource {

    private WeatherNetworkRequests api;
    private final String URL = "https://query.yahooapis.com";

//    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public WeatherRemoteDataSource() {
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//        httpClient.addInterceptor(logging);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(httpClient.build())
                .build();

        this.api = retrofit.create(WeatherNetworkRequests.class);
    }



    public Call<WeatherResponseModel> getLocation(String lat, String lon) {
        String query = "select * from weather.forecast where woeid in (SELECT woeid FROM geo.places WHERE text=\"(" + lat + "," + lon +")\")";
        return this.api.getLocation(query);
    }


//    public Call<WeatherResponseModel> getCityList(String pattern, int maxCount) {
//        String query = "select * from geo.places(" + maxCount + ") where text=\"" + pattern + "\")";
//        return this.api.getCityList(query);
//    }


}