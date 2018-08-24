package mezu.simple.weather;

import android.app.Activity;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luis Silva on 23/08/2018.
 */
public class AppObserver implements LifecycleObserver {

    private Context ctx;
    private List<Activity> activities;

    public AppObserver (Context context){
        this.ctx = context;
        activities = new ArrayList<>();
    }

    public void addActivity(Activity top){
        activities.add(top);
    }

    public void removeLastActivity(Activity top){
        if(activities.size() > 1) {
            activities.remove(0);
        }
    }


    public Activity getTop(){
        if(!activities.isEmpty()){
            return activities.get(activities.size() - 1);
        }
        return null;
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onForeground() {
        if(getTop() != null && getTop() instanceof baseActivity){
            ((baseActivity) getTop()).showContents_for_foreground();
        }
        // App goes to foreground
        Log.i("Test","test foreground");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onBackground() {
        if(getTop() != null && getTop() instanceof baseActivity){
            Log.i("Test","test background - entered");
            ((baseActivity) getTop()).hideContents_for_background();
        }

        // App goes to background
        Log.i("Test","test background");


    }

}
