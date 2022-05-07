package com.hfad.cafeastrology;

import androidx.annotation.ContentView;
import androidx.test.core.app.ActivityScenario;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;


import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AppNavigationTest {
    @Test
    public void fragmentNavigationTest() {
        ActivityScenario activityScenario = ActivityScenario.launch(MainActivity.class);
        //check if the mainFragment is up
        onView(withId(R.id.nav_host_fragment)).check(matches(withId(R.id.nav_host_fragment)));
        //click on the instruction button
        onView(withId(R.id.instructionsbtn)).perform(click());
        //press back
        pressBack();
        //click on the button let's go
        onView(withId(R.id.nextbtn)).perform(click());
        //clear the text in the username view and type actual username
        onView(withId(R.id.usernameInput)).perform(clearText()).perform(typeText("sadje"));
        //clear the text in the password view and type actual password
        onView(withId(R.id.passwordInput)).perform(clearText()).perform(typeText("ser210"));
        //click submit button
        onView(withId(R.id.loginSubmit)).perform(click());




    }
}
