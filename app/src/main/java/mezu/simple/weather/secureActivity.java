package mezu.simple.weather;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by Luis Silva on 23/08/2018.
 */
public class secureActivity extends AppCompatActivity {

    private Activity thisActivity;
    private TextView counter;
    private CountDownTimer timer;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secure);

        thisActivity = this;
        counter = findViewById(R.id.hide_screen_counter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        counter.setText("2");
        timer = new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {
                counter.setText("" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                if(!thisActivity.isFinishing()) {
//                    Intent myIntent = new Intent(thisActivity, baseActivity.class);
//                    myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(myIntent);
                    thisActivity.finish();
                }
            }

        }.start();

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}
