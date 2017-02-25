package fr.android.androidexercises;

import android.app.Application;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Predicates.instanceOf;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.anything;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LibraryActivityTest extends ApplicationTestCase<Application> {
    public LibraryActivityTest() {
        super(Application.class);
    }

    @Rule
    public ActivityTestRule<LibraryActivity> mActivityRule = new ActivityTestRule<>(LibraryActivity.class);


    @Test
    public void changeText_sameActivity() throws InterruptedException {
        //onView(withId(R.id.coverBookItemView)).perform(click());
//       onData(anything()) // Use Hamcrest matchers to match item
//               .inAdapterView(withId(R.id.FrameLayoutList)). // Specify the explicit id of the ListView
//               atPosition(1).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
////        onView(withId(R.id.coverBookItemView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        //        onView(withId(R.id.passwordEdit)).perform(typeText("password"), closeSoftKeyboard());
//        onView(withId(R.id.loginButton)).perform(click());
//        onView(withId(R.id.loggedText)).check(matches(isDisplayed()));

    }
}