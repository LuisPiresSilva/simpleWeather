package mezu.simple.weather;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.ProcessLifecycleOwner;
import android.os.Bundle;
import android.util.Log;


/**
 * Created by Luis Silva on 23/08/2018.
 */
public class baseApplication extends Application implements Application.ActivityLifecycleCallbacks{

    private AppObserver lifecycle;


    @Override
    public void onCreate() {
        super.onCreate();

        lifecycle = new AppObserver(this);


        registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(lifecycle);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Log.i("Test", "started: " + activity.getLocalClassName());
        //we only add our activities (third party could eventually get in) and the activities we want to trigger events
        if(activity instanceof baseActivity ||
                activity instanceof secureActivity) {
            lifecycle.addActivity(activity);
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        Log.i("Test", "stopped: " + activity.getLocalClassName());
        if(activity instanceof baseActivity ||
                activity instanceof secureActivity) {
            lifecycle.removeLastActivity(activity);
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
