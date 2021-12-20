package com.example.mareu.meeting_list;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ListMeetingActivityTest {

    private static int TODAY_DATE_FILTERED_BY_DATE_MEETING_COUNT = 2;
    private static int TODAY_DATE_FILTERED_BY_ROOM_MEETING_COUNT = 2;
    private static int ITEMS_TOTAL_COUNT = 4;

    private ListMeetingActivity activity;

    @Rule
    public ActivityTestRule<ListMeetingActivity> activityRule =
            new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        activity = activityRule.getActivity();
        assertThat(activity, notNullValue());
        List<Meeting> meetingList = DI.getMeetingApiService().getMeetings();
    }


    @Test
    public void listMeetingActivity_onFilterByDateClick_onlyDisplaysSelectedDate() {

        onView(ViewMatchers.withId(R.id.filter_button)).perform(click());
        onView(withText("Filter by date")).perform(click());
        onView(withText("OK")).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(TODAY_DATE_FILTERED_BY_DATE_MEETING_COUNT));

    }


    @Test
    public void listMeetingActivity_onFilterByRoomClick_onlyDisplaysSelectedRoom() {

        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Filter by room")).perform(click());
        onView(withText("Link")).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(TODAY_DATE_FILTERED_BY_ROOM_MEETING_COUNT));


    }


    @Test
    public void listMeetingActivity_onResetFilterClick_displaysAllMeetings() {

        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Reset filters")).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_TOTAL_COUNT));


    }


    @Test
    public void listMeetingActivity_deleteAction_shouldRemoveItem() {

        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_TOTAL_COUNT));

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_TOTAL_COUNT - 1));
    }


    @Test
    public void listMeetingActivity_onDeleteFilteredItem_isDeletedFromAllLists() {

        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Filter by room")).perform(click());
        onView(withText("Link")).perform(click());

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Reset filters")).perform(click());

        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_TOTAL_COUNT - 1));

    }
    //Test if FAB clicked should open create meeting activity

    @Test
    public void myNeighbourList_onItemClick_shouldOpenDetailedActivity() {

        onView(withId(R.id.addMeetinfab)).perform(click());

        onView(withId(R.id.create_meeting_activity)).check(matches(isDisplayed()));


    }
    //Test if recyclerview is empty, another view is displayed

    @Test
    public void listMeetingActivity_onEmptyList_displaysDifferentLayout() {

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));




        onView(withText(R.string.No_Meetings_Text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));


    }






}