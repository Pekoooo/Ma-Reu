package com.example.mareu.meeting_list;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.mareu.R;
import com.example.mareu.utils.DeleteViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ListMeetingActivityTest {

    private static final int TODAY_DATE_FILTERED_BY_DATE_MEETING_COUNT = 2;
    private static final int EXPECTED_AMOUNT_OF_MEETINGS_IN_LINK_ROOM = 2;
    private static final int ITEMS_TOTAL_COUNT = 4;

    private ListMeetingActivity activity;

    @Rule
    public ActivityTestRule<ListMeetingActivity> activityRule =
            new ActivityTestRule(ListMeetingActivity.class);

    @Before
    public void setUp() {
        activity = activityRule.getActivity();
        assertThat(activity, notNullValue());

    }

    @Test
    public void onResetFilterClick_displaysAllMeetings() {

        onView(ViewMatchers.withId(R.id.filter_button)).perform(click());
        onView(withText("Filter by date")).perform(click());
        onView(withText("OK")).perform(click());

        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Reset filters")).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_TOTAL_COUNT));


    }


    @Test
    public void onFilterByDateClick_onlyDisplays_SelectedDateMeetings() {

        onView(ViewMatchers.withId(R.id.filter_button)).perform(click());
        onView(withText("Filter by date")).perform(click());
        onView(withText("OK")).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(TODAY_DATE_FILTERED_BY_DATE_MEETING_COUNT));

    }


    @Test
    public void onFilterByRoomClick_onlyDisplays_SelectedRoomMeetings() {

        onView(withId(R.id.filter_button)).perform(click());
        onView(withText("Filter by room")).perform(click());
        onView(withText("Link")).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(EXPECTED_AMOUNT_OF_MEETINGS_IN_LINK_ROOM));


    }


    @Test
    public void deleteAction_shouldRemoveItem() {

        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_TOTAL_COUNT - 1));

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));

        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(withItemCount(ITEMS_TOTAL_COUNT - 2));
    }


    @Test
    public void onDeleteFilteredItem_isDeletedFromAllLists() {

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
    public void onFabClick_shouldOpenCreateMeetingActivity() {

        onView(withId(R.id.addMeetinfab)).perform(click());

        onView(withId(R.id.create_meeting_activity)).check(matches(isDisplayed()));


    }




}