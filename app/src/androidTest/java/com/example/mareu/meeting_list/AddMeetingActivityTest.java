package com.example.mareu.meeting_list;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.R;
import com.example.mareu.di.DI;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.core.IsNull.notNullValue;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;


import static org.junit.Assert.*;

import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.meeting_list.ListMeetingActivity;
import com.example.mareu.model.Meeting;
import com.example.mareu.utils.DeleteViewAction;

import java.util.List;


@RunWith(AndroidJUnit4.class)

public class AddMeetingActivityTest {

    private AddMeetingActivity activity;

    @Rule
public ActivityTestRule<AddMeetingActivity> activityRule = new ActivityTestRule(AddMeetingActivity.class);

    @Before
    public void setUp()  {
        activity = activityRule.getActivity();
        assertThat(activity, notNullValue());



    }


    //toast when try to create meeting without topic

    @Test
    public void displayToastWhen_MeetingTopicIsEmpty_withSuccess() {

        onView(withId(R.id.edit_text_participants)).perform(typeText("didier@lamzone.com")).perform(pressImeActionButton());
        onView(withId(R.id.editText)).perform(click());
        onView(withText("Link")).perform(click());
        onView(withId(R.id.button_setup_time)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.button_setup_date)).perform(click());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.button_create_meeting)).perform(click());

        onView(withText("Make sure all text fields are filled in"))
                .inRoot((withDecorView(not(activityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));




    }


    //toast when try to create meeting without participants
    //toast when try to create meeting without selecting room
    //toast when try to create meeting without time
    //toast when try to create meeting without date
    //toast when participant is not typed with email format
    //toast when try to create meeting that is already created at the same date and hour
    //

}