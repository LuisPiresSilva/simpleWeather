package mezu.simple.weather;

import android.Manifest;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.filters.SmallTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ProgressBar;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;

/**
 * Created by Luis Silva on 24/08/2018.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class baseActivityTest {

    @Rule
    public ActivityTestRule<baseActivity> mActivityRule = new ActivityTestRule<baseActivity>(baseActivity.class);

//    we assume we have been given location permissions
    @Rule
    public GrantPermissionRule mRuntimePermissionRule = GrantPermissionRule.grant(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION);



//this should not be done like this (Thread.sleep) but dont have much time to do it properly
    @Test
    public void simpleViewChecker() throws Exception {
        baseActivity activity = mActivityRule.getActivity();

        activity.bindViews();

        assertThat(R.id.weather_data_container, notNullValue());
        assertThat(R.id.weather_location, notNullValue());
        assertThat(R.id.weather_image, notNullValue());
        assertThat(R.id.weather_temperature, notNullValue());
        assertThat(R.id.weather_resume_container, notNullValue());

        Thread.sleep(2000);//wait for lock screen

        onView(withId(R.id.weather_data_container)).check(matches(not(isDisplayed())));
        onView(withId(R.id.weather_location)).check(matches(not(isDisplayed())));
        onView(withId(R.id.weather_image)).check(matches(not(isDisplayed())));
        onView(withId(R.id.weather_temperature)).check(matches(not(isDisplayed())));
        onView(withId(R.id.weather_resume_container)).check(matches(not(isDisplayed())));


        Thread.sleep(5000);//wait for data request

        onView(withId(R.id.weather_data_container)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_location)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_image)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_temperature)).check(matches(isDisplayed()));
        onView(withId(R.id.weather_resume_container)).check(matches(isDisplayed()));


    }


}
