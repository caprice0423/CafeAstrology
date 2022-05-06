package com.hfad.cafeastrology;

import static androidx.test.espresso.Espresso.onView;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginFragmentTest {
    private MainActivity activity = null;




   @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setup(){
        activity = mainActivityActivityTestRule.getActivity();


    }

   @Test
    public void checkUsername(){
        onView(withId(R.id.usernameInput)).perform(typeText("cap123"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.passwordInput)).perform(typeText("coolbeans"), ViewActions.closeSoftKeyboard());
        onView(withText("Submit")).perform(click());
        String expected = "cap123";
        onView(withId(R.id.appCompatTextView)).check(matches(withText(expected)));





   }

}