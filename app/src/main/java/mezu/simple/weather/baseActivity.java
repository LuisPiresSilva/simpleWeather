package mezu.simple.weather;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import io.reactivex.Flowable;
import mezu.simple.weather.components.GlideApp;
import mezu.simple.weather.model.Channel;
import mezu.simple.weather.networking.WeatherRemoteDataSource;
import mezu.simple.weather.networking.WeatherResponseModel;
import mezu.simple.weather.networking.yahoo_weather_response_model.Results;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luis Silva on 22/08/2018.
 */
public class baseActivity extends AppCompatActivity implements ISecureActivity {

    private String[] PERMISSIONS_LOCATION = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private final int REQUEST_LOCATION = 1000;
    private LocationCallback mLocationCallback;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest mLocationRequest;

    private WeatherRemoteDataSource weatherRemoteDataSource;

    private enum UI_Flow_State {
        LOADING,
        DATA,
        ERROR
    }

    //LOADING STATE
    private ProgressBar weather_data_loading;

    //DATA STATE
    private LinearLayout weather_data_container;
    private TextView weather_location;
    private ImageView weather_image;
    private TextView weather_temperature;
    private TextView weather_description;
    private TextView weather_humidity;

    //ERROR STATE
    private LinearLayout weather_errors_options_container;
    private TextView weather_text_error_explanation;
    private Button weather_button_retry;
    private Button weather_button_load_lisbon;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        weatherRemoteDataSource = new WeatherRemoteDataSource();

        //hides the content of the app and does not let the user take screenshots of the app
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        bindViews();

        initLocation();

    }


    @Override
    protected void onStart() {
        super.onStart();
        setUIState(UI_Flow_State.LOADING);
        startLocationUpdates();

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopLocationUpdates();

    }

    private void setUIState(UI_Flow_State state){
        switch (state) {
            case LOADING:
                weather_data_loading.setVisibility(View.VISIBLE);
                weather_data_container.setVisibility(View.GONE);
                weather_errors_options_container.setVisibility(View.GONE);
                break;

            case DATA:
                weather_data_loading.setVisibility(View.GONE);
                weather_data_container.setVisibility(View.VISIBLE);
                weather_errors_options_container.setVisibility(View.GONE);
                break;

            case ERROR:
                weather_data_loading.setVisibility(View.GONE);
                weather_data_container.setVisibility(View.GONE);
                weather_errors_options_container.setVisibility(View.VISIBLE);
                break;
        }
    }

    protected void bindViews(){
        weather_data_loading = findViewById(R.id.weather_data_loading);

        weather_data_container = findViewById(R.id.weather_data_container);
        weather_location = findViewById(R.id.weather_location);
        weather_image = findViewById(R.id.weather_image);
        weather_temperature = findViewById(R.id.weather_temperature);
        weather_description = findViewById(R.id.weather_description);
        weather_humidity = findViewById(R.id.weather_humidity);

        weather_errors_options_container = findViewById(R.id.weather_errors_options_container);
        weather_text_error_explanation = findViewById(R.id.weather_text_error_explanation);
        weather_button_retry = findViewById(R.id.weather_button_retry);
        weather_button_load_lisbon = findViewById(R.id.weather_button_load_lisbon);

        weather_button_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUIState(UI_Flow_State.LOADING);
                startLocationUpdates();
            }
        });
        weather_button_load_lisbon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Location location = new Location("Lisbon");
                location.setLatitude(38.736946);
                location.setLongitude(-9.142685);
                setUIState(UI_Flow_State.LOADING);
                loadLocationData(location);
            }
        });
    }

    private void bindData(Channel channel) {
        weather_location.setText("Weather in " + channel.getCity().getCity() + ", " + channel.getCity().getCountry());
        //3200 also has image -> non availabe
        GlideApp.with(this).load(channel.getItem().getCondition().getConditionURL()).into(weather_image);
        weather_temperature.setText(channel.getItem().getCondition().getTemp() + "\u2109");//\u2103 for celsius
        weather_description.setText(channel.getItem().getCondition().getText());
        weather_humidity.setText("Humidity " + channel.getAtmosphere().getHumidity() + "%");

    }

    public void loadLocationData(@NonNull Location location) {
        Call<WeatherResponseModel> location_response = weatherRemoteDataSource.getLocation(location.getLatitude() + "",location.getLongitude() + "");
        location_response.enqueue(new Callback<WeatherResponseModel>() {
                    @Override
                    public void onResponse(Call<WeatherResponseModel> call, Response<WeatherResponseModel> response) {
                        if (response.isSuccessful()) {
//                            Log.i("Test","response: " + response.body().getQuery().toString());

                            Results results = response.body().getQuery().getResults();
                            if(results != null){
                                bindData(results.getChannel());
                                setUIState(UI_Flow_State.DATA);
                            } else {
                                weather_text_error_explanation.setText("Weather for this location is not available");
                                setUIState(UI_Flow_State.ERROR);
                            }

                        } else {//Server error or client bug
                            weather_text_error_explanation.setText(response.message());
                            setUIState(UI_Flow_State.ERROR);
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherResponseModel> call, Throwable t) {
                        weather_text_error_explanation.setText("Check your internet connection or location settings");
                        setUIState(UI_Flow_State.ERROR);
                    }
                });
    }




    //LOCATION
    private void initLocation(){
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(60000);
        mLocationRequest.setFastestInterval(30000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//ALLOW
                    startLocationUpdates();
                } else if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED) {//DENY
                    weather_text_error_explanation.setText("You must allow location to show your current location weather");
                    setUIState(UI_Flow_State.ERROR);
                }
            }
        }
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_LOCATION, REQUEST_LOCATION);
        } else {
            mLocationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    if (locationResult == null) {
                        return;
                    }

                    loadLocationData(locationResult.getLastLocation());
                }
            };
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null /* Looper */);
        }
    }

    private void stopLocationUpdates() {
        if(mLocationCallback != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
    }






//    DOES NOT WORK ANYMORE
    //    @Override
//    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
//        Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.some_image_to_show_instead_of_blank);
//        Log.i("onCreateThumbnail","onCreateThumbnail");
//        return super.onCreateThumbnail(image, canvas);
//    }

    @Override
    public void hideContents_for_background() {
        Log.i("activity","hide");

    }


    @Override
    public void showContents_for_foreground() {
        Log.i("activity","show");
        Intent myIntent = new Intent(this, secureActivity.class);
        startActivity(myIntent);

    }
}
