package mezu.simple.weather.networking;

import io.reactivex.Flowable;
import mezu.simple.weather.model.City;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public interface WeatherNetworkRequests {

    @GET("v1/public/yql?format=json")
    Call<WeatherResponseModel> getLocation(@Query("q") String query);


}
