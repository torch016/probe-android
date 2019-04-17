package org.openobservatory.ooniprobe.activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openobservatory.ooniprobe.R;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItem;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest public class MainActivityTest {
	@Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

	@Before public void before() {
	}

	@Test public void test() {
		onView(withId(R.id.recycler)).perform(actionOnItem(hasDescendant(withText(R.string.Test_Websites_Fullname)), click()));
	}
}
